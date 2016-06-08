package com.dbExercise5.client;

import java.util.Random;
import java.util.UUID;

import com.dbExercise5.core.PersistenceManager;
import com.dbExercise5.util.SynchronisedCounter;

public class Client extends Thread
{
    private PersistenceManager pm;
    
    private SynchronisedCounter idGenerator;
    private int clientid;
    private int currentTransaction;
    
    public Client(int clid)
    {
//    	idGenerator.increment();
//    	clientid = idGenerator.value();
    	
    	this.clientid = clid;
    }

    public int getClientid() {
    	return clientid;
    }

    public void setClientid(int clientid) {
    	this.clientid = clientid;
    }

    public int getCurrentTransaction() {
    	return currentTransaction;
    }

    public void setCurrentTransaction(int currentTransaction) {
    	this.currentTransaction = currentTransaction;
    }
    
    public void run()
    {
    	pm = PersistenceManager.getInstance();
    	
    	Random rng = new Random();
    	
    	for (int j = 0; j < rng.nextInt(20); j++)
    	{
    		currentTransaction = pm.beginTransaction();
        	
        	for (int i = 0; i < rng.nextInt(20); i++)
        	{	
        	    pm.write(currentTransaction, clientid * 10 + rng.nextInt(9), UUID.randomUUID().toString());
        	    try
        	    {
        	    	Thread.sleep(rng.nextInt(500));
        	    }
        	    catch (InterruptedException e)
        	    {
        		
        	    }
        	}
        	
        	pm.commit(currentTransaction);
        	currentTransaction = 0;
        	
        	try
        	{
        		Thread.sleep(rng.nextInt(500));
        	}
        	catch (InterruptedException e)
        	{
        	    
        	}
    	}
    }
}

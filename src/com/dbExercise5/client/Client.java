package com.dbExercise5.client;

import java.util.UUID;

import com.dbExercise5.persistence.PersistenceManager;
import com.dbExercise5.util.SynchronisedCounter;

public class Client extends Thread
{
    private SynchronisedCounter idGenerator;
    private PersistenceManager pm;
    private int clientid;
    private int currentTransaction;
    
    public Client()
    {
	idGenerator.increment();
	clientid = idGenerator.value();
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
	currentTransaction = pm.beginTransaction();
	
	for (int i = 0; i < 5; i++)
	{	
	    pm.write(currentTransaction, clientid * 10 + i, UUID.randomUUID().toString());
	    try
	    {
		Thread.sleep(1000);
	    }
	    catch (InterruptedException e)
	    {
		
	    }
	}
	
	pm.commit(currentTransaction);
    }
}

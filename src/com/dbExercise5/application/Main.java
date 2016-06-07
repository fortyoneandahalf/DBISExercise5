package com.dbExercise5.application;

import com.dbExercise5.client.Client;
import com.dbExercise5.core.PersistenceManager;
import com.dbExercise5.util.SynchronisedCounter;

public class Main {
    
    public static void main(String[] args)
    {
	// Create PersistenceManager object
	PersistenceManager pm = PersistenceManager.getInstance();
	
	// Create clients
	SynchronisedCounter clientIDGenerator = new SynchronisedCounter();
	
	clientIDGenerator.increment();
	Client c1 = new Client(clientIDGenerator.value());
	clientIDGenerator.increment();
	Client c2 = new Client(clientIDGenerator.value());
	clientIDGenerator.increment();
	Client c3 = new Client(clientIDGenerator.value());
	
	// Start the clients running
	c1.start();
	c2.start();
	c3.start();
	
	// Pause for a while and let the threads run
	try
	{
	    Thread.sleep(5000);
	}
	catch (InterruptedException e)
	{
	    System.err.println("InterruptedException: " + e.getMessage());
	}
	
	// Stop clients
	c1.interrupt();
//	c2.interrupt();
//	c3.interrupt();
    }

}

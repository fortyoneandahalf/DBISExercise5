package com.dbExercise5.application;

import com.dbExercise5.client.Client;
import com.dbExercise5.persistence.PersistenceManager;

public class Main {
    
    public static void main(String[] args)
    {
	// Create PersistenceManager object
	PersistenceManager pm = PersistenceManager.getInstance();
	
	// Create clients
	Client c1 = new Client();
	Client c2 = new Client();
	Client c3 = new Client();
	
	// Start the clients running
	c1.start();
	c2.start();
	c3.start();
	
	// Pause for a while and let the threads run
	try
	{
	    Thread.sleep(10000);
	}
	catch (InterruptedException e)
	{
	    System.err.println("InterruptedException: " + e.getMessage());
	}
	
	// Stop clients
	c1.interrupt();
	c2.interrupt();
	c3.interrupt();
    }

}

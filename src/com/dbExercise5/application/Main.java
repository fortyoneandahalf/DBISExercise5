package com.dbExercise5.application;

import java.util.Random;

import com.dbExercise5.client.Client;
import com.dbExercise5.core.PersistenceManager;
import com.dbExercise5.util.SynchronisedCounter;

public class Main {
    
    public static void main(String[] args)
    {
    	Random rng = new Random();
    	
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
    	clientIDGenerator.increment();
    	Client c4 = new Client(clientIDGenerator.value());
    	clientIDGenerator.increment();
    	Client c5 = new Client(clientIDGenerator.value());
    	
    	// Start the clients running
    	c1.start();
    	sleep(rng.nextInt(500) + 500);
    	c2.start();
    	sleep(rng.nextInt(500) + 500);
    	c3.start();
    	sleep(rng.nextInt(500) + 500);
    	c4.start();
    	sleep(rng.nextInt(500) + 500);
    	c5.start();
    	sleep(rng.nextInt(500) + 500);
    	
    	// Pause for a while and let the threads run
    	sleep(15000);
    	
    	// Stop clients
    	c1.interrupt();
    	c2.interrupt();
    	c3.interrupt();
    	c4.interrupt();
    	c5.interrupt();
    }
    
    private static void sleep(int duration)
    {
    	try
    	{
    	    Thread.sleep(duration);
    	}
    	catch (InterruptedException e)
    	{
    	    System.err.println("InterruptedException: " + e.getMessage());
    	}
    }
}

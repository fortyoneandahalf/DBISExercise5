package com.dbExercise5.persistence;

import java.util.Hashtable;

public class PersistenceManager {
    
    private static final PersistenceManager pm;
    private static Hashtable buffer;
    
    static
    {
	try
	{
	    pm = new PersistenceManager();
	}
	catch (Throwable e)
	{
	    throw new RuntimeException(e.getMessage());
	}
    }
    
    private PersistenceManager()
    {
	buffer = new Hashtable();
    }
    
    public static PersistenceManager getInstance()
    {
	return pm;
    }
    
    public int beginTransaction()
    {
	// TODO
	return 0;
    }
    
    public boolean commit(int taid)
    {
	// TODO
	return false;
    }
    
    public boolean write(int taid, int pageid, String data)
    {
	// TODO
	return false;
    }
    
}

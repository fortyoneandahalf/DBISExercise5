package com.dbExercise5.core;

import java.util.Hashtable;
import java.util.Iterator;

import com.dbExercise5.util.SynchronisedCounter;

public class PersistenceManager {
    
    private static final PersistenceManager PM;
    
    private static final String DB_FOLDER = "data";
    private static final String LOG_FOLDER = "log";
    
    private static Hashtable<Integer, String> pageBuffer = new Hashtable<Integer, String>();
    private static Hashtable<Integer, Transaction> transactions = new Hashtable<Integer, Transaction>();
//    private static Hashtable<Integer, LogEntry> logBuffer;
    private static SynchronisedCounter taidGenerator;
    private static SynchronisedCounter lsnGenerator;
    
    static
    {
	try
	{
	    PM = new PersistenceManager();
	}
	catch (Throwable e)
	{
	    throw new RuntimeException(e.getMessage());
	}
    }
    
    private PersistenceManager()
    {
	pageBuffer = new Hashtable<Integer, String>();
	taidGenerator = new SynchronisedCounter();
	lsnGenerator = new SynchronisedCounter();
    }
    
    public static PersistenceManager getInstance()
    {
	return PM;
    }
    
    public int beginTransaction()
    {
	taidGenerator.increment();
	Transaction ta = new Transaction(taidGenerator.value());
	transactions.put(ta.getTaid(), ta);
	return ta.getTaid();
    }
    
    public void commit(int taid)
    {
	Transaction ta = transactions.get(taid);
	
	// Iterate through the pages modified by the transaction
	Iterator<Integer> pageIterator = ta.getPages().iterator();
	
	while (pageIterator.hasNext())
	{
	    int page = pageIterator.next();
	    
	    // Flush log data for all pages in the transaction
	    lsnGenerator.increment();
	    flushLog(lsnGenerator.value(), taid, page, pageBuffer.get(page));
	}
	
	ta.setCommitted(true);
    }
    
    public void write(int taid, int pageid, String data)
    {
	pageBuffer.put(pageid, data);
	
	if (pageBuffer.size() > 5)
	{
	    flushPageBuffer();
	}
    }
    
    private void flushLog(int lsn, int taid, int pageid, String data)
    {
	// TODO: write log to disk
    }
    
    private void flushPageBuffer()
    {
	// Iterate through committed transactions
	Iterator<Transaction> taIterator = transactions.values().iterator();
	
	while (taIterator.hasNext())
	{
	    Transaction ta = taIterator.next();
	    
	    if (!ta.isCommitted()) {continue;}
	    
	    // Iterate through pages for the committed transaction
	    Iterator<Integer> pageIterator = ta.getPages().iterator();
	    
	    while (pageIterator.hasNext())
	    {
		// TODO
		// Flush the log for this page
		// Flush the page to disk
		// Remove the page from the buffer
	    }
	    
	    // Remove the transaction from the set?
	}
    }
    
    private void flushPage(int pageid)
    {
	// TODO: write page with given page ID to disk
    }
}

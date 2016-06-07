package com.dbExercise5.core;

import java.util.Hashtable;
import java.util.Iterator;

import com.dbExercise5.util.SynchronisedCounter;
import com.dbExercise5.logging.LogEntry;
import com.dbExercise5.util.FileUtilities;

public class PersistenceManager {
    
    private static final PersistenceManager PM;
    
    private static Hashtable<Integer, String> pageBuffer = new Hashtable<Integer, String>();
    private static Hashtable<Integer, Transaction> transactions = new Hashtable<Integer, Transaction>();
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
    	
    	lsnGenerator.increment();
    	flushLog(lsnGenerator.value(), "BOT", ta.getTaid(), 0, "");
    	
    	transactions.put(ta.getTaid(), ta);
    	
    	return ta.getTaid();
    }
    
    public void commit(int taid)
    {
    	Transaction ta = transactions.get(taid);
    	
    	lsnGenerator.increment();
    	flushLog(lsnGenerator.value(), "COMMIT", taid, 0, "");
    	
    	ta.setCommitted(true);
    }
    
    public void write(int taid, int pageid, String data)
    {
    	pageBuffer.put(pageid, data);
    	lsnGenerator.increment();
    	flushLog(lsnGenerator.value(), "WRITE", taid, pageid, data);
    	
    	Transaction ta = transactions.get(taid);
    	ta.addPage(pageid);
    	
    	if (pageBuffer.size() > 5)
    	{
    	    flushPageBuffer();
    	}
    }
    
    private void flushLog(int lsn, String logType, int taid, int pageid, String data)
    {
    	LogEntry logEntry = new LogEntry(lsn, logType, taid, pageid, data);
    	FileUtilities.writeLogEntryToFile(logEntry);
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
    		// Flush the page to disk
    		int pageid = pageIterator.next();
    		flushPage(pageid);
    		
    		// Remove the page from the buffer
    		pageBuffer.remove(pageid);
    	    }
    	    
    	    // Remove the transaction from the set of active transactions
    	    transactions.remove(ta.getTaid());
    	}
    }
    
    private void flushPage(int pageid)
    {
    	lsnGenerator.increment();
    	Page page = new Page(pageid, lsnGenerator.value(), pageBuffer.get(pageid));
    	FileUtilities.writePageToFile(page);
    }
}

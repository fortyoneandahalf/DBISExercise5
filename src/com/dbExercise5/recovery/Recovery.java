package com.dbExercise5.recovery;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.dbExercise5.logging.LogEntry;
import com.dbExercise5.util.FileUtilities;
import com.dbExercise5.core.Page;

public class Recovery {
    
    public static void recover ()
    {
	LinkedHashSet<LogEntry> logs = new LinkedHashSet<LogEntry> ();
	
	// Read all logs
	// If this were a proper system, we'd have some kind of checkpointing here
	
	logs = FileUtilities.readAllLogsFromFile();
	
	// Analyse logs and make a record of winner transactions.
	
	HashSet<Integer> winners = new LinkedHashSet<Integer>();
	
	Iterator<LogEntry> logIt = logs.iterator();
	while (logIt.hasNext())
	{
	    LogEntry le = logIt.next();
	    
	    if (le.getLogType() == "COMMIT")
	    {
		winners.add(le.getTaid());
	    }
	}
	
	// Redo page modifications which were committed by winner transactions but didn't
	// reach persistent storage
	
	logIt = logs.iterator();
	while (logIt.hasNext())
	{
	    LogEntry le = logIt.next();
	    
	    Page p = FileUtilities.readPageFromFile(le.getPageid());
	    
	    if (le.getLsn() > p.getLsn() && winners.contains(le.getTaid()))
	    {
		FileUtilities.writePageToFile(new Page(le.getPageid(), le.getLsn(), le.getData()));
	    }
	}
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	recover();

    }

}

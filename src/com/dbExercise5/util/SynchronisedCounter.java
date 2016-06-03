package com.dbExercise5.util;

public class SynchronisedCounter
{
    private int i = 0;
    
    public synchronized void increment()
    {
	i++;
    }
    
    public synchronized void decrement()
    {
	i--;
    }
    
    public synchronized int value()
    {
	return i;
    }
}

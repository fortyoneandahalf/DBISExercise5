package com.dbExercise5.client;

import java.util.UUID;

public class Client {
    
    private UUID clientid;
    
    public Client()
    {
	clientid = UUID.randomUUID();
    }

    public UUID getClientid() {
	return clientid;
    }

    public void setClientid(UUID clientid) {
	this.clientid = clientid;
    }
    
}

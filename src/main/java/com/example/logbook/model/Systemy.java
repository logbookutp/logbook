/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logbook.model;

public class Systemy {
    
    private String id;
    private String name;
    private String description;
    private String administratorId;
    private String administratorName;

    public Systemy(String id, String name, String description, String administratorId, String administratorName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.administratorId = administratorId;
        this.administratorName = administratorName;
    }

    public Systemy() {
        
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public String getAdministratorName() {
        return administratorName;
    }    
    
}

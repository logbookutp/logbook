/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logbook.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Właściciel
 */
public class User {
	@NotNull
    private String login;
	@NotNull
    private String password;
    private String imie;

    public User(){}
    
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

    
}


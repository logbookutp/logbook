/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logbook.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import com.example.logbook.model.User;


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UzytkownikSesja implements Serializable{
    
    private String login = "";
    private String imie = "";
    
    public void zapiszLoginWsesji(String login){
        this.login = login;
    }
    public void zapiszImieWsesji(String imie){
    	this.imie = imie;
    }
    
    public User zwrocUzytkownika(){
        User user = new User();
        user.setLogin(login);
        user.setImie(imie);
        return user;
    }
}

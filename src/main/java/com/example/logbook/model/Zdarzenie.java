package com.example.logbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Zdarzenie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dataZdarzenia;	
	private String czasZdarzenia;	
	@Size(min = 2, max = 255, message = "Opis musi się składać z 2-255 znaków")
    private String opis;   
    private String uzytkownik;  
    private String nazwaSystemu;
    
    public String getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataZdarzenia() {
        return dataZdarzenia;
    }

    public void setDataZdarzenia(String dataZdarzenia) {
        this.dataZdarzenia = dataZdarzenia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNazwaSystemu() {
        return nazwaSystemu;
    }

    public void setNazwaSystemu(String nazwaSystemu) {
        this.nazwaSystemu = nazwaSystemu;
    }

	public String getCzasZdarzenia() {
		return czasZdarzenia;
	}

	public void setCzasZdarzenia(String czasZdarzenia) {
		this.czasZdarzenia = czasZdarzenia;
	}    
  
}

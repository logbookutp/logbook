package com.example.logbook.controller;


import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.logbook.dao.ZdarzenieDAO;
import com.example.logbook.model.Systemy;
import com.example.logbook.model.User;
import com.example.logbook.model.Zdarzenie;
import com.google.gson.Gson;

@Controller
//@RequestMapping("/dziennik")
public class KontrolerDziennika {
    
    @Autowired
    ZdarzenieDAO zdarzenieDAO;

    @Autowired
    UzytkownikSesja uzytkownikSesja;
    
    @RequestMapping("/")
    public String wyswietlStronePowitalna(){
    	
    	return "redirect:/stronaGlowna";
    }
    
    @RequestMapping("stronaGlowna")
    public ModelAndView PrzekierujNaHome() {
        if(uzytkownikSesja.zwrocUzytkownika().getLogin().equals(""))
        {
            return new ModelAndView("logowanie").addObject("user", new User());
        }
        Iterable<Systemy> systemy = zwrocSystemyUzytkownika();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("systemy", systemy);
        mav.addObject("imie", uzytkownikSesja.zwrocUzytkownika().getImie());
        return mav;
    }

    @RequestMapping("wyloguj")
    public String PrzekierujNaLogPoWylogowaniu() {
        uzytkownikSesja.zapiszLoginWsesji("");  //Usuniecie poprzedniego loginu (poprzez zastąpienie pustym stringiem)
        uzytkownikSesja.zapiszImieWsesji("");
        return "redirect:/zaloguj";   //nazwa kontrolera
    }

    @RequestMapping("zaloguj")
    public ModelAndView PrzekierujNaLogowanie() {
        return new ModelAndView("logowanie").addObject("user", new User());
    }
    
    @RequestMapping(value = "zaloguj", method = RequestMethod.POST)
    public String uwierzytelnijUzytkownika(User user){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 			//okreslenie typu wysylanej informacji
        Gson gson = new Gson();
        String loginIhaslo = gson.toJson(user); 						//parsowanie usera do postaci JSONA
        HttpEntity<String> entity = new HttpEntity<String>(loginIhaslo, headers);
        String url = "http://212.122.192.216:8097/api/v1/user/checklogin";
        ResponseEntity<String> responseApi = restTemplate.postForEntity(url, entity, String.class);
        System.out.println(responseApi.toString());
        if(responseApi.toString().contains("\"success\":true")        		//sprawdzam czy istnieje uzytkownik o podanym loginie i haśle
                && responseApi.toString().contains("\"isAsi\":true")) 		//oraz czy należy do ASI jeśli tak to zapamiętuje login w sesji i kieruje na następną stronę
        {
            uzytkownikSesja.zapiszLoginWsesji(user.getLogin());
            uzytkownikSesja.zapiszImieWsesji(zwrocImie(responseApi.toString()));
            System.out.println(uzytkownikSesja.zwrocUzytkownika().getImie());
            return "redirect:/stronaGlowna";
        }
        return "redirect:/zaloguj"; //zwracam nazwę kontrolera zwracającego typ ModelAndView i stronę logowanie
    }

    @RequestMapping("/wyswietlZdarzenie")//obsluga drugiego przycisku ze strony home.jsp
    public ModelAndView Wyswietl() {
        if(uzytkownikSesja.zwrocUzytkownika().getLogin().equals(""))
        {
            return new ModelAndView("logowanie").addObject("user", new User());
        }
    	Iterable<Zdarzenie> zdarzenia = zdarzenieDAO.findAll();//wyszukanie wszystkich rekordow z bazy
        ModelAndView mav = new ModelAndView("dziennikZdarzen");
        mav.addObject("imie", uzytkownikSesja.zwrocUzytkownika().getImie());
        mav.addObject("zdarzenia", zdarzenia);//przekazanie kolekcji do widoku dziennikZdarzen
        return mav;
    }
    
    @RequestMapping("/dodajZdarzenie")
    public ModelAndView wyswietlFormDodawania() {//metoda wywolywana podczas klikniecia znacznika dodajZdarzenie na home.jsp
        if(uzytkownikSesja.zwrocUzytkownika().getLogin().equals(""))
        {
            return new ModelAndView("logowanie").addObject("user", new User());
        }
    	ModelAndView mav = new ModelAndView("dodajZd");
    	mav.addObject("imie", uzytkownikSesja.zwrocUzytkownika().getImie());
        mav.addObject("zdarzenie", new Zdarzenie()); //przekazanie obiektu do formularza pod identyfikatorem "zdarzenie"
        mav.addObject("systemy", zwrocSystemyUzytkownika());
        return mav;
    }

    @RequestMapping(value = "dodajZdarzenie", method = RequestMethod.POST)//wywolywane podczas wyslania formularza ze strony dodajZd.jsp
    public String dodajZdarzenie(@Valid Zdarzenie zdarzenie, BindingResult binding) {
        if(uzytkownikSesja.zwrocUzytkownika().getLogin().equals(""))
        {
            return "redirect:/zaloguj";
        }
    	if (binding.hasErrors()) {
            return "dodajZd";   
        }
        zdarzenie.setUzytkownik(uzytkownikSesja.zwrocUzytkownika().getImie());
        zdarzenieDAO.save(zdarzenie);   //zapisanie zdarzenia w bazie danych
        return "redirect:/stronaGlowna";   //nazwa kontrolera ktory wyswietla strone jsp
    }

    @RequestMapping("wyswietlSystemy")  //nazwa kontrolera ktorego wywoluje link ze strony głównej 
    public ModelAndView wyswietlSystemy() {
        if(uzytkownikSesja.zwrocUzytkownika().getLogin().equals(""))
        {
            return new ModelAndView("logowanie").addObject("user", new User());
        }
    	RestTemplate restTemplate = new RestTemplate();
        String url = "http://212.122.192.216:8097/api/v1/itsystem/all";
        Iterable<Systemy> systems = restTemplate.getForObject(url, Iterable.class); 
        ModelAndView mav = new ModelAndView("systemy");
        mav.addObject("imie", uzytkownikSesja.zwrocUzytkownika().getImie());
        mav.addObject("system", systems);
        return mav;
    }
    
    List<Systemy> zwrocSystemyUzytkownika(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://212.122.192.216:8097/api/v1/itsystem/all";
        ResponseEntity<List<Systemy>> systemy = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Systemy>>(){});
        Iterator<Systemy> iter = systemy.getBody().iterator();
        String nazwaAdmina;
        while(iter.hasNext())
        {
        	//usuwam system z kolekcji gdy NIE NALEŻY do aktualnie zalogowanego uzytkownika
        	nazwaAdmina = iter.next().getAdministratorName();
        	if(!nazwaAdmina.equals(uzytkownikSesja.zwrocUzytkownika().getLogin()))
        		iter.remove();
        }        
        return systemy.getBody();
    }
    
    //funkcja wyszukuje imie uzytkownika w Json'ie zwróconym przez API .../checklogin
    String zwrocImie(String stringZapi){
    	StringTokenizer st = new StringTokenizer(stringZapi, ",");
        String token;
        while(st.hasMoreTokens()){
        	token = st.nextToken();
        	if(token.contains("name")){
        		token = token.substring(1, token.length() - 1); //usuniecie pierwszego i ostatniego cudzysłowu		
        		return token.substring(token.indexOf(":") + 2); //imieInazwisko występuje po dwukropku i cudzysłowu stąd "przeskok" o 2
        	}
        }
        return "brakPolaNameApi";
    }
}

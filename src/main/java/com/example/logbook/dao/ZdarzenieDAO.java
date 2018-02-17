package com.example.logbook.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.logbook.model.Zdarzenie;


public interface ZdarzenieDAO extends CrudRepository<Zdarzenie, Integer>{
    //public Iterable<Zdarzenie> findByNameLike(String name);
    
    @Query("select z from Zdarzenie z where z.dataZdarzenia like :data order by z.dataZdarzenia")
    public Iterable<Zdarzenie> findByJPQL(@Param("data") String dataZdarzenia);
}

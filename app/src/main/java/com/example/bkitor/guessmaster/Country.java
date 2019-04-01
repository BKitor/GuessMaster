package com.example.bkitor.guessmaster;

import com.example.bkitor.guessmaster.Date;
import com.example.bkitor.guessmaster.Entity;

/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/
public class Country extends Entity {

    private String capital;

    public Country(){
        super();
        capital = "";
    }
    public Country(Date born, String name, Double difficulty, String capital){
        super(born, name, difficulty);
        this.capital = capital;
    }
    public Country(int month, int day, int year, String name, Double difficulty, String capital)throws Exception{
        super(month, day, year, name, difficulty);
        this.capital = capital;
    }
    public Country(Country copyCountry){
        super(copyCountry);
        capital = copyCountry.capital;
    }

    @Override
    public String entityType(){
        return "This entity is a Country!";
    }

    @Override
    public Country clone() {
        return new Country(this);
    }

    @Override
    public String toString(){
        return super.toString()+"Capital Ciry: "+this.capital+"\n";
    }
}

package com.example.bkitor.guessmaster;

import com.example.bkitor.guessmaster.Date;
import com.example.bkitor.guessmaster.Person;

/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/
public class Politician extends Person {

    private String party;

    public Politician(){
        super();
        party = "";
    }
    public Politician(Date born, String name, Double difficulty, String gender, String party){
        super(born, name, difficulty, gender);
        this.party = party;
    }
    public Politician(int month, int day, int year, String name, Double difficulty, String gender, String party)throws Exception{
        super(month, day, year, name, difficulty, gender);
        this.party = party;
    }
    public Politician(Politician copyPolitician){
        super(copyPolitician);
        party = copyPolitician.party;
    }


    @Override
    public Politician clone() {
        return new Politician(this);
    }

    @Override
    public String entityType() {
        return "This entity Type is a person";
    }

    @Override
    public String toString(){
        return super.toString()+"Party: "+this.party+'\n';
    }

}

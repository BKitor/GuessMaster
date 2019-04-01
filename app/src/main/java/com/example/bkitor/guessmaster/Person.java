package com.example.bkitor.guessmaster;

/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/
public class Person extends Entity {

    private String gender;

    public Person(){
        super();
        gender = "";
    }
    public Person(Date born, String name, Double difficulty, String gender){
        super(born, name, difficulty);
        this.gender = gender;
    }
    public Person(int month, int day, int year, String name, Double difficulty, String gender)throws Exception{
        super(month, day, year, name, difficulty);
        this.gender = gender;
    }
    public Person(Person copyPerson){
        super(copyPerson);
        gender = copyPerson.gender;
    }

    @Override
    public String entityType() {
        return "This entity is a Person";
    }

    @Override
    public Entity clone() {
        return new Person(this);
    }

    @Override
    public String toString(){
        return super.toString()+"Gender: "+this.gender+"\n";
    }

}

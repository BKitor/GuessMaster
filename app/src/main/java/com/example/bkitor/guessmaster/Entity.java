package com.example.bkitor.guessmaster;

import com.example.bkitor.guessmaster.Date;

/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/
public abstract class Entity {
    //Fields getters and setters
    private String name;
    private Date born;
    private Double difficulty;

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    //Constructors, equals and tostring Methods
    public Entity(Date born, String name, Double difficulty) {
        this.born = born;
        this.name = name;
        this.difficulty = difficulty;
    }

    public Entity(int month, int day, int year, String name, Double difficulty) throws Exception {
        this.born = new Date(month, day, year);
        this.name = name;
        this.difficulty = difficulty;
    }

    public Entity() {
        name = "";
        born = new Date();
        difficulty = 0.0;
    }

    public Entity(Entity copyEntity) {
        if (copyEntity == null) {
            System.out.println("Fatal Error.");
            System.exit(0);
        }
        name = copyEntity.name;
        born = new Date(copyEntity.born);
        difficulty = copyEntity.difficulty;
    }

    public String toString() {
        return this.name + " was born on " + this.born.toString()+". \nDifficulty is: "+this.difficulty+"\n";
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null && this.getClass() != otherObject.getClass())
            return false;
        Entity otherEntiry = (Entity) otherObject;
        return (this.name.equals((otherEntiry).name)) && (this.born.equals(otherEntiry.born));
    }

    public int getAwardedTicketNumber(){
        return (int) (this.difficulty*100);
    }

    public abstract String entityType();

    public abstract Entity clone();

    public String welcomeMessage(){
        return "Welcome, let's start the game."+this.entityType()+"\nGuess "+this.name+"'s birthday.";
    }

    public String closingMessage(){
        return "Congrats, you got it! \n"+this.toString();
    }

}

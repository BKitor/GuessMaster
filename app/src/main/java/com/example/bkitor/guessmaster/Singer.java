package com.example.bkitor.guessmaster;

/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/
public class Singer extends Person{

    private String debutAlbum;
    private Date debutAlubmReleaseDate;

    public Singer(){
        super();
        debutAlbum = "";
        debutAlubmReleaseDate=new Date();
    }
    public Singer(Date born, String name, Double difficulty, String gender, String debutAlbum, Date debutAlbumReleaseDate){
        super(born, name, difficulty, gender);
        this.debutAlbum = debutAlbum;
        this.debutAlubmReleaseDate = debutAlbumReleaseDate;
    }
    public Singer(int month, int day, int year, String name, Double difficulty, String gender, String debutAlbum, Date debutAlubmReleaseDate)throws Exception{
        super(month, day, year, name, difficulty, gender);
        this.debutAlbum = debutAlbum;
        this.debutAlubmReleaseDate = debutAlubmReleaseDate;
    }
    public Singer(Singer cloneSinger){
        super(cloneSinger);
        debutAlbum = cloneSinger.debutAlbum;
        debutAlubmReleaseDate = new Date(debutAlubmReleaseDate);
    }

    @Override
    public String entityType(){
        return "This entity is a Person!";
    }

    @Override
    public Singer clone(){
        return new Singer(this);
    }

    @Override
    public String toString(){
        return super.toString()+"debut album is is "+this.debutAlbum+" and it was released on "+this.debutAlubmReleaseDate+"\n";
    }

}

package com.example.bkitor.guessmaster;/*
Written by Benjamin Kitor, 17bwk, 20068579
Mar 12, 2019
*/

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.example.bkitor.guessmaster.Country;
import com.example.bkitor.guessmaster.Politician;

import java.util.Scanner;

public class GuessMaster extends AppCompatActivity {
    //Fields for logic
    private static int numberOfcandidateEntities;
    private Entity[] entities;
    private int score;
    private String userInput;
    String answer;
    Entity activeEntity;
    //Fields for Android
    private TextView entityName;
    private TextView ticketSum;
    private Button guessButton;
    private Button buttonClearContent;
    private EditText userIn;
    private ImageView entityImage;


    //getters and setters
    public int getNumberOfcandidateEntities() {
        return numberOfcandidateEntities;
    }

    public void setNumberOfcandidateEntities(int numberOfcandidateEntities) {
        GuessMaster.numberOfcandidateEntities = numberOfcandidateEntities;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }


    //helper function to deal with calculating and printing tickes
    private static void calculateTickets(Entity entity, GuessMaster theGame) {
        theGame.score += entity.getAwardedTicketNumber();
        System.out.println("You earned " + entity.getAwardedTicketNumber() + " Tickets\nYour score is :" + theGame.score);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initGame(){
        activeEntity = choseRandEntity();
        entityName.setText(activeEntity.getName());
        setEntityImage();

        createAlertDialog("GuessMaster V3", "Welcome to Benjamin Kitor's Guessmaster", "Play gmae", "Game is Starting").show();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeEntity(){
        activeEntity = choseRandEntity();
        setEntityImage();
        entityName.setText(activeEntity.getName());
        userIn.getText().clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makeGuess(){
        Editable guess = userIn.getText();
        Date guessDate;
        try {
            guessDate = new Date(guess.toString());

            if(guessDate.equals(activeEntity.getBorn())){
                String toastMsg = activeEntity.getAwardedTicketNumber()+" tickets earned!";
                createAlertDialog("Congradulations", "You Corectly guessed the person", "Keep playing", toastMsg).show();

                updateScore(activeEntity);
                changeEntity();
            }else{
                String hintStr = guessDate.precedes(activeEntity.getBorn()) ? " later" : "n earlier";
                String message = "Not quite try a" + hintStr + " date";
                createAlertDialog("Wrong answer", message, "Try again", "").show();
            }
        }catch (BadDateInputExeption e){
            createAlertDialog("Bad Date", "The date you entered wasn't correctly formated, please input a date in the format of MM/DD/YYYY.", "Try again", "").show();
        }
        guess.clear();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_master);

        this.fillEntityList();
        ticketSum = findViewById(R.id.ticket);
        entityImage = findViewById(R.id.entityImage);
        entityName = findViewById(R.id.entityName);
        userIn = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.btnGuess);
        buttonClearContent = findViewById(R.id.btnClear);
        score = 0;
        ticketSum.setText("Current Ticket Score is 0");

        buttonClearContent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                makeGuess();
            }
        });

        this.initGame();
    }

    //updates the ticket score
    private void updateScore(Entity ent){
        this.score+=ent.getAwardedTicketNumber();
        ticketSum.setText("Current Ticket Score is "+this.score);
    }

    //sets the entityImage imageView with the activeEntities image
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEntityImage(){
        Drawable im;
        switch (activeEntity.getName()){
            case "Justin Trudeau":
                im = getResources().getDrawable(R.mipmap.justin);
                break;
            case"Celine Dion":
                im = getResources().getDrawable(R.mipmap.celine);
                break;
            case "United States of America":
                im = getResources().getDrawable(R.mipmap.usa);
                break;
            case "My Creator":
                im = getResources().getDrawable(R.mipmap.thecreator);
            break;
            default:
                im = getResources().getDrawable(R.mipmap.image_error);
            break;
        }
        entityImage.setForeground(im);

    }

    //helper Function to chose random entity
    private Entity choseRandEntity(){
        return this.entities[(int)(Math.random()*this.entities.length)];
    }

    //called during onCreate to fill Entities[]
    private void fillEntityList() {
        this.score = 0;
        this.entities = new Entity[4];
        numberOfcandidateEntities = entities.length;
        try {
            this.entities[0] = new Politician(new Date(12, 25, 1971), "Justin Trudeau", 0.25, "Male", "Liberal");
            this.entities[1] = new Singer(new Date(3, 30, 1968), "Celine Dion", 0.5, "Female", "La Voi du Bon Dieu", new Date("November", 6, 1981));
            this.entities[2] = new Country(new Date(7, 4, 1776), "United States of America", 0.1, "Washington D.C.");
            this.entities[3] = new Person(new Date(9, 1, 2000), "My Creator", 1.0, "Female");
        } catch (Exception e) {
            System.out.println("Exeption caught in fillEntityList()");
            System.out.println(e);
            System.exit(0);
        }
    }

    //code that returns an alert dialog, cause creating alert dialogs is verbose
    //leaving the toast field as an empty string will not send a toast
    private AlertDialog createAlertDialog(String title, String message, String button, final String toast){
        AlertDialog.Builder alert = new AlertDialog.Builder(GuessMaster.this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);
        alert.setNegativeButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!toast.equals("")){
                    Toast.makeText(getBaseContext(), toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return alert.create();

    }

}

package com.manzanart.albick.taskmanager;

import android.graphics.Color;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RoxasIsPoor on 04/12/2017.
 */


public class Task implements  Serializable{
    public int getId() {
        return id;
    }

    private int id;
    private ArrayList<Task> subTasks;
    private String name;
    private Date startingDate;
    private Date endingDate;
    private int color;

    private String description;
    private boolean displayed;


    public ArrayList<NotififRules> getRules() {
        return rules;
    }

    public void setRules(ArrayList<NotififRules> rules) {
        this.rules = rules;
    }

    private ArrayList<NotififRules> rules;

    public Task(ArrayList<Task> subTasks, String name,String description, Date startingDate, Date endingDate, int color,ArrayList<NotififRules> rules) {
        this.id=hashCode();
        this.subTasks = subTasks;
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        //Log.d("Ending date", endingDate.toString());
        this.color= color;
        this.rules=rules;
        this.description = description;
        this.displayed=false;
    }

    public ArrayList<Task> getSubTasks() {
        return subTasks;

    }


    public void setSubTasks(ArrayList<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setDescription(String description) { this.description = description; }

    public boolean isDisplayed(){return this.displayed;}

    public void switchDisplay(){
        if (this.displayed) { this.displayed = false;}
        else {this.displayed = true;}
    }

    public String getDescription() { return description; }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    // Return a String corresponding to the time left, it adapt if there is function of the span left
    public String getTimeLeft(){
        long today = System.currentTimeMillis();
        if ( this.getEndingDate() != null) {
            long timestamp = this.getEndingDate().getTime();
            int left = (int) (timestamp - today);
            Log.d("Timestamp", Integer.toString((int) timestamp));
            Log.d("today", Integer.toString((int) today));
            Log.d("Left", Integer.toString(left));
            if (Math.abs(left) >= (1000 * 3600 * 24)) {
                int daysLeft =  left / (1000 * 3600 * 24);
                return (Integer.toString(daysLeft) + " days left !");
            } else if (Math.abs(left) >= (1000 * 3600)) {
                int hoursLeft = left / (1000 * 3600 * 24);
                return (Integer.toString(hoursLeft) + " hours left !");
            }
            int minutesLeft = left / (60 * 1000);
            return (Integer.toString(minutesLeft) + " minutes left !");
        }
        return ("Waiting");
    }


}

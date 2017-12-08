package com.manzanart.albick.taskmanager;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RoxasIsPoor on 04/12/2017.
 */


public class Task implements  Serializable{
    public Task(ArrayList<Task> subTasks, String name,String description, Date startingDate, Date endingDate, int color) {

        this.subTasks = subTasks;
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.color= color;

        this.description = description;
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

    public String getDescription() { return description; }

    private ArrayList<Task> subTasks;
    private String name;
    private Date startingDate;
    private Date endingDate;
    private int color;

    private String description;

    private ArrayList<NotififRules> rules;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}

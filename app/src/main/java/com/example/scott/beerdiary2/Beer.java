package com.example.scott.beerdiary2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by Scott on 5/25/17.
 */

public class Beer {
    private String name;
    private String date;
    private int style;
    private String hopName;
    private String hopAmount;
    private ArrayList<Hops> bitteringHops;
    private ArrayList<Hops> flavorHops;
    private ArrayList<Hops> aromaHops;
    private ArrayList<Hops> knockOutHops;
    private ArrayList<Hops> dryHops;
    private String preboilGravity;
    private String originalGravity;
    private String finalGravity;
    private String ABV = "";
    private String notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getHopName() {
        return hopName;
    }

    public void setHopName(String hopName) {
        this.hopName = hopName;
    }

    public String getHopAmount() {
        return hopAmount;
    }

    public void setHopAmount(String hopAmount) {
        this.hopAmount = hopAmount;
    }

    public ArrayList<Hops> getBitteringHops() {
        return bitteringHops;
    }

    public void setBitteringHops(Hops hops) {
        this.bitteringHops.add(hops);
    }

    public ArrayList<Hops> getFlavorHops() {
        return flavorHops;
    }

    public void setFlavorHops(Hops hops) {
        this.flavorHops.add(hops);
    }

    public ArrayList<Hops> getAromaHops() {
        return aromaHops;
    }

    public void setAromaHops(Hops hops) {
        this.aromaHops.add(hops);
    }

    public ArrayList<Hops> getKnockOutHops() {
        return knockOutHops;
    }

    public void setKnockOutHops(Hops hops) {
        this.knockOutHops.add(hops);
    }

    public ArrayList<Hops> getDryHops() {
        return dryHops;
    }

    public void setDryHops(Hops hops) {
        this.dryHops.add(hops);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPreboilGravity() {
        return preboilGravity;
    }

    public void setPreboilGravity(String preboilGravity) {
        this.preboilGravity = preboilGravity;
    }

    public String getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(String originalGravity) {
        this.originalGravity = originalGravity;
    }

    public String getFinalGravity() {
        return finalGravity;
    }

    public void setFinalGravity(String finalGravity) {
        this.finalGravity = finalGravity;
    }
    public String getABV() {
        return ABV;
    }

    public void calculateABV() {
        if(!"".equals(originalGravity) && !"".equals(finalGravity)) {
            double tempABV = (Double.parseDouble(originalGravity) - Double.parseDouble(finalGravity)) * 131.25;
            DecimalFormat df = new DecimalFormat("#.00");
            ABV = df.format(tempABV);
        }
    }

    public void calculateABV(String originalGravity, String finalGravity) {
        double tempABV = (Double.parseDouble(originalGravity) - Double.parseDouble(finalGravity)) * 131.25;
        DecimalFormat df = new DecimalFormat("#.00");
        ABV = df.format(tempABV);
    }
}

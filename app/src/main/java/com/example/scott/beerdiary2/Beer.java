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
    private String brewDate;
    private String secondaryFermentationDate;
    private String bottlingOrKeggingDate;
    private int style;
    private ArrayList<Hops> bitteringHops = new ArrayList<>();
    private ArrayList<Hops> flavorHops = new ArrayList<>();
    private ArrayList<Hops> aromaHops = new ArrayList<>();
    private ArrayList<Hops> knockOutHops = new ArrayList<>();
    private ArrayList<Hops> dryHops = new ArrayList<>();
    private String preboilGravity;
    private String originalGravity;
    private String finalGravity;
    private String ABV = "";
    private String notes;
    public enum HopsType {
        BITTERING, FLAVOR, AROMA, KNOCKOUT, DRY
    }
    public enum dateType {
        BREW, SECONDARY, BOTTINGKEGGING
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewDate() {
        return brewDate;
    }

    public void setBrewDate(String date) {
        this.brewDate = date;
    }

    public String getSecondaryFermentationDate() {
        return secondaryFermentationDate;
    }

    public void setSecondaryFermentationDate(String secondaryFermentationDate) {
        this.secondaryFermentationDate = secondaryFermentationDate;
    }

    public String getBottlingOrKeggingDate() {
        return bottlingOrKeggingDate;
    }

    public void setBottlingOrKeggingDate(String bottlingOrKeggingDate) {
        this.bottlingOrKeggingDate = bottlingOrKeggingDate;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public void setHops(HopsType hopsType, Hops hops) {
        switch(hopsType) {
            case BITTERING:
                this.bitteringHops.add(hops);
                break;
            case AROMA:
                this.aromaHops.add(hops);
                break;
            case FLAVOR:
                this.flavorHops.add(hops);
                break;
            case KNOCKOUT:
                this.knockOutHops.add(hops);
                break;
            case DRY:
                this.dryHops.add(hops);
                break;
        }
    }

    public ArrayList<Hops> getBitteringHops() {
        return bitteringHops;
    }

    public ArrayList<Hops> getFlavorHops() {
        return flavorHops;
    }

    public ArrayList<Hops> getAromaHops() {
        return aromaHops;
    }

    public ArrayList<Hops> getKnockOutHops() {
        return knockOutHops;
    }

    public ArrayList<Hops> getDryHops() {
        return dryHops;
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

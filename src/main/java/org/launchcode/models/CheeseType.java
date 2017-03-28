package org.launchcode.models;

/**
 * Created by jeannie on 3/26/17.
 */
public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    CheeseType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

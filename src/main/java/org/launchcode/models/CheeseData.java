package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by jeannie on 3/25/17.
 */
public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    // get all cheeses
    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }

    // add cheese
    public static void add (Cheese newCheese) {
        cheeses.add(newCheese);
    }

    // remove cheese
    public static void remove(int id) {
        Cheese cheeseToRemove = getbyId(id);
        cheeses.remove(cheeseToRemove);
    }

    // get cheese from id
    public static Cheese getbyId(int id) {
        Cheese theCheese = null;
            for(Cheese candidateCheese: cheeses) {
                if(candidateCheese.getCheeseId() == id){
                    theCheese = candidateCheese;
                }
            }
        return theCheese;
    }

}

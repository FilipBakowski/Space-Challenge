package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    private int totalCost;

    public Simulation() {
        totalCost = 0;
    }

    public int getTotalCost() {
        return totalCost;
    }

    /**
     * loadItems - Loading items for each phase
     * @param resourceFile - source file with items for each phase (source file supposed to be placed in parent directory.)
     * @return list of loaded items
     */
    public ArrayList<Item> loadItems(String resourceFile){
        ArrayList<Item> items = new ArrayList<>();
        try{
            File parentDir = new File(".").getParentFile();
            File moviesListFile = new File(parentDir, resourceFile);
            Scanner fileInput = new Scanner(moviesListFile);
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] item = line.split("=");
                if(item.length!=2){
                    System.out.println("Item definition is incorrect." );
                    continue;
                }
                try {
                    int weight = Integer.parseInt(item[1]);
                    items.add(new Item(item[0], weight));
                }
                catch (NumberFormatException e) {
                    System.out.println("Item's (" + item[0] + ") weight is incorrect." );
                }
            }
            items.sort((Item i1, Item i2) ->{
                if(i1.getWeight() < i2.getWeight())
                    return 1;
                else if (i1.getWeight() > i2.getWeight())
                    return -1;
                return 0;
            });
            fileInput.close();
        }catch (FileNotFoundException e){
            System.out.println("File with movies not found.");
        }
        return items;
    }

    /**
     * loadU1 creates fleet of U1 rockets, which will carry defined cargo
     * @param items - cargo for fleet
     * @return fleet of rockets
     */
    public ArrayList<Rocket> loadU1(ArrayList<Item> items){
        ArrayList<Rocket> rockets = new ArrayList<>();
        while(!items.isEmpty()) {
            U1 rocket = new U1();
            rockets.add(rocket);
            for (int i=0; i< items.size();i++) {
                if(rocket.canCarry(items.get(i))){
                    rocket.carry(items.get(i));
                    items.remove(items.get(i));
                    i--;
                }
            }
        }
        return rockets;
    }

    /**
     * loadU2 creates fleet of U2 rockets, which will carry defined cargo
     * @param items - cargo for fleet
     * @return fleet of rockets
     */
    public ArrayList<Rocket> loadU2(ArrayList<Item> items){
        ArrayList<Rocket> rockets = new ArrayList<>();
        while(!items.isEmpty()) {
            U2 rocket = new U2();
            rockets.add(rocket);
            for (int i=0; i< items.size();i++) {
                if(rocket.canCarry(items.get(i))){
                    rocket.carry(items.get(i));
                    items.remove(items.get(i));
                    i--;
                }
            }
        }
        return rockets;
    }

    /**
     * runSimulation executes simulation for defined fleet of rockets
     * @param rockets - list of rockets
     */
    public void runSimulation(ArrayList<Rocket> rockets){
        for (Rocket rocket : rockets) {
            boolean launchedSuccessfully = false;
            boolean landedSuccessfully = false;
            while(!(landedSuccessfully == true && launchedSuccessfully == true)){
                launchedSuccessfully = rocket.launch();
                if(launchedSuccessfully)
                    landedSuccessfully = rocket.land();
                totalCost += rocket.getCost();
            }
        }
    }
}

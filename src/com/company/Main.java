package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        processSimulation("U1");
        processSimulation("U2");
    }

    public static void processSimulation(String rocketType){
        Simulation simulation1 = new Simulation();
        ArrayList<Item> items1 = simulation1.loadItems("phase-1.txt");
        ArrayList<Item> items2 = simulation1.loadItems("phase-2.txt");
        ArrayList<Rocket> rockets;
        if(rocketType.equals("U1")) {
            rockets = simulation1.loadU1(items1);
            rockets.addAll(simulation1.loadU1(items2));
        }
        else if(rocketType.equals("U2")) {
            rockets = simulation1.loadU2(items1);
            rockets.addAll(simulation1.loadU2(items2));
        }
        else{
            return;
        }
        simulation1.runSimulation(rockets);
        System.out.println("Total simulation (" + rocketType + ") cost is $" + simulation1.getTotalCost() + ".");
    }
}

package com.company;

public abstract class Rocket implements Spaceship{

    private int weight;
    private int maxTotalWeight;
    private int cost;

    public Rocket(int weight, int maxTotalWeight, int cost){
        this.weight = weight;
        this.maxTotalWeight = maxTotalWeight;
        this.cost = cost;
    }
    
    public int getCost() {
        return cost;
    }

    @Override
    public abstract boolean launch();

    @Override
    public abstract boolean land();

    public double countChance(int chance){
        double result = (double)chance * ( weight /  maxTotalWeight);
        return result;
    }

    @Override
    public boolean canCarry(Item item) {
        if(maxTotalWeight < item.getWeight() + weight)
            return false;
        return true;
    }

    @Override
    public void carry(Item item) {
        if(maxTotalWeight >= item.getWeight() + weight)
            weight += item.getWeight();
    }


}

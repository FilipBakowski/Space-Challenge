package com.company;

public class U1 extends Rocket{

    public U1() {
        super(10000, 18000, 100000000);
    }

    @Override
    public boolean launch() {
        if(countChance(5) <= Math.random()*100)
            return true;
        return false;
    }

    @Override
    public boolean land() {
        if(countChance(1) <= Math.random()*100)
            return true;
        return false;
    }
}

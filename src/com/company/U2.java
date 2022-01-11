package com.company;

public class U2 extends Rocket{
    public U2() {
        super(18000, 29000, 120000000);
    }

    @Override
    public boolean launch() {
        if(countChance(4) <= Math.random()*100)
            return true;
        return false;
    }

    @Override
    public boolean land() {
        if(countChance(8) <= Math.random()*100)
            return true;
        return false;
    }
}

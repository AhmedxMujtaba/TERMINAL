package com.ahmedxmujtaba.Backend.Entities;

import java.io.Serializable;

public class Gems implements Serializable {

    int amount;
    public Gems (int amount){

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

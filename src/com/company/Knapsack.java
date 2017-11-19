package com.company;

import java.util.Random;

/**
 * Created by scottjones on 11/13/17.
 */
public class Knapsack {

    private int numOfItems;
    private Item[] items;
    private int maxWeight;
    private int maxValue = 20;

    public Knapsack(int itemCount, int maximumWeight) {
        Random rand = new Random();
        numOfItems = itemCount;
        maxWeight = maximumWeight;
        /*
        items =new Item[4];
        items[0]=new Item('A', 45, 3);
        items[1] = new Item('B', 40, 5);
        items[2] = new Item('C', 50, 8);
        items[3] = new Item('D', 90, 100);
        */
        items = new Item[numOfItems];
        for (int i = 0; i< numOfItems; ++i) {
            items[i] = new Item(rand.nextInt(maxValue), rand.nextInt(maxValue));
        }
    }

    public int getNumOfItems(){
        return numOfItems;
    }

    public Item getItem(int index){
        return items[index];
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}

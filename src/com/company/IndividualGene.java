package com.company;

import java.util.BitSet;

/**
 * Created by scottjones on 11/13/17.
 */
public class IndividualGene {

    public BitSet bitVector;
    public int value;
    private Knapsack knapsack;

    public IndividualGene(Knapsack thisKnapsack){
        knapsack = thisKnapsack;
    }

    public IndividualGene(IndividualGene geneToCopy){
        knapsack = geneToCopy.knapsack;
        bitVector = new BitSet();
        bitVector.or(geneToCopy.bitVector);
        value = geneToCopy.value;
    }

    public int recalculateValue(){
        value = 0;
        for(int i = 0; i < this.knapsack.getNumOfItems(); ++i){
            if (this.bitVector.get(i)){
                value += knapsack.getItem(i).value;
            }
        }
        int totalWeight = this.totalWeight();
        if(totalWeight > knapsack.getMaxWeight()){
            value =  -totalWeight;
        }
        return value;
    }

    public void print(){
        System.out.println("Item Indexes: " + this.bitVector + "  \t\tTotal Value: " + this.value + " \tTotal Weight: " + this.totalWeight());
    }

    private int totalWeight() {
        int totalWeight = 0;
        int numOfItems = knapsack.getNumOfItems();
        for(int i = 0; i < numOfItems; ++i) {
            if (this.bitVector.get(i)){
                totalWeight += knapsack.getItem(i).weight;
            }
        }
        return totalWeight;
    }
}

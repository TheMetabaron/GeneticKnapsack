package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        KnapsackPacker knapsackPacker = new KnapsackPacker();
        knapsackPacker.geneticAlgorithm(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        knapsackPacker.result();
    }
}

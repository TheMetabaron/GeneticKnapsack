package com.company;

import java.util.*;

/**
 * Created by scottjones on 11/13/17.
 */
public class KnapsackPacker {

    static private int populationSize;
    static private int numIterations;
    static private int mutationPercentage = 10;  // Should be value 0 to 100
    static private int maxWeight = 100;
    static private int numOfItems = 20;
    List<IndividualGene> population;

    private Knapsack knapsack;

    private static class IndividualGeneComparator implements Comparator<IndividualGene>{

        @Override
        public int compare(IndividualGene o1, IndividualGene o2) {
            o1.recalculateValue();
            o2.recalculateValue();
            return o1.value - o2.value;
        }
    }


    public KnapsackPacker(){
        knapsack = new Knapsack(numOfItems, maxWeight);

    }

    public String geneticAlgorithm(int numIterationsParam, int populationSizeParam){
        String result = "?";
        Random rand = new Random();
        IndividualGeneComparator geneComparator = new IndividualGeneComparator();

        numIterations = numIterationsParam;
        populationSize = populationSizeParam;
        population = new ArrayList<>(populationSize);
        for(int i=0; i<populationSize; ++i) {
            population.add(createPopulation(knapsack));
        }

        for(int i = 0; i < numIterations; ++i){
            //select random pair to breed
            IndividualGene child1 = new IndividualGene(population.get(rand.nextInt(populationSize)));
            IndividualGene child2 = new IndividualGene(population.get(rand.nextInt(populationSize)));

            //pick random spot for crossover and breed two new children
            BitSet tempBitVector = new BitSet(numOfItems);
            int crossover = rand.nextInt(numOfItems);
            for (int j = crossover; j < numOfItems; ++j){
                tempBitVector.set(j, child1.bitVector.get(j));
                child1.bitVector.set(j, child2.bitVector.get(j));
                child2.bitVector.set(j, tempBitVector.get(j));
            }

            //randomly decide to mutate based on MutatePct and if so mutate one gene
            //TODO: Eliminate Incest?
            if(rand.nextInt(100) < mutationPercentage){
                child1.bitVector.flip(rand.nextInt(numOfItems));
            }
            child1.recalculateValue();
            child2.recalculateValue();
            population.add(child1);
            population.add(child2);

            //kill off two weakest members of the population
            population.sort(geneComparator);

            ListIterator<IndividualGene> iterator = population.listIterator();

            //remove first two in sorted list
            population.remove(1);
            population.remove(0);
//DEBUG
            iterator = population.listIterator();
            System.out.println("\nIteration " + i + " list of " + population.size() + " genes:");
            while(iterator.hasNext()) {
                iterator.next().print();
            }

            child1 = null;
            child2 = null;
        }

        /*   DEBUG OF BIT VECTORS
        ListIterator<BitSet> iterator = population.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        BitSet test = new BitSet(4);
        System.out.println("Test: " + test);
        test.set(0, true);
        System.out.println("Test: " + test);
        test.set(3, true);
        System.out.println("Test: " + test);
        */
        return result;
    }

    private IndividualGene createPopulation(Knapsack knapsack) {
        Random random = new Random();
        IndividualGene aGene = new IndividualGene(knapsack);
        aGene.bitVector = new BitSet(numOfItems);
        for(int i = 0; i < numOfItems; ++i) {
            aGene.bitVector.set(i, random.nextBoolean());
        }
        aGene.recalculateValue();
        return aGene;
    }
}

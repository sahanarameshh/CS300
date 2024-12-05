//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Partition Calculator
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Aman Jain (Peer Mentor) helped with working through applying numOfPartitions logic to calculatePartitions method
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class models a calculator for various problems relating to the partitions of N
 */
public class PartitionCalculator {
    public PartitionCalculator() {};

    /**
     * Given a value N, finds the total number of partitions for that value N.
     * 
     * @param N value to find total number of partitions of
     * @return number of partitions of N
     */
    public static int numOfPartitions(int N) {
        return numOfPartitionsHelper(N, N);
    }

    /**
     * Helper method for PartitionCalculator 
     * 
     * @param N value to find total number of partitions of
     * @param maxNum maximum value in the current partition
     * @return number of partitions of N
     */
    private static int numOfPartitionsHelper(int N, int maxNum) {
        if (N == 0) {
            return 1; // base case
        } else {
            if (N < 0 || maxNum == 0) {
                return 0;
            }
            return numOfPartitionsHelper(N - maxNum, maxNum) + numOfPartitionsHelper(N, maxNum - 1); // recursive case
        }
    }
    
    /**
     * Given a value N, finds all of the partitions for that value N.
     * 
     * @param N value to find all of partitions of
     * @return all of partitions of N
     */
    public static ArrayList<Partition> calculatePartitions(int N) {
        ArrayList<Partition> list = new ArrayList<Partition>();
        Partition p = new Partition(N);
        calculatePartitionsHelper(list, p, N, N);
        return list;
    }

    /**
     * Helper method for calculatePartitions 
     * 
     * @param list ArrayList that holds identified partitions
     * @param p empty Partition object to hold identified partitions
     * @param N value to find total number of partitions of
     * @param maxNum maximum value in the current partition
     */
    private static void calculatePartitionsHelper(ArrayList<Partition>list, Partition p, int N, int maxNum) {
        if (N == 0) { // base case
            list.add(p.copyOf());
            return;
        }
        
        if (N < 0 || maxNum == 0) {
            return;
        }
        
        p.addNumber(maxNum); // add N without recursing through it
        calculatePartitionsHelper(list, p, N - maxNum, maxNum); // recursive case
        p.removeLast(); // "restarts" back at the original N
        calculatePartitionsHelper(list, p, N, maxNum - 1); // recursive case
    }

    /**
     * Given a set of all partitions for value N, finds all permutations of each partition given.
     *
     * @param partitions a set of all partitions for value N
     * @return all permutations of each partition given
     */
    public static ArrayList<Partition> calculateAllPermutations(ArrayList<Partition> partitions) {
        ArrayList<Partition> list = new ArrayList<>();

        for (Partition partition : partitions) {
            ArrayList<Integer> fixed = new ArrayList<>();
            ArrayList<Integer> toPermute = partition.getAllNums();
            int N = partition.getN();

            calculateAllPermutationsHelper(list, fixed, toPermute, N);
        }

        return list;
    }

    /**
     * Helper method for calculateAllPermutations 
     * 
     * @param list ArrayList that holds identified partitions
     * @param fixed ArrayList that holds integers in partitions
     * @param toPermute ArrayList that holds integers in partitions
     * @param N value to find total number of partitions of
     */
    private static void calculateAllPermutationsHelper(ArrayList<Partition> list, ArrayList<Integer> fixed, ArrayList<Integer> toPermute, int N) {
        if (toPermute.isEmpty()) {
            Partition newPartition = new Partition(N);
            for (int i : fixed) {
                newPartition.addNumber(i);
            }

            if (!list.contains(newPartition)) {
                list.add(newPartition);
            }

            return;
        }

        for (int i = 0; i < toPermute.size(); i++) {
            ArrayList<Integer> newFixed = new ArrayList<>(fixed);
            newFixed.add(toPermute.get(i));

            ArrayList<Integer> newPermute = new ArrayList<>(toPermute);
            newPermute.remove(i);

            calculateAllPermutationsHelper(list, newFixed, newPermute, N);
        }
    }
}
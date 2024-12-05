//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Partitions Calculator Tester =
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class tests the PartitionCalculator class
 */
public class PartitionCalculatorTester {

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsBase() {
    int result1 = PartitionCalculator.numOfPartitions(1);
    int expected1 = TesterUtility.getPartitionCount(1);

    int result2 = PartitionCalculator.numOfPartitions(2);
    int expected2 = TesterUtility.getPartitionCount(2);

    return (result1 == expected1) && (result2 == expected2);
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for recursive cases with
   * n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsRecursive() {
    int result1 = PartitionCalculator.numOfPartitions(3);
    int expected1 = TesterUtility.getPartitionCount(3);

    int result2 = PartitionCalculator.numOfPartitions(4);
    int expected2 = TesterUtility.getPartitionCount(4);

    int result3 = PartitionCalculator.numOfPartitions(5);
    int expected3 = TesterUtility.getPartitionCount(5);

    return (result1 == expected1) && (result2 == expected2) && (result3 == expected3);
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected 
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsFuzz() {
    int result = 0, expected = 0;
    int randInt = (int) (Math.random() * 199) + 100;
    for (int i = 0; i < randInt; i++) {
      int N = (int) (Math.random() * 50) + 1;
      result = PartitionCalculator.numOfPartitions(N);
      expected = TesterUtility.getPartitionCount(N);
      if (result != expected) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsBase() {
    ArrayList<Partition> result1 = PartitionCalculator.calculatePartitions(1);
    ArrayList<Partition> expected1 = TesterUtility.getPartitions(1, false);

    ArrayList<Partition> result2 = PartitionCalculator.calculatePartitions(2);
    ArrayList<Partition> expected2 = TesterUtility.getPartitions(2, false);

    return (result1.equals(expected1)) && (result2.equals(expected2));
  }


  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for recursive cases with
   * n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsRecursive() {
    ArrayList<Partition> result1 = PartitionCalculator.calculatePartitions(3);
    ArrayList<Partition> expected1 = TesterUtility.getPartitions(3, false);

    ArrayList<Partition> result2 = PartitionCalculator.calculatePartitions(4);
    ArrayList<Partition> expected2 = TesterUtility.getPartitions(4, false);

    ArrayList<Partition> result3 = PartitionCalculator.calculatePartitions(5);
    ArrayList<Partition> expected3 = TesterUtility.getPartitions(5, false);

    return (result1.equals(expected1)) && (result2.equals(expected2)) && (result3.equals(expected3));
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsFuzz() {
    int randInt = (int) (Math.random() * 20) + 1;
    for (int i = 0; i < randInt; i++) {
      int N = (int) (Math.random() * 35) + 1;
      ArrayList<Partition> result = PartitionCalculator.calculatePartitions(N);
      ArrayList<Partition> expected = TesterUtility.getPartitions(N, false);
      if (!result.equals(expected)) {
        return false;
      }
    }
    return true;
  }

  public static boolean testCalculateAllPermutationsBase() {
    ArrayList<Partition> result1 = PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(1));
    ArrayList<Partition> expected1 = TesterUtility.getPartitions(1, true);

    ArrayList<Partition> result2 = PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(2));
    ArrayList<Partition> expected2 = TesterUtility.getPartitions(2, true);
    
    return (TesterUtility.comparePartitionLists(result1, expected1, true)) && (TesterUtility.comparePartitionLists(result2, expected2, true));
  }

  public static boolean testCalculateAllPermutationsRecursive() {
    ArrayList<Partition> result1 = PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(3));
    ArrayList<Partition> expected1 = TesterUtility.getPartitions(3, true);

    ArrayList<Partition> result2 = PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(4));
    ArrayList<Partition> expected2 = TesterUtility.getPartitions(4, true);

    ArrayList<Partition> result3 = PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(5));
    ArrayList<Partition> expected3 = TesterUtility.getPartitions(5, true);
    
    return (TesterUtility.comparePartitionLists(result1, expected1, true)) && (TesterUtility.comparePartitionLists(result2, expected2, true)) && (TesterUtility.comparePartitionLists(result3, expected3, true));
  }

  /**
   * Runs and outputs the results of all tester methods.
   * @return true if all tests return true, false otherwise
   * @author Michelle Jensen
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));
    
    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));
    
    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests()? "PASS" : "FAIL"));
  }

}

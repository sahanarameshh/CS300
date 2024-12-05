//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Loop Station Tester
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Anil Duvvuri
// Partner Email:   aduvvuri@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class tests the LoopStation class, and by extension, the Track class
 */
public class LoopStationTester {
  
  /**
   * Checks the correctness of the createPod() method. This method should:
   * - create a Pod with the given capacity and podClass
   * - add it to the correct end of the correct Track in the LoopStation
   * - return a reference (shallow copy) to that Pod
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    LoopStation l = new LoopStation();
    l.createPod(10, true);
    l.createPod(20, true);
    try {
      if (l.waitingFirst.get(0).getCapacity() != 20) {
        return false;
      }
    } catch (MalfunctioningPodException m) {
      ;
    }
    l.createPod(10, false);
    l.createPod(20, false);
    try {
      if (l.waitingEconomy.get(0).getCapacity() != 10) {
        return false;
      }
    } catch (MalfunctioningPodException m) {
      ;
    }
    return true;
  }
  
  /**
   * Checks the correctness of the launchPod() method. This method should:
   * - TODO!!! throw a NoSuchElementException if no pods are waiting to launch
   * - launch first class pods from the END of the waitingFirst track
   * - launch economy class pods from the BEGINNING of the waitingEconomy track
   * - launch ALL first class pods before launching ANY economy class pods
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if launchPod() is functioning correctly, false otherwise
   */

   public static boolean testLaunchPod() {
    LoopStation l = new LoopStation();
    l.waitingFirst.add(new Pod(10, 0));
    l.waitingFirst.add(new Pod(20, 0));

    try {
        l.launchPod();
        if (l.launched.get(0).getCapacity() != 10) {
            return false;
        }

        l.launchPod();
        if (l.launched.get(0).getCapacity() != 20) {
            return false;
        }
    } catch (MalfunctioningPodException | NoSuchElementException e) {
        return false;
    }

    l.waitingEconomy.add(new Pod(30, 1));
    l.waitingEconomy.add(new Pod(40, 1));

    try {
        l.launchPod();
        if (l.launched.get(2).getCapacity() != 30) {
            return false;
        }

        l.launchPod();
        if (l.launched.get(3).getCapacity() != 40) {
            return false;
        }
    } catch (MalfunctioningPodException | NoSuchElementException e) {
        return false;
    }

    try {
        l.launchPod();
        return false;
    } catch (NoSuchElementException e) {
      ;
    }

    return true; 
}
  
  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should:
   * - repeatedly check the launched track for malfunctioning pods
   * - remove those pods correctly
   * - report the number of pods it removed once there are no longer any malfunctioning pods
   * 
   * Things to consider when you are testing:
   * 
   * - there is a protected setNonFunctional() method you may use for testing purposes to ensure
   *   that at least one pod is non-functional
   *   
   * - calling isFunctional() on a Pod may cause it to malfunction! You should come up with an
   *   alternate way to check whether a Pod is functional, if you have not already.
   *   
   * - verify that the difference in number of pods from before the method was called and after
   *   the method was called is equal to the number that it reported
   *   
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */

   public static boolean testClearMalfunctioning() {
    LoopStation l = new LoopStation();

    l.waitingFirst.add(new Pod(10, 0));
    l.waitingFirst.add(new Pod(20, 0));
    l.launchPod();
    l.launchPod();
    l.launched.get(0).setNonFunctional();
    l.launched.get(1).setNonFunctional();

    int result = l.clearMalfunctioning();
    int expected = 2;
    
    if (result != expected || l.getNumLaunched() != 0) {
      return false;
    }

    return l.clearMalfunctioning() == 0;
}

  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {
    LoopStation l = new LoopStation();

    // getNumWiting()
    l.waitingFirst.add(new Pod(10, 0));
    l.waitingFirst.add(new Pod(20, 0));
    l.waitingEconomy.add(new Pod(10, 0));
    l.waitingEconomy.add(new Pod(20, 0));
    int result = l.getNumWaiting();
    int expected = 4;
    if (result != expected) {
      return false;
    }

    // getNumLaunched()
    l.launchPod();
    l.launchPod();
    result = l.getNumLaunched();
    expected = 2;
    if (result != expected) {
      return false;
    }

    l.launchPod();
    l.launchPod();
    // getNumPassengers()
    l.waitingFirst.add(new Pod(10, 0));
    l.waitingFirst.addPassenger("Harry", true);
    l.waitingFirst.addPassenger("Ron", true);
    l.waitingFirst.addPassenger("Hermione", true);
    result = l.getNumPassengers();
    expected = 3;
    if (result != expected) {
      return false;
    }

    l.waitingEconomy.add(new Pod(10, 1));
    l.waitingEconomy.addPassenger("Draco", false);
    l.waitingEconomy.addPassenger("Crabbe", false);
    l.waitingEconomy.addPassenger("Goyle", false);
    result = l.getNumPassengers();
    expected = 6;
    if (result != expected) {
      return false;
    }

    l.launchPod();
    l.launchPod();
    result = l.getNumPassengers();
    expected = 6;
    if (result != expected) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: "+(test1?"PASS":"fail"));
    
    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: "+(test2?"PASS":"fail"));
    
    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: "+(test3?"PASS":"fail"));
    
    boolean test4 = testGetNums();
    System.out.println("testGetNums: "+(test4?"PASS":"fail"));
    
    System.out.println("ALL TESTS: "+((test1&&test2&&test3&&test4)?"PASS":"fail"));
  }

}

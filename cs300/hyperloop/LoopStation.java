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
 * This class models a LoopStation, which manages three tracks:
 * waitingFirst - newly created first-class Pods are added here, ordered from most recently-created to least recently-created
 * waitingEconomy - newly created economy-class Pods are added here, ordered from least recently-created to most recently-created
 * launched - both first- and economy-class Pods which have been launched into the Hyperloop
 * Launching a Pod is done in order of creation, with first-class Pods launching before economy-class Pods.
 */
public class LoopStation {
    protected Track launched;
    protected Track waitingEconomy;
    protected Track waitingFirst;

    /**
     * Creates a new LoopStation with empty tracks
     */
    public LoopStation() {
        launched = new Track();
        waitingEconomy = new Track();
        waitingFirst = new Track();
    }

    /**
     * Creates a new Pod of the appropriate class and loads it onto the correct waiting track.
     * 
     * @param capacity the capacity of the new Pod to create
     * @param isFirstClass whether the new Pod is a first class Pod
     * @return a reference to the newly-created Pod
     */
    public Pod createPod(int capacity, boolean isFirstClass) {
        Pod p;
        if (isFirstClass) {
            p = new Pod(capacity, 0);
            waitingFirst.add(p);
        }
        else {
            p = new Pod(capacity, 1);
            waitingEconomy.add(p);
        }
        return p;
    }

    /**
     * Launches the highest-priority, least-recently-created Pod that is currently waiting
     * 
     * @throws NoSuchElementException if no Pods are waiting to launch
     */
    public void launchPod() {
        if (waitingEconomy.isEmpty() && waitingFirst.isEmpty()) {
            throw new NoSuchElementException("No pods waiting to launch");
        }

        if (!waitingFirst.isEmpty()) {
            launched.add(waitingFirst.get(waitingFirst.size() - 1));
            waitingFirst.remove(waitingFirst.size() - 1);
        }
        else if (!waitingEconomy.isEmpty()) {
            launched.add(waitingEconomy.get(0));
            waitingEconomy.remove(0);
        }
    }

    /**
     * Finds and removes any malfunctioning Pods from the launched track
     * 
     * @return the total number of pods which were removed
     */
    public int clearMalfunctioning() {
        int malfunc = 0;
        LinkedNode curr = launched.head;
        LinkedNode prev = null;
        
        while (curr != null) {
            if (!curr.getPod().isFunctional()) {
                malfunc++;

                if (prev == null) {
                    launched.head = curr.getNext();
                    curr = launched.head;
                } else {
                    prev.setNext(curr.getNext());
                    curr = prev.getNext();
                }
                
            } else {
                prev = curr;
                curr = curr.getNext();
            }
        }
        
        return malfunc;
    }

    /**
     * Reports the total number of first and economy class Pods which are waiting to be launched
     * 
     * @return the total number of unlaunched pods at this LoopStation
     */
    public int getNumWaiting() {
        return waitingEconomy.size() + waitingFirst.size();
    }

    /**
     * Reports the total number of Pods, functional or non-functional, which are currently running on the launched track
     * 
     * @return the total number of Pods on the launched track
     */
    public int getNumLaunched() {
        return launched.size();
    }

    /**
     * Reports the total number of passengers in functional Pods across all tracks, waiting and launched
     * 
     * @return the total number of passengers in functional Pods currently being served by this LoopStation
     */
    public int getNumPassengers() {
        int total = 0;
        LinkedNode curr = waitingEconomy.head;
        while (curr != null) {
            try {
                total += curr.getPod().getNumPassengers();
                curr = curr.getNext();
            } catch (MalfunctioningPodException e) {
                ;
            }
        }

        curr = waitingFirst.head;
        while (curr != null) {
            try {
                total += curr.getPod().getNumPassengers();
                curr = curr.getNext();
            } catch (MalfunctioningPodException e) {
                ;
            }
        }

        curr = launched.head;
        while (curr != null) {
            try {
                total += curr.getPod().getNumPassengers();
                curr = curr.getNext();
            } catch (MalfunctioningPodException e) {
                ;
            }
        }
        
        return total;
    }
}
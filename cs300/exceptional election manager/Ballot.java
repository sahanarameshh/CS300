//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Ballot Class
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * An instantiable class representing a ballot. The class stores a list of Elections, and each Ballot instance is allowed one vote per Election.
 */
public class Ballot {
    private static boolean ballotsCreated;
    private static ArrayList<Election> elections = new ArrayList<>(); 
    private boolean[] hasVoted;

    /**
     * Initializes a new ballot which corresponds to the current number of elections present in the elections ArrayList.
     * 
     */
    public Ballot() {
        if (elections.size() == 0) {
            throw new IllegalStateException("No elections available.");
        } else {
            hasVoted = new boolean[elections.size()];
            ballotsCreated = true;
        }
    }

    /**
     * Adds an election to the end of the elections ArrayList, as long as this election (or one equal to it) is not yet present.
     * 
     * @param election the election to add to the list of elections
     */
    public static void addElection(Election election) {
        if (elections.contains(election)) {
            throw new IllegalArgumentException("Election already exists.");
        }

        if (ballotsCreated) {
            throw new IllegalStateException("Cannot add elections after ballots have been created.");
        }
        
        elections.add(election);
    }

    /**
     * Adds 1 vote to the provided Candidate running in the election for the given seatName, if this Ballot has not yet voted in that election.
     * 
     * @param seatName the name of the seat for the election to vote in
     * @param candidate the Candidate to vote for
     */
    public void vote(String seatName, Candidate candidate) {
        if (hasVoted(seatName)) {
            throw new IllegalStateException("Already voted for this seat.");
        }

        boolean eExists = false;
        //int index = 0;
        for (int i = 0; i < elections.size(); i++) {
            if (elections.get(i).SEAT_NAME.equals(seatName)) { 
                if (elections.get(i).toString().contains(candidate.toString())) {  
                    eExists = true;
                    hasVoted[i] = true;
                    candidate.addVote();  
                    break;
                }
            }
        }

        if (!eExists) {
            throw new NoSuchElementException("No such election or candidate found.");
        }
    }

    /**
     * Checks whether this ballot has already voted in an election corresponding to the given seatName
     * 
     * @param seatName the name of the seat for the election to vote in
     * @return true if this ballot has already cast a vote for the specified election, false otherwise
     */
    public boolean hasVoted(String seatName) {
        for (int i = 0; i < elections.size(); i++) {
            if (elections.get(i).SEAT_NAME.equals(seatName)) {
                return hasVoted[i];
            }
        }

        throw new NoSuchElementException("No such election found.");
    }

    /**
     * Creates and returns a String representation of this ballot's voter state as follows: in order, lists the seatName of the election from the elections ArrayList and whether this Ballot has yet cast a vote in that election.
     * 
     * @return a string representation of this ballot as described in this comment, which does NOT end with a newline
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Election e : elections) {
            str.append(e.SEAT_NAME).append(": ").append(hasVoted(e.SEAT_NAME)).append("\n");
        }
        return str.toString();
    }

    /**
     * empties the elections arraylist and resets ballotsCreated
     * 
     */
    public static void clearElections() {
        elections.clear();
        ballotsCreated = false;
    }
}
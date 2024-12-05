//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Candidate Class
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

/**
 * An instantiable class representing a candidate in an election.
 */
public class Candidate {
    private String name;
    private int numVotes;
    private String party;

    /**
     * Creates a new Candidate object with the given name and party.
     * 
     * @param name name of candidate
     * @param party party of candidate
     */
    public Candidate(String name, String party) {
        if (name == null || party == null) {
            throw new IllegalArgumentException();
        }

        if (name.trim().isEmpty() || party.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.party = party;
    }

    /**
     * Adds one (1) vote to this candidate's total
     * 
     */
    public void addVote() {
        this.numVotes++;
    }

    /**
     * Determines whether this candidate and anObject are copies (deep or shallow) of each other.
     * 
     * @param anObject another Candidate object for comparison
     * @return true if this candidate and anObject are equal, false otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof Candidate) {
            return (this.name.equals(((Candidate) anObject).name)) && (this.numVotes == ((Candidate) anObject).numVotes) && (this.party.equals(((Candidate) anObject).party));
        }
        return false;
    }

    /**
     * Accessor for the candidate's current number of votes
     * 
     * @return number of votes
     */
    public int getNumVotes() {
        return this.numVotes;
    }

    /**
     * Creates and returns a String representation of this candidate in the form "name (party): numVotes".
     * 
     */
    @Override
    public String toString() {
        return name + " (" + party + "): " + numVotes;
    }
}
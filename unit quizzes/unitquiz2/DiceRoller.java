//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name:   Sahana Ramesh
// Campus ID #: 9086752467
// WiscEmail:   ramesh37@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// No other imports besides ArrayList, NoSuchElementException, or Random are permitted.
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains THREE classes. You will need to complete the
// implementation of ALL classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// We have only provided a tester method for ONE of the classes here; you may
// add others for the other classes but be aware of time!
//
// When creating new exception objects, including messages within these objects 
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * ((almost)) PROVIDED: base class for the dice roller
 */
class SillyDice {
  // Data fields
  private static Random randGen = new Random(0);
  private int numSides;
  private int rollsLeft;
  
  // Constructor - creates a new sillydice with the given number of sides,
  // which can be rolled up to 5 times
  public SillyDice(int numSides) {
    if (numSides <= 1) throw new IllegalArgumentException();
    this.numSides = numSides;
    this.rollsLeft = 5;
  }
  
  // Generates a random number from 1-number of sides, inclusive, if this sillydice can be rolled
  public int rollDice() {
    if (!canRoll()) throw new IllegalStateException();
    rollsLeft--;
    return randGen.nextInt(numSides)+1;
  }
  
  //////////////////////////////////////////// TODO ///////////////////////////////////////////////
  /**
   * Determines whether this sillydice can be rolled
   * @return true if there are any rolls left, false otherwise
   */
  public boolean canRoll() {
    // (1) TODO: complete the implementation of this method according to its javadocs
    return rollsLeft > 0;
  }
  /////////////////////////////////////////////////////////////////////////////////////////////////
  
  // Creates a string representation of this SillyDice
  public String toString() { 
    return this.numSides+" sides ("+rollsLeft+")"; 
  }
}

/////////////////////////////////////////////////////////////////////////////////////////////////
// YOU ARE NOT DONE YET!! There are two more classes below...
//
// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)
/////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * An object of this class' type "is-a" SillyDice and inherits most of its functionality from 
 * SillyDice. For SillyCoins we will specifically translate their "rolls" to H (for heads) if
 * they roll a 1, and T (for tails) if they roll a 2.
 * 
 * ALL overridden methods you write MUST have the @Override annotation for full credit.
 */
class SillyCoin extends SillyDice { // (2) TODO: modify the class signature so that a SillyCoin "is-a" SillyDice
  /**
   * Create a SillyCoin as a SillyDice with 2 sides.
   */
  public SillyCoin() {
    // (3) TODO: complete the implementation of this constructor according to its javadocs
    super(2);
  }
  
  /**
   * Translates a SillyDice roll to a coin flip value
   * @return 'H' if the dice roll was 1, 'T' otherwise
   */
  public char flip() {
    // (4) TODO: complete the implementation of this method according to its javadocs
    if (super.rollDice() == 1) {
      return 'H';
    }
    return 'T';
  }
}

/**
 * This class models the DiceRoller data type. It models a collection of dice of various shapes,
 * some of which may be coins but may be other types of dice as well.
 * 
 * NOTE:
 * You may NOT add any additional data fields to this class unless specified in the TODO tags.
 */
public class DiceRoller {
  
  // (5) TODO: declare and initialize a CLASS variable named dice to store a possibly-infinite number
  //     of values which can be generic SillyDice OR specifically SillyCoins.
  private static ArrayList<SillyDice> dice = new ArrayList<>();  
  // PROVIDED: create a new SillyDice and add it to the DiceRoller
  public static void addDice(int numSides) {
    if (numSides == 2) {
      dice.add(new SillyCoin()); // uncomment this once you have created your class variables
    }
    else dice.add(new SillyDice(numSides)); // uncomment this once you have created your class variables
  } 
  
  /**
   * Finds a specific SillyDice by its number of sides. HINT: use the toString() method!
   * @param numSides - the number of sides on the SillyDice we are looking for
   * @return the first reference to a SillyDice with that many sides
   * @throws NoSuchElementException if no dice with that number of sides are found
   */
  public static SillyDice getNsidedDice(int numSides) {
    // (6) TODO: complete the implementation of this method according to its javadocs
    for (int i = 0; i < dice.size(); i++) {
      if (dice.get(i).toString().contains(String.valueOf(numSides))) {
        return dice.get(i);
      }
    }
    throw new NoSuchElementException();
  }
  
  /**
   * Creates a list of all SillyDice which have no rolls left.
   * @return a list containing references to all SillyDice in this DiceRoller which cannot be rolled
   */
  public static ArrayList<SillyDice> findAllDeadDice() {
    // (7) TODO: complete the implementation of this method according to its javadocs
    ArrayList<SillyDice> deadDice = new ArrayList<>();
    for (int i = 0; i < dice.size(); i++) {
      if(!dice.get(i).canRoll()) {
        deadDice.add(dice.get(i));
      }
    }
    return deadDice;
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////
  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)
  /////////////////////////////////////////////////////////////////////////////////////////////////
  
  public static boolean testDiceRoller() {
    DiceRoller.addDice(4);
    DiceRoller.addDice(2);
    DiceRoller.addDice(6);
    DiceRoller.addDice(12);
    if (dice.size() != 4) return false;
    
    SillyDice d = getNsidedDice(2);
    if (d == null) return false;
    if (!(d instanceof SillyCoin)) return false;
    
    for (int i=0; i<5; i++) ((SillyCoin)d).flip();
    try {
      ((SillyCoin)d).flip();
      return false;
    } catch (IllegalStateException e) {
    }
    
    ArrayList<SillyDice> noRolls = DiceRoller.findAllDeadDice();
    if (noRolls.size() != 1) return false;
    if (!noRolls.get(0).toString().startsWith("2 ")) return false;
    
    return true;
  }
  
  public static void main(String[] args) {
    System.out.println("testDiceRoller: "+testDiceRoller());
  }

}

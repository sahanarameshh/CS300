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

/**
 * This class models Track objects as a doubly-linked list for the CS300 Hyperloop project.
*/
public class Track implements ListADT<Pod>{
    protected LinkedNode head = null;
    private int size = 0;
    protected LinkedNode tail = null;
  
    /**
    * Adds a Pod to the track in the correct location.
    * 
    * @param newElement the Pod to add to this track
    */
    @Override
    public void add(Pod newElement) {
        try {
            if (newElement.getPodClass() == 0) {
                LinkedNode newNode = new LinkedNode(newElement, null, head);
                newNode.setNext(head);
                if (head != null) {
                    head.setPrev(newNode);
                }

                head = newNode;

                if (head.getNext() == null) {
                    tail = newNode;
                }
            }
            else if (newElement.getPodClass() == 1) {
                LinkedNode newNode = new LinkedNode(newElement, tail, null);
                if(head == null) {
                    head = newNode;  
                    tail = newNode;
                } else {
                    tail.setNext(newNode);
                    newNode.setPrev(tail);
                    tail = newNode;
                }        
            }
            size++;
        } catch(MalfunctioningPodException m){
            ;
        }
    }

    /**
     * Adds a passenger to the first available seat in a Pod which matches their class designation.
     * 
     * @param name the name of the passenger to add
     * @param isFirstClass whether this passenger is first class
     * @return true if they were successfully added to an available seat of their corresponding class, false if there were no seats or Pods available for their class
     */
    public boolean addPassenger(String name, boolean isFirstClass) {
        LinkedNode currentNode = head;
        if (size == 0) {
            return false;
        }

        if (isFirstClass) {
            for (int x = 0; x < size; x++) {
                try {
                    if ((currentNode.getPod().getPodClass() == 0) && (!currentNode.getPod().isFull())) {
                        currentNode.getPod().addPassenger(name);
                        return true;
                    }
                } catch(MalfunctioningPodException m) {
                    ;
                }
                currentNode = currentNode.getNext();
            }
            return false;
        } else {
            for(int x = 0; x < size; x++) {
                try {
                    if ((currentNode.getPod().getPodClass() == 1) && !currentNode.getPod().isFull()) {
                        currentNode.getPod().addPassenger(name);
                        return true;
                    }
                } catch(MalfunctioningPodException m) {
                    ;
                }
                currentNode = currentNode.getNext();
            }
            return false;
        }
    }
  
    /**
     * Removes ALL Pods from this track
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Determines whether a particular Pod is contained in the track
     * 
     * @param toFind the Pod to search for in the track
     * @return true if the Pod is contained in the track, false otherwise
     */
    @Override
    public boolean contains(Pod toFind) {
        LinkedNode current = head;
        for (int x = 0; x < size; x++) {
            if (current.getPod().equals(toFind)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    /**
     * Finds the index of the first non-functional pod on the track.
     * 
     * @return the lowest index of a non-functional Pod on the track, or -1 if all Pods are currently functioning (or the Track is currently empty)
     */
    public int findFirstNonFunctional(){
        LinkedNode current = head;
        int counter = 0;
        if (this.size == 0) {
            return -1;
        }
   
        while(current!=null) {
            try {
                current.getPod().getCapacity();
            } catch (MalfunctioningPodException m) {
                return counter;
            }
            current = current.getNext();
        }
        return -1;
    }
  
    /**
     * Searches all Pods in the track to find the given passenger

     * 
     * @param name the name of the passenger to find
     * @return the index of the Pod this passenger was located in, or -1 if they were not found (or the Track is currently empty)
     */
    public int findPassenger(String name) {
        LinkedNode current = head;
        int counter = 0;
        if (this.size == 0) {
            return -1;
        }
        while(current!=null) {
            try {
                if (current.getPod().containsPassenger(name)) {
                    return counter;
                }
            } catch (MalfunctioningPodException m) {
                ;
            }
            counter++;
            current = current.getNext();
        }
        return - 1;
    }
 
    /**
     * Accesses the Pod at a given index
     * 
     * @param index the index of the Pod to access
     * @throws IndexOutOfBoundsException if the given index is invalid
     * @return a reference to the Pod at a given index in the track
     */
    public Pod get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        LinkedNode current = head;
        for (int x = 0; x<index; x++) {
            current = current.getNext();
        }
        return current.getPod();
    }

    /**
     * Reports whether the track is currently empty
     * 
     * @return true if the track is currently empty, false otherwise
     */
    public boolean isEmpty() {
        if (head == null && tail == null) {
            return true;
        }
        return false;
    }

    /**
     * Removes a Pod at a given index from the track
     * 
     * @param index the index of the Pod to remove
     * @throws IndexOutOfBoundsException if the given index is invalid
     * @return a reference to the Pod removed from the track
     */
    public Pod remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        LinkedNode current = head;
        Pod temp;

        if (index == 0) {
            temp = head.getPod();
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
        } else {
            int currentIndex = 0;
            while (currentIndex < index) {
                current = current.getNext();
                currentIndex++;
            }
            
            temp = current.getPod();
            
            if (current.getPrev() != null) {
                current.getPrev().setNext(current.getNext());
            }
            if (current.getNext() != null) {
                current.getNext().setPrev(current.getPrev());
            } else {
                tail = current.getPrev();  // Update tail if last element is removed
            }
        }
        size--;
        return temp;
    }

    /**
     * Reports the current number of Pods currently on this track. This number includes both functional and non-functional Pods.
     * 
     * @return the number of Pods on this track
     */
    public int size() {
        int counter = 0;
        LinkedNode current = head;
        while (current != null) {
            counter++;
            current = current.getNext();
        }
        return counter;
    }

    /**
     * Returns a String representation of the entire contents of the track
     * 
     * @return a String representation of all Pods currently on the track
     */
    public String toString() {
        LinkedNode current = head;
        String returnVal = "";
        while (current!=null) {
            returnVal += current.getPod().toString();
            returnVal += "\n";
            current = current.getNext();
        }
        return returnVal;
    }
}
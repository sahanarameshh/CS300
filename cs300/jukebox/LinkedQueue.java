import java.util.ArrayList;

public class LinkedQueue<T> implements QueueADT<T> {
    private LinkedNode<T> back;
    private LinkedNode<T> front;
    private int size = 0;

    public void enqueue(T value) {
        LinkedNode newNode = new LinkedNode<T>(value);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            back.setNext(newNode);
            back = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (front == null) {
            return null;
        }
        
        T temp = front.getData();
        front = front.getNext();
        if (isEmpty()) {
            back = null;
        }
        size--;
        return temp;
    }

    public T peek() {
        if (front == null) {
            return null;
        }

        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        LinkedNode current = front;
        while (current != null) {
            if (current.getData().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    public ArrayList<T> getList() {
        ArrayList<T> elements = new ArrayList<>();
        LinkedNode current = front;
        while (current != null) {
            elements.add((T) current.getData());
            current = current.getNext();
        }
        return elements;
    }
}

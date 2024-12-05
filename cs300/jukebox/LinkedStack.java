import java.util.ArrayList;

public class LinkedStack<T> implements StackADT<T> {
    private LinkedNode<T> top;

    public void push(T value) {
        LinkedNode newNode = new LinkedNode(value);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        T poppedValue = top.getData();
        top = top.getNext();
        return poppedValue;
    }

    public T peek() {
        if (top == null) {
            return null;
        }

        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean contains(T value) {
        LinkedNode current = top;
        while (current != null) {
            if (current.getData().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public ArrayList<T> getList() {
        ArrayList<T> elements = new ArrayList<>();
        LinkedNode current = top;
        while (current != null) {
            elements.add((T) current.getData());
            current = current.getNext();
        }
        return elements;
    }
}

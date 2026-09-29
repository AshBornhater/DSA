package linkedlist;

public abstract class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public abstract void append(Object data);
    public abstract void addFirst(Object data);
    public abstract void remove(Object data);
    public abstract boolean contains(Object data);
    
    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void display() {
        if (head == null) {
            System.out.println("Empty List.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print("\u001B[36m" + current.data + "\u001B[0m --> ");
            current = current.next;
        }
        System.out.println("null\n");
        System.out.println("\u001B[31m[ Head = " + head.data + ", Tail = " + tail.data + ", Size = " + size + " ]\u001B[0m \n");
    }

}
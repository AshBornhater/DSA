package sub;

import linkedlist.LinkedList;
import linkedlist.Node;

public class SinglyLinkedList extends LinkedList {

    @Override
    public void append(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addFirst(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    @Override
    public void remove(Object data) {
        if (isEmpty()) {
            System.out.println("LinkedList is empty, Nothing to remove.");
            return;
        }

        if (head.data.equals(data)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return;
        }


        Node current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

    
        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
            }
            current.next = current.next.next;
            size--;
        } else {
            System.out.println("Data not found.");
        }
    }

    @Override
    public boolean contains(Object data) {
        Node current = head;
        while (current != null) {
            if (current.data != null && current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

}
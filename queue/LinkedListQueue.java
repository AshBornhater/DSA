/**
 * Implementasi Queue menggunakan Single Linked-List.
 *
 * Konsep FIFO (First In, First Out):
 *  - enqueue  -> selalu dimasukkan di TAIL (belakang)
 *  - dequeue  -> selalu diambil dari HEAD (depan)
 *
 * Digunakan untuk: checking palindrome (urutan karakter asli tetap terjaga).
 */
public class LinkedListQueue implements Main.QueueADT {

    /** Node tunggal untuk single linked-list. */
    private static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // depan (tempat dequeue)
    private Node tail; // belakang (tempat enqueue)
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Masukkan karakter di belakang antrian. O(1). */
    @Override
    public void enqueue(char c) {
        Node baru = new Node(c);
        if (isEmpty()) {
            head = baru;
        } else {
            tail.next = baru;
        }
        tail = baru;
        size++;
    }

    /** Ambil karakter dari depan antrian. O(1). */
    @Override
    public char dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong! Tidak ada yang bisa di-dequeue.");
        }
        char data = head.data;
        head = head.next;
        if (head == null) {
            tail = null; // habis terhapus semua
        }
        size--;
        return data;
    }

    /** Lihat karakter paling depan tanpa menghapus. O(1). */
    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong!");
        }
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    /** Cetak isi antrian dari depan ke belakang. */
    public void printQueue() {
        Node current = head;
        System.out.print("[Depan] ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("[Belakang]");
    }
}

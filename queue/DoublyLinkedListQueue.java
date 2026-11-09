/**
 * Implementasi Queue menggunakan Doubly Linked-List.
 *
 * Konsep FIFO (First In, First Out):
 *  - enqueue  -> dimasukkan di TAIL (belakang)
 *  - dequeue  -> diambil dari HEAD (depan)
 *
 * Bedanya dengan single linked-list: setiap Node punya pointer prev & next,
 * sehingga bisa ditraverse dua arah (maju dan mundur).
 * Digunakan untuk: checking palindrome (urutan karakter asli tetap terjaga).
 */
public class DoublyLinkedListQueue implements Main.QueueADT {

    /** Node ganda: punya prev dan next. */
    private static class Node {
        char data;
        Node prev;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    private Node head; // depan (tempat dequeue)
    private Node tail; // belakang (tempat enqueue)
    private int size;

    public DoublyLinkedListQueue() {
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
            baru.prev = tail;
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
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // antrian jadi kosong
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

    /** Cetak isi antrian dari depan ke belakang (traverse maju). */
    public void printQueueForward() {
        Node current = head;
        System.out.print("[Depan] ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("[Belakang]");
    }

    /** Cetak isi antrian dari belakang ke depan (traverse mundur, hanya bisa di DLL). */
    public void printQueueBackward() {
        Node current = tail;
        System.out.print("[Belakang] ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println("[Depan]");
    }
}

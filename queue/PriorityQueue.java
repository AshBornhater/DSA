/**
 * Implementasi Priority Queue menggunakan sorted linked-list.
 *
 * Konsep: elemen yang dikeluarkan (dequeue) BUKAN elemen yang masuk pertama,
 * melainkan elemen dengan PRIORITAS TERTINGGI.
 *
 * Di sini angka prioritas ditentukan dari nilai karakter:
 * karakter dengan nilai ASCII/Unicode terkecil = prioritas tertinggi,
 * jadi hasil dequeue selalu urut naik (sorted), TIDAK urut masuk (FIFO).
 *
 * CATATAN TEORI (kenapa tidak dipakai untuk palindrome):
 * Cek palindrom menuntut urutan karakter ASLI dipertahankan (FIFO).
 * Priority queue mengurutkan ulang karakter berdasarkan prioritasnya,
 * sehingga urutan asli hilang dan palindrom tidak bisa diperiksa.
 * Contoh: enqueue "katak" -> dequeue hasilnya "aakkt" (bukan "katak" lagi),
 * sehingga perbandingan dengan kebalikan string akan selalu salah.
 *
 * Karena itu kelas ini TETAP dibuat sesuai assignment, tetapi tidak
 * dijadikan pilihan di menu Main untuk checking palindrome.
 */
public class PriorityQueue implements Main.QueueADT {

    /** Node single linked-list, sekalian menyimpan nilai prioritasnya. */
    private static class Node {
        char data;
        int priority;
        Node next;

        Node(char data) {
            this.data = data;
            this.priority = data; // prioritas = nilai karakter
        }
    }

    private Node head; // selalu elemen dengan PRIORITAS TERTINGGI
    private int size;

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    /**
     * Masukkan karakter ke posisi yang sesuai dengan prioritasnya (sorted insert).
     * O(n), karena harus mencari posisi yang tepat.
     */
    @Override
    public void enqueue(char c) {
        Node baru = new Node(c);
        // Case 1: kosong, atau prioritasnya lebih tinggi dari head
        if (isEmpty() || baru.priority < head.priority) {
            baru.next = head;
            head = baru;
        } else {
            // Case 2: cari posisi di tengah/belakang (ascending)
            Node current = head;
            while (current.next != null && current.next.priority <= baru.priority) {
                current = current.next;
            }
            baru.next = current.next;
            current.next = baru;
        }
        size++;
    }

    /** Ambil elemen dengan prioritas tertinggi (nilai karakter terkecil). O(1). */
    @Override
    public char dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue kosong! Tidak ada yang bisa di-dequeue.");
        }
        char data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /** Lihat elemen prioritas tertinggi tanpa menghapus. O(1). */
    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue kosong!");
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

    /** Cetak isi priority queue (prioritas tertinggi -> terendah). */
    public void printQueue() {
        Node current = head;
        System.out.print("[Prioritas tertinggi] ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("[Prioritas terendah]");
    }
}

/**
 * Implementasi Queue menggunakan Circular Array (array melingkar).
 *
 * Konsep FIFO (First In, First Out):
 *  - enqueue  -> data ditulis pada posisi (front + count) % capacity
 *  - dequeue  -> data diambil dari front, lalu front = (front + 1) % capacity
 *
 * Dengan "melingkar", memori depan yang sudah kosong bisa dipakai lagi
 * tanpa perlu menggeser elemen (efisien dibanding array biasa).
 * Jika penuh, kapasitas otomatis digandakan.
 * Digunakan untuk: checking palindrome (urutan karakter asli tetap terjaga).
 */
public class CircularArrayQueue implements Main.QueueADT {

    private char[] data;
    private int front; // indeks elemen paling depan
    private int count; // jumlah elemen saat ini

    public CircularArrayQueue() {
        this(10); // kapasitas awal default
    }

    public CircularArrayQueue(int capacity) {
        data = new char[capacity];
        front = 0;
        count = 0;
    }

    /** True jika jumlah elemen sudah sama dengan kapasitas array. */
    public boolean isFull() {
        return count == data.length;
    }

    /** Masukkan karakter di belakang antrian. O(1) amortized. */
    @Override
    public void enqueue(char c) {
        if (isFull()) {
            resize(data.length * 2); // kapasitas penuh -> gandakan
        }
        int rear = (front + count) % data.length; // posisi belakang (melingkar)
        data[rear] = c;
        count++;
    }

    /** Ambil karakter dari depan antrian. O(1). */
    @Override
    public char dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong! Tidak ada yang bisa di-dequeue.");
        }
        char c = data[front];
        front = (front + 1) % data.length; // mundur satu posisi secara melingkar
        count--;
        return c;
    }

    /** Lihat karakter paling depan tanpa menghapus. O(1). */
    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong!");
        }
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    /** Perbesar kapasitas, dengan mempertahankan urutan elemen secara logis. */
    private void resize(int newCapacity) {
        char[] baru = new char[newCapacity];
        for (int i = 0; i < count; i++) {
            baru[i] = data[(front + i) % data.length];
        }
        data = baru;
        front = 0;
    }

    /** Cetak isi antrian secara logis (depan -> belakang). */
    public void printQueue() {
        System.out.print("[Depan] ");
        for (int i = 0; i < count; i++) {
            System.out.print(data[(front + i) % data.length] + " ");
        }
        System.out.println("[Belakang] (kapasitas array: " + data.length + ")");
    }
}

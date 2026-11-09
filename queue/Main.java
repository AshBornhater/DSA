import java.util.Scanner;

/**
 * Program utama: memilih jenis queue untuk CHECKING PALINDROME.
 *
 * Menu:
 *  1. LinkedListQueue          (bisa dipakai cek palindrom, FIFO)
 *  2. DoublyLinkedListQueue    (bisa dipakai cek palindrom, FIFO)
 *  3. CircularArrayQueue       (bisa dipakai cek palindrom, FIFO)
 *  4. PriorityQueue            (TIDAK bisa dipakai cek palindrom, bukan FIFO)
 *  5. Keluar
 *
 * Teori: cek palindrom membutuhkan urutan karakter ASLI (FIFO).
 * Priority queue keluar berdasarkan prioritas, bukan urutan masuk,
 * sehingga urutan asli hancur dan tidak valid untuk cek palindrom.
 */
public class Main {

    /** Kontrak queue yang dipakai untuk cek palindrom. */
    public interface QueueADT {
        void enqueue(char c);
        char dequeue();
        boolean isEmpty();
    }

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            pilihan = bacaAngka();

            switch (pilihan) {
                case 1 -> jalankanCekPalindrom(new LinkedListQueue(), "LinkedListQueue");
                case 2 -> jalankanCekPalindrom(new DoublyLinkedListQueue(), "DoublyLinkedListQueue");
                case 3 -> jalankanCekPalindrom(new CircularArrayQueue(), "CircularArrayQueue");
                case 4 -> demoPriorityQueue(); // hanya demo, TIDAK dipakai cek palindrom
                case 5 -> System.out.println("Program selesai. Terima kasih!");
                default -> System.out.println("Pilihan tidak valid, coba lagi!\n");
            }
        } while (pilihan != 5);

        input.close();
    }

    private static void tampilkanMenu() {
        System.out.println("==============================================");
        System.out.println("        CEK PALINDROM MENGGUNAKAN QUEUE");
        System.out.println("==============================================");
        System.out.println("1. LinkedListQueue");
        System.out.println("2. DoublyLinkedListQueue");
        System.out.println("3. CircularArrayQueue");
        System.out.println("4. PriorityQueue (tidak bisa untuk palindrom)");
        System.out.println("5. Keluar");
    }

    /** Alur cek palindrom dengan queue pilihan user. */
    private static void jalankanCekPalindrom(QueueADT queue, String namaQueue) {
        System.out.print("\nMasukkan kata/kalimat: ");
        String asli = input.nextLine();

        // Bersihkan: buang selain huruf & abaikan besar/kecil
        String bersih = asli.toLowerCase().replaceAll("[^a-z]", "");

        if (bersih.isEmpty()) {
            System.out.println("Input harus mengandung minimal satu huruf!\n");
            return;
        }

        // enqueue semua karakter sesuai URUTAN ASLI (FIFO) ke antrian yang kosong
        for (int i = 0; i < bersih.length(); i++) {
            queue.enqueue(bersih.charAt(i));
        }

        // Bandingkan hasil dequeue (urut depan) dengan bacaan dari belakang.
        // Cocok semua => palindrom.
        boolean palindrom = true;
        for (int i = 0; i < bersih.length() && palindrom; i++) {
            char depan = queue.dequeue();
            char belakang = bersih.charAt(bersih.length() - 1 - i);
            if (depan != belakang) {
                palindrom = false;
            }
        }

        System.out.println("\nQueue yang dipakai : " + namaQueue);
        System.out.println("Input              : " + asli);
        System.out.println("Hasil              : " + (palindrom ? "PALINDROM" : "BUKAN PALINDROM"));
        System.out.println();
    }


    private static void demoPriorityQueue() {
        System.out.println("\n[!] PriorityQueue TIDAK dapat dipilih untuk checking palindrome.");
        System.out.println("    Alasan: dequeue dilakukan berdasarkan PRIORITAS, bukan urutan masuk (bukan FIFO),");
        System.out.println("    sehingga urutan asli string hilang dan tidak bisa dibandingkan dengan kebalikannya.");
        System.out.println("    Berikut demo kecil untuk membuktikannya:\n");

        PriorityQueue pq = new PriorityQueue();
        String kata = "katak"; 

        System.out.print("Enqueue  : ");
        for (int i = 0; i < kata.length(); i++) {
            pq.enqueue(kata.charAt(i));
            System.out.print(kata.charAt(i) + " ");
        }

        System.out.print("\nDequeue  : ");
        StringBuilder hasil = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.dequeue();
            hasil.append(c);
            System.out.print(c + " ");
        }

        System.out.println("\nString asli  : " + kata);
        System.out.println("Hasil dequeue: " + hasil + "  (urutan asli sudah berubah!)");
        System.out.println("Kesimpulan   : urutan hancur -> priority queue tidak cocok untuk cek palindrom.\n");
    }

    /** Membaca input menu dengan aman (anti crash kalau user bukan angka). */
    private static int bacaAngka() {
        while (true) {
            String baris = input.nextLine().trim();
            try {
                return Integer.parseInt(baris);
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka. Pilih menu: ");
            }
        }
    }
}

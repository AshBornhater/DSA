# Infix to Postfix Converter (Java)

Program kecil buat tugas Struktur Data: mengubah ekspresi infix (yang biasa kita tulis, kayak `A + B * C`) jadi postfix (`A B C * +`) menggunakan stack.

Stack-nya sengaja dibuat sendiri dari linked list, bukan pakai `java.util.Stack`, supaya implementasi push/pop/peek-nya kelihatan jelas. Sesuai batasan tugas, satu-satunya library Java yang dipakai adalah `Scanner` untuk membaca input, dan itu pun hanya di `Main.java`. Tidak ada `ArrayList`, `List`, `StringBuilder`, `Character`, atau koleksi lain di dalam program.

## Isi Folder

| File | Fungsi |
| --- | --- |
| `Node.java` | Node linked list, isinya satu `char` dan pointer `next` |
| `Stack.java` | Stack dasar (push, pop, peek, isEmpty) di atas linked list |
| `InfixToPostfix.java` | Logika utama: pembacaan operand, validasi, dan konversi |
| `Main.java` | Program interaktif, baca input lewat `Scanner` lalu cetak hasilnya |

## Fitur

- Operator yang didukung: `+`, `-`, `*`, `/`, `^`
- Tiga jenis kurung: `( )`, `[ ]`, `{ }`, dan pasangannya dicek satu per satu
- Operand boleh lebih dari satu karakter, jadi `12` atau `3.5` diperlakukan sebagai satu operand
- Spasi di input diabaikan
- Ekspresi yang salah ditolak dengan pesan error yang jelas, bukan hasil ngawur

Program mengecek dulu apakah susunan operand/operator masuk akal sebelum konversi dijalankan, jadi kasus seperti `A + * B`, `(A+B`, atau `A+B)` langsung ditolak.

## Cara Menjalankan

Butuh JDK 8 atau lebih baru (program hanya memakai fitur Java dasar dan `try-with-resources`). Masuk ke folder ini lalu:

```bash
javac *.java
java Main
```

Contoh sesi:

```
=== Infix to Postfix Conversion ===
Operator: + - * / ^
Brackets: ( ) [ ] { }

Input infix expression (q = quit): A+B*C

Infix  : A+B*C
Postfix: A B C * +

Input infix expression (q = quit): 5*(10+8)+9^3

Infix  : 5*(10+8)+9^3
Postfix: 5 10 8 + * 9 3 ^ +

Input infix expression (q = quit): q
```

Beberapa contoh lain:

| Infix | Postfix |
| --- | --- |
| `A+B*C` | `A B C * +` |
| `(A+B)*C` | `A B + C *` |
| `A*B+C/D` | `A B * C D / +` |
| `{a+(b*c)}^2` | `a b c * + 2 ^` |
| `12+3.5*4` | `12 3.5 4 * +` |

## Cara Kerjanya

### 1. Membaca operand tanpa token list

`convert()` dan `validateOrder()` membaca string infix langsung dari kiri ke kanan. Helper `isDigit()` dan `isLetter()` mengecek rentang ASCII, `isOperandChar()` menambahkan titik desimal, dan `isWhitespace()` mengenali spasi/tab/baris baru. `operandEnd()` mengembalikan indeks setelah karakter terakhir operand, sehingga `12` atau `3.5` dibaca sebagai satu unit tanpa perlu `ArrayList` atau `StringBuilder`.

### 2. Validasi urutan

`validateOrder()` memakai satu flag `expectingOperand` yang berganti-ganti: setelah operand harusnya operator/kurung tutup, setelah operator harusnya operand, dan setelah kurung buka harusnya operand. Pelanggaran dilaporkan lewat helper `misplaced()`. Flag `hasToken` menolak input kosong, dan pengecekan terakhir menolak ekspresi yang berakhir dengan operator atau kurung buka.

### 3. Konversi (shunting-yard)

Inti algoritmanya ada di `convert()`:

- Operand langsung ditulis ke output.
- Kurung buka di-push ke stack.
- Kurung tutup: pop stack dan tulis ke output sampai ketemu kurung buka, lalu kurung buka itu di-pop dan dicek pasangannya.
- Operator: selama operator di puncak stack punya prioritas lebih tinggi (atau sama), pop dan tulis ke output. Setelah itu operator baru di-push.
- Di akhir, semua isi stack dikeluarkan. Kalau masih ada kurung buka di situ, berarti kurungnya tidak pernah ditutup.

Prioritas operator: `^` paling tinggi (3), lalu `*` dan `/` (2), lalu `+` dan `-` (1). Untuk `^` dipakai aturan right-associative, jadi `A^B^C` dibaca `A^(B^C)`, bukan `(A^B)^C`.

Output postfix dirapikan dengan `removeTrailingSpace()`, yang hanya memakai `charAt()` dan `substring()`.

## Error yang Ditangani

- Input kosong
- Karakter yang tidak dikenal
- Operand/operator salah posisi, misal `A + * B`
- Ekspresi berakhir dengan operator atau kurung buka
- Kurung tutup tanpa pasangan
- Kurung buka yang tidak ditutup
- Pasangan kurung tidak cocok, misal `(A+B]`

Semua error dilempar sebagai `IllegalArgumentException` dari `InfixToPostfix`, lalu ditangkap di `Main` dan ditampilkan sebagai `Invalid expression: ...` supaya program tidak mati.

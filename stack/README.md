# Infix to Postfix Converter (Java)

Program kecil buat tugas Struktur Data: mengubah ekspresi infix (yang biasa kita tulis, kayak `A + B * C`) jadi postfix (`A B C * +`) menggunakan stack.

Stack-nya sengaja dibuat sendiri dari linked list, bukan pakai `java.util.Stack`, supaya implementasi push/pop/peek-nya kelihatan jelas.

## Isi Folder

| File | Fungsi |
| --- | --- |
| `Node.java` | Node linked list, isinya satu `char` dan pointer `next` |
| `Stack.java` | Stack dasar (push, pop, peek, isEmpty) di atas linked list |
| `InfixToPostfix.java` | Logika utama: tokenizing, validasi, dan konversi |
| `Main.java` | Program interaktif, baca input dari keyboard lalu cetak hasilnya |

## Fitur

- Operator yang didukung: `+`, `-`, `*`, `/`, `^`
- Tiga jenis kurung: `( )`, `[ ]`, `{ }`, dan pasangannya dicek satu per satu
- Operand boleh lebih dari satu karakter, jadi `12` atau `3.5` diperlakukan sebagai satu operand
- Spasi di input diabaikan
- Ekspresi yang salah ditolak dengan pesan error yang jelas, bukan hasil ngawur

Yang terakhir itu cukup penting. Program mengecek dulu apakah susunan operand/operator masuk akal sebelum konversi dijalankan, jadi kasus seperti `A + * B`, `(A+B`, atau `A+B)` langsung ditolak.

## Cara Menjalankan

Butuh JDK (program ini ditulis pakai Java modern, ada `switch expression` di `precedence`, jadi minimal Java 14). Masuk ke folder ini lalu:

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

Input infix expression (q = quit): (A+B)*C

Infix  : (A+B)*C
Postfix: A B + C *

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

### 1. Tokenizing

`tokenize()` membaca string karakter per karakter. Huruf, angka, dan titik digabung jadi satu operand, sementara operator, kurung, dan spasi langsung dipisah. Karakter aneh di luar itu langsung memicu `IllegalArgumentException` (`Unknown character`).

### 2. Validasi urutan

`validateOrder()` memakai satu flag `expectingOperand` yang berganti-ganti: setelah operand harusnya operator, setelah operator harusnya operand. Dengan begitu ekspresi yang strukturnya rusak bisa ditolak lebih awal.

### 3. Konversi (shunting-yard)

Inti algoritmanya ada di `convert()`:

- Operand langsung ditulis ke output.
- Kurung buka di-push ke stack.
- Kurung tutup: pop stack dan tulis ke output sampai ketemu kurung buka, lalu kurung buka itu di-pop dan dicek pasangannya.
- Operator: selama operator di puncak stack punya prioritas lebih tinggi (atau sama), pop dan tulis ke output. Setelah itu operator baru di-push.
- Di akhir, semua isi stack dikeluarkan. Kalau masih ada kurung buka di situ, berarti kurungnya tidak pernah ditutup.

Prioritas operator: `^` paling tinggi (3), lalu `*` dan `/` (2), lalu `+` dan `-` (1). Untuk `^` dipakai aturan right-associative, jadi `A^B^C` dibaca `A^(B^C)`, bukan `(A^B)^C`.

## Error yang Ditangani

- Input kosong
- Karakter yang tidak dikenal
- Operand/operator salah posisi, misal `A + * B`
- Ekspresi berakhir dengan operator atau kurung buka
- Kurung tutup tanpa pasangan
- Kurung buka yang tidak ditutup
- Pasangan kurung tidak cocok, misal `(A+B]`

Semua error dilempar sebagai `IllegalArgumentException` dari `InfixToPostfix`, lalu ditangkap di `Main` dan ditampilkan sebagai `Invalid expression: ...` supaya program tidak mati.

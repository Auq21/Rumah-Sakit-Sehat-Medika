public class RiwayatKunjungan {

    // Node buatan sendiri: menyimpan catatan + pointer ke node sebelum dan sesudahnya
    private static class Node {
        String catatan;
        Node prev;
        Node next;

        Node(String catatan) {
            this.catatan = catatan;
        }
    }

    private Node head;      // kunjungan pertama (paling lama)
    private Node tail;      // kunjungan terakhir (paling baru)
    private Node saatIni;   // kunjungan yang sedang dilihat dokter
    private int jumlah;     // total kunjungan
    private int posisi;     // nomor urut kunjungan yang sedang dilihat (1..jumlah)

    // Menambah kunjungan baru di akhir, lalu pointer saatIni pindah ke kunjungan baru. O(1)
    public void tambahKunjungan(String catatan) {
        Node baru = new Node(catatan);
        if (head == null) {            // list masih kosong
            head = baru;
            tail = baru;
        } else {                       // sambungkan dua arah di belakang tail
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        saatIni = baru;
        jumlah++;
        posisi = jumlah;
    }

    // Pindah ke kunjungan sebelumnya. O(1)
    public void sebelumnya() {
        if (saatIni == null) {
            System.out.println("   [!] Riwayat masih kosong.");
            return;
        }
        if (saatIni.prev == null) {
            System.out.println("   [!] Sudah di kunjungan pertama, tidak bisa mundur lagi.");
            return;
        }
        saatIni = saatIni.prev;
        posisi--;
    }

    // Pindah ke kunjungan berikutnya. O(1)
    public void berikutnya() {
        if (saatIni == null) {
            System.out.println("   [!] Riwayat masih kosong.");
            return;
        }
        if (saatIni.next == null) {
            System.out.println("   [!] Sudah di kunjungan terakhir, tidak bisa maju lagi.");
            return;
        }
        saatIni = saatIni.next;
        posisi++;
    }

    // Menampilkan kunjungan yang sedang dilihat. O(1)
    public void tampilkanSaatIni() {
        if (saatIni == null) {
            System.out.println("   Riwayat kosong.");
            return;
        }
        System.out.println("   Saat ini [" + posisi + "/" + jumlah + "]: " + saatIni.catatan);
    }

    // Tambahan: menampilkan seluruh riwayat dengan penanda posisi saat ini. O(n)
    public void tampilkanSemua() {
        Node n = head;
        int i = 1;
        while (n != null) {
            String penanda = (n == saatIni) ? "  <-- saat ini" : "";
            System.out.println("   " + i + ". " + n.catatan + penanda);
            n = n.next;
            i++;
        }
    }

    public static void main(String[] args) {
        RiwayatKunjungan riwayat = new RiwayatKunjungan();

        System.out.println("=== SIMULASI RIWAYAT KUNJUNGAN PASIEN ===");

        System.out.println("\n[1] Cek riwayat sebelum ada data:");
        riwayat.tampilkanSaatIni();

        System.out.println("\n[2] Menambahkan 4 kunjungan:");
        String[] data = {
            "05-01-2026 | Demam tinggi, resep paracetamol",
            "12-02-2026 | Kontrol, kondisi membaik",
            "03-04-2026 | Batuk pilek, resep obat batuk",
            "20-06-2026 | Cek tekanan darah, hasil normal"
        };
        for (int i = 0; i < data.length; i++) {
            riwayat.tambahKunjungan(data[i]);
            System.out.println(" Tambah kunjungan ke-" + (i + 1) + ":");
            riwayat.tampilkanSaatIni();
        }

        System.out.println("\n[3] Navigasi mundur 2 kali:");
        for (int i = 1; i <= 2; i++) {
            riwayat.sebelumnya();
            System.out.println(" Mundur ke-" + i + ":");
            riwayat.tampilkanSaatIni();
        }

        System.out.println("\n[4] Navigasi maju 1 kali:");
        riwayat.berikutnya();
        System.out.println(" Maju ke-1:");
        riwayat.tampilkanSaatIni();

        System.out.println("\n[5] Posisi akhir dalam seluruh riwayat:");
        riwayat.tampilkanSemua();

        System.out.println("\n[6] Uji batas (tambahan): mundur 3 kali dari posisi sekarang");
        for (int i = 1; i <= 3; i++) {
            riwayat.sebelumnya();
            riwayat.tampilkanSaatIni();
        }
    }
}

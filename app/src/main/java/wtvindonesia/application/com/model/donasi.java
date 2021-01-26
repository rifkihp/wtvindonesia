package wtvindonesia.application.com.model;

import java.io.Serializable;

public class donasi implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String tanggal;
    private String judul;
    private String keterangan;
    private int jumlah;
    private int transfer;
    private int id_bank;
    private String no_rekening, nama_pemilik_rekening, nama_bank, cabang, gambar;
    private int status;

    public donasi(int id, String tanggal, String judul, String keterangan, int jumlah, int transfer, int id_bank, String no_rekening, String nama_pemilik_rekening, String nama_bank, String cabang, int status, String gambar) {
        this.id = id;
        this.tanggal = tanggal;
        this.judul = judul;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
        this.transfer = transfer;
        this.id_bank = id_bank;
        this.no_rekening = no_rekening;
        this.nama_pemilik_rekening = nama_pemilik_rekening;
        this.nama_bank = nama_bank;
        this.cabang = cabang;
        this.status = status;
        this.gambar = gambar;
    }

    public int getId() {
        return this.id;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public int getTransfer() {
        return this.transfer;
    }

    public int getId_bank() {
        return this.id_bank;
    }

    public String getNo_rekening() {
        return this.no_rekening;
    }

    public String getNama_pemilik_rekening() {
        return this.nama_pemilik_rekening;
    }

    public String getNama_bank() {
        return this.nama_bank;
    }

    public int getStatus() {
        return this.status;
    }

    public String getCabang() {
        return this.cabang;
    }

    public String getGambar() {
        return this.gambar;
    }

}

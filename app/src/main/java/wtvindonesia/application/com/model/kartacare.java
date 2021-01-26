package wtvindonesia.application.com.model;

import java.io.Serializable;

/**
 * Created by apple on 21/05/16.
 */
public class kartacare implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String judul, tanggal, penjelasan, jatuhtempo, kontak, nohp, gambar;
    private int biaya, terkumpul, donatur;
    private double pencapaian;
    private int batas_waktu_hari;

    public kartacare(int id, String judul, String tanggal, String penjelasan, String kontak, String nohp, String jatuhtempo, int biaya, int terkumpul, int donatur, double pencapaian, int batas_waktu_hari, String gambar) {
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.penjelasan = penjelasan;
        this.kontak = kontak;
        this.nohp = nohp;
        this.jatuhtempo = jatuhtempo;
        this.biaya = biaya;
        this.terkumpul = terkumpul;
        this.donatur = donatur;
        this.pencapaian = pencapaian;
        this.batas_waktu_hari = batas_waktu_hari;
        this.gambar = gambar;
    }

    public int getId() {
        return this.id;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getPenjelasan() {
        return this.penjelasan;
    }

    public String getKontak() {
        return this.kontak;
    }

    public String getNohp() {
        return this.nohp;
    }

    public String getJatuhtempo() {
        return this.jatuhtempo;
    }

    public int getBiaya() {
        return this.biaya;
    }

    public int getTerkumpul() {
        return this.terkumpul;
    }

    public int getDonatur() {
        return this.donatur;
    }

    public double getPencapaian() {
        return this.pencapaian;
    }

    public int getBatas_waktu_hari() {
        return this.batas_waktu_hari;
    }

    public String getGambar() {
        return this.gambar;
    }

}



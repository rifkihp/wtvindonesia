package wtvindonesia.application.com.model;

import java.io.Serializable;

/**
 * Created by apple on 21/05/16.
 */
public class news implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String tanggal;
    private String sejak;
    private String judul;
    private String kategori;
    private String header;
    private String konten;
    private String gambar;

    public news(int id, String tanggal, String sejak, String judul, String kategori, String header, String konten, String gambar) {
        this.id = id;
        this.tanggal = tanggal;
        this.sejak = sejak;
        this.judul = judul;
        this.kategori = kategori;
        this.header  = header;
        this.konten = konten;
        this.gambar = gambar;
    }

    public int getId() {
        return this.id;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getSejak() {
        return this.sejak;
    }

    public String getHeader() {
        return this.header;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getKategori() {
        return this.kategori;
    }

    public String getKonten() {
        return this.konten;
    }

    public String getGambar() {
        return this.gambar;
    }

}



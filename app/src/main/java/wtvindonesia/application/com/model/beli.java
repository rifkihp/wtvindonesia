package wtvindonesia.application.com.model;

import java.io.Serializable;

public class beli implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String no_trx;
    private String jns_trx;
    private String tanggal;
    private String status_trx;
    private String keterangan;
    private String no_tujuan;
    private String faktur;
    private int jumlah;

    public beli(int id, String no_trx, String jns_trx, String tanggal, String status_trx, String keterangan, String no_tujuan, String faktur, int jumlah) {
        this.id = id;
        this.no_trx = no_trx;
        this.jns_trx = jns_trx;
        this.tanggal = tanggal;
        this.status_trx = status_trx;
        this.keterangan = keterangan;
        this.no_tujuan = no_tujuan;
        this.faktur = faktur;
        this.jumlah = jumlah;
    }

    public int getId() {
        return this.id;
    }

    public String getNo_trx() {
        return this.no_trx;
    }

    public String getJns_trx() {
        return this.jns_trx;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getStatus_trx() {
        return this.status_trx;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public String getNo_tujuan() {
        return this.no_tujuan;
    }

    public String getFaktur() {
        return this.faktur;
    }

    public int getJumlah() {
        return this.jumlah;
    }

}

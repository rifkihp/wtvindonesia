package wtvindonesia.application.com.model;

import java.io.Serializable;

public class detail_tagihan implements Serializable {

    private static final long serialVersionUID = 1L;
    String inquiry, kode, nama;
    double premi, saldo;

    public detail_tagihan(String inquiry, String kode, String nama, double premi, double saldo) {

        this.inquiry = inquiry;
        this.kode = kode;
        this.nama = nama;
        this.premi = premi;
        this.saldo = saldo;
    }

    public String getInquiry() {
        return this.inquiry;
    }

    public String getKode() {
        return this.kode;
    }

    public String getNama() {
        return this.nama;
    }

    public double getPremi() {
        return this.premi;
    }

    public double getSaldo() {
        return this.saldo;
    }

}

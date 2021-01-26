package wtvindonesia.application.com.model;

import java.io.Serializable;

public class tagihan_tv_detail implements Serializable {

    private static final long serialVersionUID = 1L;
    String periode, jatuhTempo, referensi, noref1, noref2, paket;
    double nilaiTagihan, denda, admin, transaksi, fee, total;

    public tagihan_tv_detail(String periode, String jatuhTempo, String referensi, String noref1, String noref2, String paket, double nilaiTagihan, double denda, double admin, double transaksi, double total, double fee) {
        
        this.periode = periode;
        this.jatuhTempo = jatuhTempo;
        this.referensi = referensi;
        this.noref1 = noref1;
        this.noref2 = noref2;
        this.paket = paket;
        this.nilaiTagihan = nilaiTagihan;
        this.denda = denda;
        this.admin = admin;
        this.transaksi = transaksi;
        this.fee = fee;
        this.total = total;
    }

    public String getPeriode() {
        return this.periode;
    }

    public String getJatuhTempo() {
        return this.jatuhTempo;
    }

    public String getReferensi() {
        return this.referensi;
    }

    public String getNoref1() {
        return this.noref1;
    }

    public String getNoref2() {
        return this.noref2;
    }

    public String getPaket() {
        return this.paket;
    }

    public double getNilaiTagihan() {
        return this.nilaiTagihan;
    }

    public double getDenda() {
        return this.denda;
    }

    public double getAdmin() {
        return this.admin;
    }

    public double getTransaksi() {
        return this.transaksi;
    }

    public double getTotal() {
        return this.total;
    }

    public double getFee() {
        return this.fee;
    }

}

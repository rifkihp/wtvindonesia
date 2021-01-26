package wtvindonesia.application.com.model;

import java.io.Serializable;

public class tagihan_listrik_detail implements Serializable {

    private static final long serialVersionUID = 1L;
    String periode;
    double nilaiTagihan, denda, admin, transaksi, total;

    public tagihan_listrik_detail(String periode, double nilaiTagihan, double denda, double admin, double transaksi, double total) {
        
        this.periode = periode;
        this.nilaiTagihan = nilaiTagihan;
        this.denda = denda;
        this.admin = admin;
        this.transaksi = transaksi;
        this.total = total;
    }

    public String getPeriode() {
        return this.periode;
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

}

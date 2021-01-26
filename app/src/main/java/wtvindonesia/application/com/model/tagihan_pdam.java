package wtvindonesia.application.com.model;

import java.io.Serializable;

public class tagihan_pdam implements Serializable {

    private static final long serialVersionUID = 1L;
    String id_pelanggan, customer, periode;
    double tagihan, denda, admin, total;

    public tagihan_pdam(String id_pelanggan, String customer, String periode, double tagihan, double denda, double admin, double total) {
        
        this.id_pelanggan = id_pelanggan;
        this.customer = customer;
        this.periode = periode;
        this.tagihan = tagihan;
        this.denda = denda;
        this.admin = admin;
        this.total = total;
    }

    public String getId_pelanggan() {
        return this.id_pelanggan;
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getPeriode() {
        return this.periode;
    }

    public double getTagihan() {
        return this.tagihan;
    }

    public double getDenda() {
        return this.denda;
    }

    public double getAdmin() {
        return this.admin;
    }

    public double getTotal() {
        return this.total;
    }

}

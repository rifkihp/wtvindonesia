package wtvindonesia.application.com.model;

import java.io.Serializable;

public class tagihan_telkom implements Serializable {

    private static final long serialVersionUID = 1L;
    String no_telepon, customer, periode;
    double tagihan, admin, total;

    public tagihan_telkom(String no_telepon, String customer, String periode, double tagihan, double admin, double total) {
        
        this.no_telepon = no_telepon;
        this.customer = customer;
        this.periode = periode;
        this.tagihan = tagihan;
        this.admin = admin;
        this.total = total;
    }

    public String getNo_telepon() {
        return this.no_telepon;
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

    public double getAdmin() {
        return this.admin;
    }

    public double getTotal() {
        return this.total;
    }

}

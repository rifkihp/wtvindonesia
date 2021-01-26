package wtvindonesia.application.com.model;

import java.io.Serializable;
import java.util.ArrayList;

public class tagihan implements Serializable {

    private static final long serialVersionUID = 1L;
    String inquiry, id_pelanggan, customer, lembar, platform, pemakaian, periode;
    double tagihan, denda, biaya, admin, transaksi, fee, total;
    ArrayList<detail_tagihan> tagihanlist;

    public tagihan(String inquiry, String id_pelanggan, String customer, String lembar, String platform, String pemakaian, String periode, double tagihan, double denda, double biaya, double admin, double transaksi, double fee, double total) {

        this.inquiry = inquiry;
        this.id_pelanggan = id_pelanggan;
        this.customer = customer;
        this.lembar = lembar;
        this.platform = platform;
        this.pemakaian = pemakaian;
        this.periode = periode;
        this.tagihan = tagihan;
        this.denda = denda;
        this.biaya = biaya;
        this.admin = admin;
        this.transaksi = transaksi;
        this.total = total;
        this.fee = fee;
        this.tagihanlist = new ArrayList<>();
    }

    public String getInquiry() {
        return this.inquiry;
    }

    public String getId_pelanggan() {
        return this.id_pelanggan;
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getLembar() {
        return this.lembar;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getPemakaian() {
        return this.pemakaian;
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

    public double getBiaya() {
        return this.biaya;
    }

    public double getAdmin() {
        return this.admin;
    }

    public double getTransaksi() {
        return this.transaksi;
    }

    public double getFee() {
        return this.fee;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTagihanlist(ArrayList<detail_tagihan> tagihanlist) {
        this.tagihanlist = tagihanlist;
    }

    public ArrayList<detail_tagihan> getTagihanlist() {
        return this.tagihanlist;
    }
}

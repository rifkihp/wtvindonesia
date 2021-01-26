package wtvindonesia.application.com.model;

import java.io.Serializable;

public class tagihan_pdam_detail implements Serializable {

    private static final long serialVersionUID = 1L;
    String periode, pemakaian, meterAwal, meterAkhir, tarif, alamat, accountNumber;
    double nilaiTagihan, penalty, tagihanLain,  admin, transaksi, total, fee;

    public tagihan_pdam_detail(String periode, String pemakaian, String meterAwal, String meterAkhir, double nilaiTagihan, double penalty, double tagihanLain, double admin, double transaksi, double total, String tarif, String alamat, String accountNumber, double fee) {
        
        this.periode = periode;
        this.pemakaian = pemakaian;
        this.meterAwal = meterAwal;
        this.meterAkhir = meterAkhir;
        this.nilaiTagihan = nilaiTagihan;
        this.penalty = penalty;
        this.tagihanLain = tagihanLain;
        this.admin = admin;
        this.transaksi = transaksi;
        this.total = total;
        this.tarif = tarif;
        this.alamat = alamat;
        this.accountNumber = accountNumber;
        this.fee = fee;
    }

    public String getPeriode() {
        return this.periode;
    }

    public String getPemakaian() {
        return this.pemakaian;
    }

    public String getMeterAwal() {
        return this.meterAwal;
    }

    public String getMeterAkhir() {
        return this.meterAkhir;
    }

    public double getNilaiTagihan() {
        return this.nilaiTagihan;
    }

    public double getPenalty() {
        return this.penalty;
    }

    public double getTagihanLain() {
        return this.tagihanLain;
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

    public String getTarif() {
        return this.tarif;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getFee() {
        return this.fee;
    }


}

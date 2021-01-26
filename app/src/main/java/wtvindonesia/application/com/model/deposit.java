package wtvindonesia.application.com.model;

import java.io.Serializable;

/**
 * Created by apple on 21/05/16.
 */
public class deposit implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String no_transaksi;
    private String tgl_transaksi;

    private int deposit;
    private int transfer;

    private int id_bank;
    private String no_rekening, nama_pemilik_rekening, nama_bank, cabang;

    private int status;

    public deposit(int id, String no_transaksi, String tgl_transaksi, int deposit, int transfer, int id_bank, String no_rekening, String nama_pemilik_rekening, String nama_bank, String cabang, int status) {
        this.id = id;

        this.no_transaksi = no_transaksi;
        this.tgl_transaksi = tgl_transaksi;

        this.deposit = deposit;
        this.transfer = transfer;

        this.id_bank = id_bank;
        this.no_rekening = no_rekening;
        this.nama_pemilik_rekening = nama_pemilik_rekening;
        this.nama_bank = nama_bank;
        this.cabang = cabang;

        this.status= status;
    }

    public int getId() {
        return this.id;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public String getTgl_transaksi() {
        return this.tgl_transaksi;
    }

    public int getTransfer() {
        return this.transfer;
    }

    public int getDeposit() {
        return this.deposit;
    }

    public int getId_bank() {
        return this.id_bank;
    }

    public String getNo_rekening() {
        return this.no_rekening;
    }

    public String getNama_pemilik_rekening() {
        return this.nama_pemilik_rekening;
    }

    public String getNama_bank() {
        return this.nama_bank;
    }

    public String getCabang() {
        return this.cabang;
    }

    public int getStatus() {
        return this.status;
    }

}



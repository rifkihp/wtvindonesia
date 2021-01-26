package wtvindonesia.application.com.model;

import java.io.Serializable;

public class stok implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ukuran, warna, harga;
    private int qty;

    public stok(String ukuran, String warna, String harga, int qty) {
        this.ukuran = ukuran;
        this.warna = warna;
        this.harga = harga;
        this.qty = qty;
    }

    public String getUkuran() {
        return this.ukuran;
    }

    public String getWarna() {
        return this.warna;
    }

    public String getHarga() {
        return this.harga;
    }

    public int getQty() {
        return this.qty;
    }

}

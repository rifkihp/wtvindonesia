package wtvindonesia.application.com.model;

import java.io.Serializable;

public class token_listrik implements Serializable {

    private static final long serialVersionUID = 1L;
    String code, nama;
    double harga;

    public token_listrik(String code, String nama, double harga) {
        
        this.code = code;
        this.nama = nama;
        this.harga = harga;
    }

    public String getCode() {
        return this.code;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

}

package wtvindonesia.application.com.model;

import java.io.Serializable;

public class pdam implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String kode, nama;

    public pdam(int id, String kode, String nama) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
    }

    public int getId() {
        return this.id;
    }

    public String getKode() {
        return this.kode;
    }

    public String getNama() {
        return this.nama;
    }

}

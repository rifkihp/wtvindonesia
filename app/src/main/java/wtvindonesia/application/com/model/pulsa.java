package wtvindonesia.application.com.model;

import java.io.Serializable;

public class pulsa implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    int price, transaksi, fee, jual;
    private String code, provider, provider_sub, description, logo;

    public pulsa(int id, String provider, String provider_sub, String code, String description, int price, int transaksi, int fee, int jual, String logo) {
        this.id = id;
        this.code = code;
        this.provider = provider;
        this.provider_sub= provider_sub;
        this.description = description;
        this.price = price;
        this.transaksi = transaksi;
        this.fee = fee;
        this.jual = jual;
        this.logo = logo;
    }

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getProvider_sub() {
        return this.provider_sub;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public int getTransaksi() {
        return this.transaksi;
    }

    public int getFee() {
        return this.fee;
    }

    public int getJual() {
        return this.jual;
    }

    public String getLogo() {
        return this.logo;
    }

}

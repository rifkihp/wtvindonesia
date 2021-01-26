package wtvindonesia.application.com.model;

import java.io.Serializable;
import java.util.ArrayList;

public class tagihan_listrik implements Serializable {



    private static final long serialVersionUID = 1L;
    String subscriberID, nama, tarif, daya, productCode, refID;
    int lembarTagihanTotal, lembarTagihan, lembarTagihanSisa;
    ArrayList<tagihan_listrik_detail> detilTagihan;
    double totalTagihan;

    public tagihan_listrik(String subscriberID, String nama, String tarif, String daya, int lembarTagihanTotal, int lembarTagihan, int lembarTagihanSisa, ArrayList<tagihan_listrik_detail> detilTagihan, double totalTagihan, String productCode, String refID) {
        
        this.subscriberID = subscriberID;
        this.nama = nama;
        this.tarif = tarif;
        this.daya = daya;
        this.lembarTagihanTotal = lembarTagihanTotal;
        this.lembarTagihan = lembarTagihan;
        this.lembarTagihanSisa = lembarTagihanSisa;
        this.detilTagihan = detilTagihan;
        this.totalTagihan = totalTagihan;
        this.productCode = productCode;
        this.refID = refID;

    }

    public String getSubscriberID() {
        return this.subscriberID;
    }

    public String getNama() {
        return this.nama;
    }

    public String getTarif() {
        return this.tarif;
    }

    public String getDaya() {
        return this.daya;
    }

    public int getLembarTagihanTotal() {
        return this.lembarTagihanTotal;
    }

    public int getLembarTagihan() {
        return this.lembarTagihan;
    }

    public int getLembarTagihanSisa() {
        return this.lembarTagihanSisa;
    }

    public ArrayList<tagihan_listrik_detail> getDetilTagihan() {
        return this.detilTagihan;
    }

    public double getTotalTagihan() {
        return this.totalTagihan;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public String getRefID() {
        return this.refID;
    }


}

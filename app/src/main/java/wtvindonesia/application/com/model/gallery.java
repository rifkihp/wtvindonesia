package wtvindonesia.application.com.model;

import java.io.Serializable;

/**
 * Created by apple on 21/05/16.
 */
public class gallery implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id, id_owner, like;
    private String keterangan, owner, gambar, photo;
    private boolean is_favorite;

    public gallery(int id, String keterangan, String gambar) {
        this.id = id;
        this.keterangan = keterangan;
        this.gambar = gambar;
        this.id_owner = 0;
        this.owner = "";
        this.photo = "default.png";
        this.like = 0;
        this.is_favorite = false;
    }

    public int getId() {
        return this.id;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public String getGambar() {
        return this.gambar;
    }

    public void setOwner(int id, String nama, String photo) {
        this.id_owner = id;
        this.owner = nama;
        this.photo = photo;
    }
    
    public int getId_owner() {
        return this.id_owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return this.like;
    }

    public void setFavorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public boolean getFavorite() {
        return this.is_favorite;
    }
}



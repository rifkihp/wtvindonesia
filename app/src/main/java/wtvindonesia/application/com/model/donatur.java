package wtvindonesia.application.com.model;

import java.io.Serializable;

public class donatur implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nama, tanggal, photo, keterangan;
	private int jumlah;

	public donatur(int id, String nama, String tanggal, String photo, String keterangan, int jumlah) {
		this.id    = id;
		this.nama  = nama;
		this.tanggal  = tanggal;
		this.photo = photo;
		this.keterangan = keterangan;
		this.jumlah = jumlah;
	}

	public int getId() {
		return this.id;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public String getTanggal() {
		return this.tanggal;
	}

	public String getPhoto() {
		return this.photo;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public int getJumlah() {
		return this.jumlah;
	}
}

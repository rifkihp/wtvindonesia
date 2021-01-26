package wtvindonesia.application.com.model;

import java.io.Serializable;

public class kategori implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	String nama, penjelasan, header;
	boolean checked;
	String kode_trx;
	String tipe_trx;
	
	public kategori(int id, String nama, String penjelasan, String header) {
		this.id = id;
		this.nama = nama;
		this.penjelasan = penjelasan;
		this.header = header;
		this.checked = false;
		this.kode_trx = "";
		this.tipe_trx = "";
	}

	public kategori(int id, String nama, boolean checked) {
		this.id = id;
		this.nama = nama;
		this.checked = checked;
	}

	public int getId() {
		return this.id;
	}

	public String getNama() {
		return this.nama;
	}

	public String getPenjelasan() {
		return this.penjelasan;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return this.header;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public String getKode_trx() {
		return this.kode_trx;
	}

	public void setKode_trx(String kode_trx) {
		this.kode_trx = kode_trx;
	}

	public String getTipe_trx() {
		return this.tipe_trx;
	}

	public void setTipe_trx(String tipe_trx) {
		this.tipe_trx = tipe_trx;
	}
}

package wtvindonesia.application.com.model;

import java.io.Serializable;

public class shortcut implements Serializable {
	private static final long serialVersionUID = 1L;
	int id, direction;
	String nama, icon;
	
	public shortcut(int id, String nama, int direction, String icon) {
		this.id = id;
		this.nama = nama;
		this.direction = direction;
		this.icon = icon;
	}

	public int getId() {
		return this.id;
	}

	public String getNama() {
		return this.nama;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return this.icon;
	}

}

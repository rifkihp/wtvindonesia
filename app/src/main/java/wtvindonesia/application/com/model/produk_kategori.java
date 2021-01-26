package wtvindonesia.application.com.model;

import java.util.ArrayList;

public class produk_kategori {
	int id;
	String nama;
	ArrayList<news> news_list;
	ArrayList<gallery> kartagram_list;

	int height;
	int next_page;

	public produk_kategori(int id, String nama, ArrayList<news> news_list, ArrayList<gallery> kartagram_list, int next_page) {
		this.id = id;
		this.nama = nama;
		this.news_list = news_list;
		this.kartagram_list = kartagram_list;
		this.next_page = next_page;
	}

	public int getId() {
		return this.id;
	}

	public String getNama() {
		return this.nama;
	}

	public ArrayList<news> getNews_list() {
		return this.news_list;
	}

	public void setNews_list(ArrayList<news> news_list) {
		this.news_list = news_list;
	}

	public ArrayList<gallery> getKartagram_list() {
		return this.kartagram_list;
	}

	public void setKartagram_list(ArrayList<gallery> kartagram_list) {
		this.kartagram_list = kartagram_list;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height=height;
	}

	public int getNext_page() {
		return this.next_page;
	}

	public void setNext_page(int next_page) {
		this.next_page=next_page;
	}

}

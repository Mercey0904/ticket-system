package view;

import javafx.scene.control.cell.PropertyValueFactory;
import model.Member;

public class AllTableView {

	private String date;
	private int iD;
	private int noTicket;
	private String hP;
	private int totalPrice;
	public AllTableView(String date, int iD, int noTicket, String hP, int totalPrice) {
		super();
		this.date = date;
		this.iD = iD;
		this.noTicket = noTicket;
		this.hP = hP;
		this.totalPrice = totalPrice;
	}
	public String getDate() {
		return date;
	}

	public int getID() {
		return iD;
	}

	public int getNoTicket() {
		return noTicket;
	}

	public String getHP() {
		return hP;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}

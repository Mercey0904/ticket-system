package model;
import view.*;
import controller.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Transaction {
	private int id;
	private String date;
	private int number;
	private Member[] mem;
	private int price;

	public Transaction(int id, String date, int number, Member[] mem, int price) {
		this.id = id;
		this.date = date;
		this.number = number;
		this.mem = mem;
		this.price = price;
	}
	//get id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		
	}
	public String getDate() {
		return date;
	}
	//check date 
	public boolean setDate(String date) {
		if (date.length()<6 |date.length()>6)
			return false;
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date date2 = new Date();
		String dFormat = dateFormat.format(date2);

		int tDay = Integer.parseInt(dFormat.substring(0,2));
		int tMonth = Integer.parseInt(dFormat.substring(2,4));
		int tYear = Integer.parseInt(dFormat.substring(4,6));

		int day = Integer.parseInt(date.substring(0,2));
		int month = Integer.parseInt(date.substring(2,4));
		int year = Integer.parseInt(date.substring(4,6));

		if (year == tYear) {
			if (month >= tMonth & month<=12) {
				switch (month) {
				case 4,6,9,11: if (day>30 ) return false; break;
				case 1,3,5,7,8,10,12: if (day > 31 ) return false; break;
				case 2: if (year %4 ==0 && day >29) return false;
				default: return false; }
			}
			else if (month == tMonth & day>=tDay) {
					switch (month) {
					case 4,6,9,11: if (day>30 ) return false; break;
					case 1,3,5,7,8,10,12: if (day > 31 ) return false; break;
					case 2: if (year %4 ==0 && day >29) return false;
					default: return false; }}
				else 
					return false;


		}else if (year>tYear) {
			if (day <1 || day >31)
				return false;
					switch (month) {
						case 4,6,9,11: if (day > 30 ) return false; break;
						case 1,3,5,7,8,10,12: if (day > 31 ) return false; break;
						case 2: if (year %4 ==0 && day >29) return false;
								if (day > 28) return false;	break;
						default: return false;}	
					if (year<tYear) 
						return false;
		}
		return true;			
	}
	
	public int getNumber() {
		return number;
	}
	//check if number is positive
	public boolean setNumber(int number) {
		if (number<0)
			return false;
		else 
			return true;
	}
	public Member[] getMembers() {
		return mem;
	}
	public void setMembers(Member[] mem) {
	}
	public int getPrice() {
		return price;
	}
	//check price
	public boolean setPrice(int price) {
		if (price<0)
			return false;
		else 
			return true;
	}
	//string to string
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", number=" + number + ", mem=" + Arrays.toString(mem)
		+ ", price=" + price + "]";
	}


}
package controller;


public class DateProcessing {
	//extract day from date
	public static int getDD (String date) {
		int dd =0;
		String temp ="";
		temp = date.substring(0,2);
		dd= Integer.parseInt(temp);
		return dd;
	}
	//extract month from date
	public static int getMM (String date) {
		int mm =0;
		String temp ="";
		temp = date.substring(2,4);
		mm= Integer.parseInt(temp);
		return mm;
	}
	//extract year from date
	public static int getYY (String date) {
		int yy =0;
		String temp ="";
		temp = date.substring(4,6);
		yy= Integer.parseInt(temp);
		return yy;
	}
}

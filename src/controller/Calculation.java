package controller;
import view.*;
//count total price
public class Calculation {
	static int countTicket;
	public int TotalPrice(int noMember, int ticketQty) {
		int noNonMember = (ticketQty - noMember);
		int sum = ((noMember*80)+(noNonMember *100));
		return sum;
	}
	//count ticket limit everyday
	public boolean countTotalTicket (int ticketQty) {
		if (countTicket >= 100)
			return false;
		else
			countTicket += ticketQty;
		return true;
	}
}

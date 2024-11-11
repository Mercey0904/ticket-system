package view;

public class MemberTableView {
	 private String ticketDate;
	    private int transactionId;
	    private int totalPrice;

	    public MemberTableView(String ticketDate, int transactionId, int totalPrice) {
	        this.ticketDate = ticketDate;
	        this.transactionId = transactionId;
	        this.totalPrice = totalPrice;
	    }

	    public String getTicketDate() {
	        return ticketDate;
	    }

	    public int getTransactionId() {
	        return transactionId;
	    }

	    public int getTotalPrice() {
	        return totalPrice;
	    }

		
	}

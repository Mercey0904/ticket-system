//1
package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.control.TextField;

import javafx.stage.Stage;
import model.Transaction;
import view.LaunchWindow;
import view.MsgDialog;
import controller.*;


public class TicketTransactionController {
	public static String sdate = " ";
	public static int sMemberQty = 0;
	public static int sTicketQty = 0;
	public static int sTicketId = 0;
	public static int dd = 0;
	public static int mm = 0;
	public static int yy = 0;
	public static String sPhoneNum = " ";
	int totalPrice =0;
	String msg ="";
	boolean success = false;
	boolean success2 = false;
	DBController db;
	Calculation c;
	int cnt =0;
	Transaction t = new Transaction (sTicketId, sdate, sTicketQty, null, 0 );
	//Transaction t = new Transaction (0, sdate, 0, null, 0 );
	boolean valid = true;
	
	@FXML
	private TextField date;

	@FXML
	private TextField noMember;

	@FXML
	private TextField noTicket;

	@FXML
	private TextField ticketId;

	@FXML
	void moveOm(ActionEvent event) throws Exception {
		db = new DBController();
		c = new Calculation();
		sdate = date.getText();
		success = c.countTotalTicket(sTicketQty);
		valid = t.setDate (sdate);
		//check date
		dd = DateProcessing.getDD(sdate);
		mm = DateProcessing.getMM(sdate);
		yy = DateProcessing.getYY(sdate);
		if (!valid|!success) {
			MsgDialog.displayDialog("Invalid Date", "Invalid Date. Please enter again");
			//System.out.println(valid);
			date.clear();
			noMember.clear();
			noTicket.clear();
			return;

		}
		//check member qty
		sMemberQty = Integer.parseInt (noMember.getText());
		valid = t.setNumber (sMemberQty);
		if (!valid) {
			MsgDialog.displayDialog("Invalid Member Quantity", "Invalid Number of Member. Please enter again");
			//System.out.println(valid);
			//clear();
			date.clear();
			noMember.clear();
			noTicket.clear();
			return;

		}
		//check ticket qty
		sTicketQty = Integer.parseInt (noTicket.getText());
		valid = t.setNumber (sTicketQty);
		//System.out.println(valid);
		if (!valid| sMemberQty>sTicketQty) {
			MsgDialog.displayDialog("Invalid Ticket Quantity", "Invalid Ticket Quantity. Please enter again");
			//System.out.println(valid);
			//clear();
			date.clear();
			noMember.clear();
			noTicket.clear();
			return;
		}
		valid = t.setNumber(sTicketQty);
		//System.out.println(valid);
		if (!valid) {
			MsgDialog.displayDialog("Ticket Sold Out", "Ticket sold out, please select another date");
		}

		//insert record
		else {
			if (sMemberQty ==0){
				sPhoneNum = "-";
				totalPrice = c.TotalPrice(sMemberQty, sTicketQty);
				success = db.insertTransaction(TicketTransactionController.sdate,TicketTransactionController.sTicketId, sPhoneNum, totalPrice);
				success2 = db.insertTransactionReport(sdate, sTicketId, sTicketQty, totalPrice, dd, mm, yy);
				if (success == true & success2 ==true) {
					msg = "Record sucessfully inserted. Your total price is RM " + totalPrice;
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					new LaunchWindow().display(stage,"Main Menu ", "MainMenu.fxml");
					MsgDialog.displayDialog("Total Price and Input Successful", msg);
					stage.close();
				}
				else if (success == false | success2 == false)
					MsgDialog.displayDialog("Input Failed", "Error inserting a record. Please try again");
			}
			else {
				for (int cnt=0 ; cnt< sMemberQty; cnt++) {
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					new LaunchWindow().display(stage,"Ticket Transaction 2 ", "AddTicket2.fxml");
					
				}
			}
			}
		}

	//intialize auto generated id
	public void initialize()  throws Exception{
		DBController db = new DBController();
		sTicketId = db.AutoGenerateId();
		ticketId.setText(Integer.toString(sTicketId));
	}

}




package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import view.LaunchWindow;
import view.MsgDialog;
import view.controller.TicketTransactionController;
import controller.*;

public class TicketTransactionController2 {
	String sPhoneNum ;
	boolean valid = false;
	boolean valid2 = false;
	boolean valid3 = false;
	boolean success = false;
	boolean success2 = false;
    int totalPrice = 0;
    int count = 0;
    String msg;
    DBController db = new DBController();
    Validation va = new Validation();
    Calculation c = new Calculation();
    Member m = new Member (null, sPhoneNum);
    
  
    public TicketTransactionController2() throws Exception{
    }

	@FXML
    private TextField phoneNum;
    
    @FXML
    void check(ActionEvent event) throws Exception {
    	//collect phone number and check format
    	sPhoneNum = phoneNum.getText();
    	valid = m.setHp(sPhoneNum);
    	valid2 = va.isPhoneNumberExist(sPhoneNum);
		valid3 =va.isOneTicket(0,sPhoneNum);
		if (!valid|valid2) {
    		MsgDialog.displayDialog("Invalid Phone Number", "Invalid Phone Number. Please enter again");
			phoneNum.clear();}
		else if (!valid3) {
			MsgDialog.displayDialog("Repeated Phone Number", "Repeated Phone Number. Please enter again");
			phoneNum.clear();}
		else 
			totalPrice = c.TotalPrice(TicketTransactionController.sMemberQty, TicketTransactionController.sTicketQty);
			msg = "Your total price is RM " + totalPrice + "Please click continue to store into database";
			MsgDialog.displayDialog("Total Price and Continue", msg);
			
			//close window, obtain number of window after all window close return to main menu
			    }
    	

    @FXML
    void next(ActionEvent event) throws Exception{
    //insert data to database
    	totalPrice = c.TotalPrice(TicketTransactionController.sMemberQty, TicketTransactionController.sTicketQty);
    	count = TicketTransactionController.sMemberQty;
    	success = db.insertTransaction(TicketTransactionController.sdate,TicketTransactionController.sTicketId, sPhoneNum, totalPrice);
    	success2 = db.insertTransactionReport(TicketTransactionController.sdate,TicketTransactionController.sTicketId,TicketTransactionController.sTicketQty,totalPrice, 
    										  TicketTransactionController.dd, TicketTransactionController.mm, TicketTransactionController.yy);
    	if (success == true & success2 ==true) {
			msg = "Record sucessfully inserted. Your total price is RM " + totalPrice;
			MsgDialog.displayDialog("Total Price and Input Successful", msg);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
			count--;
			
    		}
		else if (success == false | success2 == false)
			MsgDialog.displayDialog("Input Failed", "Error inserting a record. Please try again");
    	//return main menu
		if (count ==0) {
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		new LaunchWindow().display(stage,"Main Menu", "MainMenu.fxml");
    	}
		
    }

}

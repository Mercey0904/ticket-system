//2
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

public class ModifyTicketController {
	String sdate = " ";
	int sMemberQty = 0;
	int sTicketQty = 0;
	int sTicketId = 0;
	int dd = 0;
	int mm = 0;
	int yy = 0;
	Validation va;
	Calculation c;
	DBController db;
	Transaction t = new Transaction (sTicketId, sdate, sTicketQty, null, 0  );
	boolean valid = true, valid2 = true;
	boolean success = false;
	

    public ModifyTicketController() throws Exception{
		va = new Validation();
		c = new Calculation();
		db = new DBController();
	}

	@FXML
    private TextField date;

    @FXML
    private TextField ticketId;

    @FXML
    void next(ActionEvent event) throws Exception {
    	//check date
    	sdate = date.getText();
    	valid = t.setDate(sdate);
    	valid2 = c.countTotalTicket(sTicketQty);
    	
    	if (!valid) {
			MsgDialog.displayDialog("Invalid Date", "Invalid Date. Please enter again");
			//System.out.println(valid);
			date.clear();
			return;
    	}
    	//check id
    	sTicketId = Integer.parseInt(ticketId.getText());
    	valid = va.isExistId(sTicketId);
    	if (!valid)
    		MsgDialog.displayDialog("Invalid ID", "Your ID is incorrect. Please enter again");
    	else {
    		
    		//update database
    		success = db.updateRecord(sdate, sTicketId, dd, mm, yy);
    		dd = DateProcessing.getDD(sdate);
    		mm = DateProcessing.getMM(sdate);
    		yy = DateProcessing.getYY(sdate);
    		//close window return main
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.close();
    		new LaunchWindow().display(stage,"Main Menu ", "MainMenu.fxml");
    		if (success == true) {
    			MsgDialog.displayDialog("Input Successful", "Your update has been stored");}
    		else
    			MsgDialog.displayDialog("Input Failed", "Error inserting your record");
    	}
    	
    }

}


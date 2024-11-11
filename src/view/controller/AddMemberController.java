//4
package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LaunchWindow;
import view.MsgDialog;
import model.Member;
import controller.*;


public class AddMemberController {
	String sName = " ";
	String sPhoneNum = " ";
	boolean valid = false, valid2 = false;
	boolean success = true;
	Member m = new Member(sName,sPhoneNum);
	Validation va;
	DBController db;
	@FXML
	private TextField name;

	@FXML
	private TextField phoneNumber;

	public AddMemberController() throws Exception {
		va = new Validation();
		db = new DBController();
	}

	@FXML
	void next(ActionEvent event) throws Exception {
		//extract name and compare format
		sName = name.getText();
		valid = m.setName(sName);
		if (!valid) {
			MsgDialog.displayDialog("Invalid Name", "Invalid name format. Please enter again");
			phoneNumber.clear();
			return;
		}
		//extract phone number and compare format
		sPhoneNum = phoneNumber.getText();
		valid = m.setHp(sPhoneNum);
		valid2 = va.isPhoneNumberExist(sPhoneNum);
		if (!valid|!valid2) {
			MsgDialog.displayDialog("Invalid Phone Number", "Invalid phone numer. Please enter again");
			phoneNumber.clear();
			return;
		}
		else {
			//insert into database
			success = db.insertNewMember(sName, sPhoneNum);
			//close window
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
			//launch main menu
			new LaunchWindow().display(stage,"Main Menu ", "MainMenu.fxml");
			if (success == true) {
				MsgDialog.displayDialog("Input Successful", "Your update has been stored");}
			else
				MsgDialog.displayDialog("Input Failed", "Error inserting your record");
		}

	}

}


//5
package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Member;
import model.Transaction;
import view.LaunchWindow;
import view.MsgDialog;
import view.MemberTableView;
import controller.*;

public class MemberRecordsController {
	
    @FXML
    private TableView<MemberTableView> memberTable;

    @FXML
    private TextField phoneNumber;
    String sPhoneNum = "";
    public static String sdate = " ";
	public static int sMemberQty = 0;
	public static int sTicketQty = 0;
	public static int sTicketId = 0;
	public static int sTicketPrice = 0;
	public static String sName = " ";
	boolean valid = false, valid2 = false;
	
	Member m = new Member(null,sPhoneNum);
	Validation va;
	DBController db = new DBController();
	
    public MemberRecordsController() throws Exception {
		va = new Validation();
	}
    @FXML
    private TextField finalPH;
    
    @FXML
    private TextField name;
	@FXML
    private TableColumn<MemberTableView, String> tcDate;

    @FXML
    private TableColumn<MemberTableView, Integer> tcID;

    @FXML
    private TableColumn<MemberTableView, Integer> tcPrice;


	@FXML
    void generate(ActionEvent event) throws Exception {
		//register table columns with MemberTableView variable
		memberTable.getItems().clear();
		tcDate.setCellValueFactory(new PropertyValueFactory("ticketDate"));
    	tcID.setCellValueFactory(new PropertyValueFactory("transactionId"));
    	tcPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
    	//check phone number
		sPhoneNum = phoneNumber.getText();
		valid = m.setHp(sPhoneNum);
		valid2 = va.isPhoneNumberExist(sPhoneNum);
		
		if (!valid)
			MsgDialog.displayDialog("Invalid Phone Number", "Invalid phone number. Please enter again");
		if (valid2)
			MsgDialog.displayDialog("Invalid Phone Number", "Invalid phone number. This phone number is not a member. Please enter again");
		else {
			
    	try {
    		sName = db.viewMemberDetails(sPhoneNum);
    		name.setText(sName);
    		finalPH.setText(sPhoneNum);
    		Transaction arr[]= db.viewMemberReport(sPhoneNum);
    		
    		if (arr!= null) {
    			for (int cnt=0; cnt<arr.length; cnt++) {
    				int id = arr[cnt].getId();
    				String date = arr[cnt].getDate();
    				int price= arr[cnt].getPrice();
    				MemberTableView mem = new MemberTableView(date, id, price);
    				memberTable.getItems().add(mem);
    		}
    	} else
    		MsgDialog.displayDialog("error caught", "No record found in the database");
    	} catch (Exception ex) {
    		MsgDialog.displayDialog("error caught", "Error retrieve records in the database");
    	}
		}
	
    }
//return main menu
    @FXML
    void next(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new LaunchWindow().display(stage,"Main Menu", "MainMenu.fxml");
    }

}

//3

package view.controller;

import controller.DBController;
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
import view.AllTableView;



public class AllRecordController {
	DBController db;
	String sPhoneNum = "";
    public static String sdate = " ";
	public static int sMemberQty = 0;
	public static int sTicketQty = 0;
	public static int sTicketId = 0;
	public static int sTicketPrice = 0;
	boolean valid = false;
	Transaction t = new Transaction (sTicketId, sdate, sTicketQty, null, 0 );
    @FXML
    private TableView<AllTableView> allTable;

    @FXML
    private TextField date;

    @FXML
    private TableColumn<AllTableView, String> tcHp;

    @FXML
    private TableColumn<AllTableView, Integer> tcNoTicket;

    @FXML
    private TableColumn<AllTableView, String> tcTicketDate;

    @FXML
    private TableColumn<AllTableView, Integer> tcTotalPrice;

    @FXML
    private TableColumn<AllTableView, Integer> tcTransactionID;
    

    public AllRecordController() throws Exception {
		db = new DBController();
	}

	@FXML
    void generate(ActionEvent event) {
		//clear table, extract date, compare date format
		allTable.getItems().clear();
		sdate = date.getText();
		valid =t.setDate(sdate);
		if (!valid) {
			MsgDialog.displayDialog("Invalid Date", "Invalid Date. Please enter again");
			date.clear();
		}
		//register table columns with AllTableView variable
        tcTicketDate.setCellValueFactory(new PropertyValueFactory("date"));
        tcTransactionID.setCellValueFactory(new PropertyValueFactory("iD"));
        tcNoTicket.setCellValueFactory(new PropertyValueFactory("noTicket"));
        tcHp.setCellValueFactory(new PropertyValueFactory("hP"));
        tcTotalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        //connect to database
        try {
            Transaction arr[] = db.getAllTransactionFromDate(sdate);
            //print data 
            if (arr != null) {
                for (int cnt=0; cnt<arr.length; cnt++) {
                    int id = arr[cnt].getId();
                    int ticketNum = arr[cnt].getNumber();
                    String date = arr[cnt].getDate();
                    int price = arr[cnt].getPrice();
                    Member[] Hp = arr[cnt].getMembers();
                    StringBuilder sp = new StringBuilder();
                    //extract member array, brake it down to phone number only
                    for (Member member : Hp) {
                        sp.append(member.toString2());
                        sp.append(", ");
                    }
                    
                    if (sp.length() > 2) {
                        sp.setLength(sp.length() - 2);
                    }
                    AllTableView all = new AllTableView (date, id, ticketNum, sp.toString(), price);
                    //AllTableView all = new AllTableView (date, id, ticketNum, Hp, price);
                    allTable.getItems().add(all);
                }
            } else {
                MsgDialog.displayDialog("error caught", "No record found in the database");
            }
        } catch (Exception ex) {
            MsgDialog.displayDialog("error caught", "Error retrieve records in the database");
        }
    }

    @FXML
    void main(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"Main Menu", "MainMenu.fxml");
    }
}
package view;
//to launch error text
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MsgDialog {
	static Alert alert;
	
	public static void displayDialog (String header, String msg) {
		alert = new Alert(AlertType.INFORMATION); 
		alert.setTitle(header);
		alert.setContentText(msg);
		alert.show();
	}
}

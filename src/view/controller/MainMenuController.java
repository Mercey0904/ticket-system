package view.controller;
//1
import view.LaunchWindow;
import view.MsgDialog;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainMenuController {
	MediaPlayer player = null;
    @FXML
    private MediaView mediaView;
    //ticket transaction
    @FXML
    void buy(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"1. New Ticket Transaction ", "AddTicket1.fxml");
    }
    //exit
    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"Thank You ", "ThankU.fxml");
		    }
    //new member registration
    @FXML
    void register(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"4. New Member Registration", "AddMember.fxml");
    }
    //update ticket
    @FXML
    void update(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"2. Modify an Ticket Transaction", "ModifyTicket.fxml");
    }
    //see all records
    @FXML
    void viewAll(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"3.View All Records", "AllRecord.fxml");
    }
    //see all member
    @FXML
    void viewMember(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new LaunchWindow().display(stage,"5. View All Member Transaction", "MemberRecord.fxml");
    }
    //play video
    public void initialize() {
    	URL url = null;
    	Media media = null;
    	
    	url = this.getClass().getResource("../../media/video.mp4");
    	media = new Media (url.toString());
    	player = new MediaPlayer (media);
    	mediaView.setMediaPlayer(player);
    	
    	player.play();
    	
 
    }

}

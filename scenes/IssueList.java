//Class which handle the functionality and view for Issue List Scene
package scenes;
import Model.Issues;
import issuetracker.DataAccess;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IssueList {

    public Scene getScene(Stage primaryStage, String user, String username) {
        //add title label
        Label lblTitle = new Label("Issue Tracker List");
        lblTitle.setStyle("-fx-text-fill: red; -fx-font: normal bold 35px 'serif'; ");
        
        //create an arraylist and initialize by getting data from data access class
        ArrayList<Issues> issues = null;
        try {
            issues = new DataAccess().getIssues();
        } catch (Exception e) {
            System.out.println(e);
        }
        // create an array list for showing in list view
        final ArrayList<Issues> finalIssues = issues;
        ObservableList<String> names = FXCollections.observableArrayList();
        
        //getting data and store in names according to user types
        if (user.equals("developer")) {
            for (int i = 0; i < issues.size(); i++) {
                try{
                    if (issues.get(i).getAssignTo().equalsIgnoreCase(username)) {
                    names.add(issues.get(i).getName());
                }
                }catch(Exception e){
                    
                }
            }
        } else if(user.equals("user")) {
            for (int i = 0; i < issues.size(); i++) {
                names.add(issues.get(i).getName());
            }
        }
        else if(user.equals("manager")){
            for (int i = 0; i < issues.size(); i++) {
                names.add(issues.get(i).getName());
            }
        }
        
        //create label and list view
        Label lblIssueList = new Label("Issue List: ");
        lblIssueList.setStyle("-fx-text-fill: black; -fx-font: normal bold 20px 'serif'; ");
        ListView<String> issueList = new ListView<String>();
        issueList.setItems(names);
        issueList.setMaxHeight(400);
        issueList.setStyle("-fx-text-fill: black; -fx-font: normal bold 20px 'serif'; ");
        
        //event on mouse click on list view
        issueList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
                String itemSelected = issueList.getSelectionModel().getSelectedItem();
                for(int i=0; i<finalIssues.size();i++){
                    //check selected item and show all details in new issue scene
                    if(finalIssues.get(i).getName().equals(itemSelected)){
                        primaryStage.setScene(new NewIssue().getScene(primaryStage, user, finalIssues.get(i).getStatus(), i, username));
                    }
                }
                
            }
        });
        
        //hbox for adding label and list view
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10, 50, 50, 60));
        hbox.getChildren().addAll(lblIssueList, issueList);
        hbox.setAlignment(Pos.CENTER);
        
        //create buttons and set styles
        Button btnNewIssue = new Button("New Issue");
        Button btnAddUser = new Button("Add User");
        btnNewIssue.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif'; ");
        btnAddUser.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif'; ");
        
        //action event on add user button
        btnAddUser.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //set scene to add user scene
                primaryStage.setScene(new AddUser().getScene(primaryStage, user, username));
            }
        });
        
        //action event on new issue button
        btnNewIssue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //set scene to new issue scene
                primaryStage.setScene(new NewIssue().getScene(primaryStage, user,"createNew",0, username));
            }
        });
        //hbox for adding buttons
        HBox hbButton = new HBox();
        hbButton.setAlignment(Pos.CENTER);
        hbButton.setSpacing(30);
        //add newIssue, addUser button in hbox if user type is manager
        if (user.equals("manager")) {
            hbButton.getChildren().addAll(btnNewIssue, btnAddUser);
            
        }
        // add newIssue button for other types
        else {
            hbButton.getChildren().addAll(btnNewIssue);
        }
        
        //add vbox for making a scene and set style
        VBox vbPane = new VBox();
        vbPane.setAlignment(Pos.CENTER);
        vbPane.setSpacing(30);
        vbPane.getChildren().addAll(lblTitle, hbox, hbButton);
        vbPane.setStyle("-fx-background-color: BEIGE; -fx-alignment: center ;");
        
        //create a scene and return it 
        Scene scene = new Scene(vbPane, 600, 700);
        return scene;
    }
}

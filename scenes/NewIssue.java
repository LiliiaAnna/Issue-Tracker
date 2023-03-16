//Class which handle the functionality and view for New Issue Scene
package scenes;

import Model.Issues;
import Model.Users;
import issuetracker.DataAccess;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NewIssue {

    public Scene getScene(Stage primaryStage, String user, String action, int index, String username) {
        //Adding labels and set their styles
        Label lblTitle = new Label("Issue Tracker Issue Details");
        lblTitle.setStyle("-fx-text-fill: blue; -fx-font: normal bold 40px 'serif'  ");
        Label lblName = new Label("Name: ");
        lblName.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblEnv = new Label("Environment: ");
        lblEnv.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblType = new Label("Type: ");
        lblType.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblDetails = new Label("Details: ");
        lblDetails.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblStatus = new Label("Status: ");
        lblStatus.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red; -fx-font: normal bold 15px 'serif'  ");
        lblError.setVisible(true);

        //adding textfields
        TextField txtName = getTextField();
        TextField txtEnv = getTextField();
        TextField txtType = getTextField();
        TextField txtDetails = getTextField();
        TextField txtStatus = getTextField();

        //creating buttons
        Button btnCancel = getButton("Cancel");
        Button btnSubmit = getButton("Submit");
        Button btnAssign = getButton("Assign");
        Button btnClose = getButton("Close");
        Button btnValidate = getButton("Validate");
        Button btnFail = getButton("Fail");
        Button btnOpen = getButton("Open");
        Button btnReject = getButton("Reject");
        Button btnResolve = getButton("Resolve");

        //add gridpane and add labels and textfields
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblName, 0, 0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(lblEnv, 0, 1);
        gridPane.add(txtEnv, 1, 1);
        gridPane.add(lblType, 0, 2);
        gridPane.add(txtType, 1, 2);
        gridPane.add(lblDetails, 0, 3);
        gridPane.add(txtDetails, 1, 3);
        gridPane.add(lblStatus, 0, 4);
        gridPane.add(txtStatus, 1, 4);
        gridPane.add(lblError, 1, 6);

        //create hbox and add cancel button
        HBox hbButton = new HBox();
        hbButton.setSpacing(20);
        hbButton.setAlignment(Pos.CENTER);
        hbButton.getChildren().add(btnCancel);

        //create a combobox for showing developers and set style
        ComboBox cbDevelopers = new ComboBox();
        cbDevelopers.setStyle("-fx-text-fill: black; -fx-font: normal bold 15px 'serif'; ");

        //Create an arraylist of issues and initialize by getting data from file
        ArrayList<Issues> issues = new DataAccess().getIssues();

        //set text to textfields from list on specific selected issue
        txtName.setText(issues.get(index).getName());
        txtEnv.setText(issues.get(index).getEnv());
        txtType.setText(issues.get(index).getType());
        txtDetails.setText(issues.get(index).getDetails());
        txtStatus.setText(issues.get(index).getStatus());

        //conditions for adding buttons on the scene
        //condition for every user if he want to create a new isse
        if ((user.equals("user") && action.equalsIgnoreCase("createNew")) || (user.equals("developer") && action.equalsIgnoreCase("createNew")) || (user.equalsIgnoreCase("manager") && action.equals("createNew"))) {
            lblTitle.setText("Issue Tracker New Issue");
            //making textfields editable and empty
            txtName.setEditable(true);
            txtEnv.setEditable(true);
            txtType.setEditable(true);
            txtDetails.setEditable(true);
            txtName.setText("");
            txtEnv.setText("");
            txtType.setText("");
            txtDetails.setText("");
            txtStatus.setVisible(false);
            lblStatus.setVisible(false);
            //add submit button to hbox
            hbButton.getChildren().addAll(btnSubmit);
        }
        //condition if user type is manager and issue status is new or rejected
        if (user.equals("manager") && (action.equalsIgnoreCase("new") || action.equalsIgnoreCase("rejected"))) {
            //add assign button to hbox
            hbButton.getChildren().add(btnAssign);
            //add label and combo box for selecting developer
            Label lblAssign = new Label("Assign to:");
            lblAssign.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
            ArrayList<Users> developers = new DataAccess().getUsers();
            //add all developers in the combobox
            for (int i = 0; i < developers.size(); i++) {
                if (developers.get(i).getType().equals("developer")) {
                    cbDevelopers.getItems().add(developers.get(i).getUsername());
                }
            }
            //add lable and combobox in gridpane
            gridPane.add(lblAssign, 0, 5);
            gridPane.add(cbDevelopers, 1, 5);
        }
        //condition if user type is manager and issue status is validated or rejected
        if (user.equals("manager") && (action.equalsIgnoreCase("validated") || action.equalsIgnoreCase("rejected"))) {
            //add close button to hbox
            hbButton.getChildren().add(btnClose);
        }
        //condition if user type is manager and issue status is resolved
        if (user.equals("manager") && action.equalsIgnoreCase("resolved")) {
            //add Validate button and Fail button to hbox
            hbButton.getChildren().addAll(btnValidate, btnFail);
        }
        //condition if user type is developer and issue status is assigned
        if (user.equals("developer") && action.equalsIgnoreCase("assigned")) {
            //add Open button and Reject button to hbox
            hbButton.getChildren().addAll(btnOpen, btnReject);
        }
        //condition if user type is developer and issue status is opened
        if (user.equals("developer") && action.equalsIgnoreCase("opened")) {
            //add Resolve button to hbox
            hbButton.getChildren().add(btnResolve);
        }

        //action event on cancel button
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //set scene to issuelist scene
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });

        //action event on submit button
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //check and show error if textfields are empty
                if (txtName.getText().trim().equals("") || txtName.getText().trim().equals(null)) {
                    lblError.setText("Please Enter Name!");
                } else if (txtEnv.getText().trim().equals("") || txtEnv.getText().trim().equals(null)) {
                    lblError.setText("Please Enter Environment!");
                } else if (txtType.getText().trim().equals("") || txtType.getText().trim().equals(null)) {
                    lblError.setText("Please Enter Type!");
                } else if (txtDetails.getText().trim().equals("") || txtDetails.getText().trim().equals(null)) {
                    lblError.setText("Please Enter Details!");
                } //if not empty
                else {
                    //declare variables and initilize them by getting data from textfields
                    String name = txtName.getText().trim();
                    String env = txtEnv.getText().trim();
                    String type = txtType.getText().trim();
                    String details = txtDetails.getText();
                    String status = "new";
                    // add data to file by passing data to Data Access class method
                    new DataAccess().addIssue(name, env, type, details, status, null);
                    //set scene to issue list scene
                    primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
                }
            }
        });

        //action evnet on assign button
        btnAssign.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //check if developer is not selected for assigning issue
                if (cbDevelopers.getSelectionModel().isEmpty()) {
                    //show error
                    lblError.setText("Select a developer!");
                    lblError.setVisible(true);
                } 
                else {
                    //create an arraylist and initialize by getting data from Data Access class
                    ArrayList<Issues> issues = new DataAccess().getIssues();
                    //set assignTo to developer name
                    issues.get(index).setAssignTo(cbDevelopers.getSelectionModel().getSelectedItem().toString());
                    //set status to assigned
                    issues.get(index).setStatus("Assigned");
                    //update the file
                    new DataAccess().updateIssues(issues);
                    //set scene to issue list scene
                    primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
                }

            }
        });
        
        //action on close button event
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to Closed
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Closed");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //action on validate button event
        btnValidate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to validated
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Validated");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //action on close button event
        btnFail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to failed
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Failed");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //action on fail button event
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to opened
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Opened");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //action on reject button event
        btnReject.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to rejected
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Rejected");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //action on resolve button event
        btnResolve.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //get all data and set the issue status to resolved
                ArrayList<Issues> issues = new DataAccess().getIssues();
                issues.get(index).setStatus("Resolved");
                //update data into file
                new DataAccess().updateIssues(issues);
                //set scene to issue list
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        
        //add vbox for creating a scene and set style
        VBox vbPane = new VBox();
        vbPane.setAlignment(Pos.CENTER);
        vbPane.setSpacing(30);
        vbPane.getChildren().addAll(lblTitle, gridPane, hbButton);
        vbPane.setStyle("-fx-background-color: BEIGE; -fx-alignment: center ;");
        
        //create scene and return
        Scene scene = new Scene(vbPane, 600, 700);
        return scene;
    }
    
    //add method for creating textfield with properties
    public TextField getTextField() {
        TextField textField = new TextField();
        textField.setFont(Font.font("Arial", 18));
        textField.setMaxWidth(200);
        textField.setMaxHeight(30);
        textField.setEditable(false);
        textField.setStyle("-fx-text-fill: light-blue; -fx-font: normal 15px 'serif'  ");
        return textField;
    }
    
    //add method for creating button with specific properties
    public Button getButton(String name) {
        Button button = new Button(name);
        button.setStyle("-fx-background-color: grey; -fx-font: normal bold 20px 'serif';");
        return button;
    }
}

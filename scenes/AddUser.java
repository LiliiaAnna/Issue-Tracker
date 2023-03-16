//Class which handle the functionality and view for Add User Scene
package scenes;
import issuetracker.DataAccess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddUser {

    public Scene getScene(Stage primaryStage, String user, String username) {
        //Adding lables and set there styles
        Label lblTitle = new Label("Issue Tracker Add User");
        lblTitle.setStyle("-fx-text-fill: black; -fx-font: normal bold 35px 'serif'  ");
        Label lblName = new Label("Name: ");
        lblName.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblEmail = new Label("Email: ");
        lblEmail.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblUsername = new Label("Username : ");
        lblUsername.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblPassword = new Label("Password: ");
        lblPassword.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblType = new Label("Type: ");
        lblType.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red; -fx-font: normal bold 14px 'serif'  ");
        lblError.setVisible(false);
        
        //adding textfields
        TextField txtName = getTextField();
        TextField txtEmail = getTextField();
        TextField txtUsername = getTextField();
        TextField txtPassword = getTextField();
        
        //adding toggle group and radio button for user type
        ToggleGroup tgTpye = new ToggleGroup();
        RadioButton r1 = new RadioButton("User");
        RadioButton r2 = new RadioButton("Developer");
        r1.setStyle("-fx-font: normal bold 15px 'serif';");
        r2.setStyle("-fx-font: normal bold 15px 'serif';");
        r1.setToggleGroup(tgTpye);
        r2.setToggleGroup(tgTpye);
        
        //adding buttons and set their style
        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif';");
        Button btnCancel = new Button("Cancel");
        btnCancel.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif'; ");
        
        //action event of button submit
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //conditions for showing error if the textfields are empty
                if (txtName.getText().trim().equals(null) || txtName.getText().trim().equals("")) {
                    lblError.setText("Please Enter Name!");
                    lblError.setVisible(true);
                }
                else if (txtEmail.getText().trim().equals(null) || txtEmail.getText().trim().equals("")) {
                    lblError.setText("Please Enter Email!");
                    lblError.setVisible(true);
                }
                else if (txtUsername.getText().trim().equals(null) || txtUsername.getText().trim().equals("")) {
                    lblError.setText("Please Enter Username!");
                    lblError.setVisible(true);
                }
                else if (txtPassword.getText().trim().equals(null) || txtPassword.getText().trim().equals("")) {
                    lblError.setText("Please Enter Password!");
                    lblError.setVisible(true);
                }
                else if(r1.isSelected()==false && r2.isSelected()==false){
                    lblError.setText("Please Select Type!");
                    lblError.setVisible(true);
                }
                //passing condition
                else{
                    //declare attributes and initialize by getting data from textfields
                    String name = txtName.getText().trim();
                    String email = txtEmail.getText().trim();
                    String username = txtUsername.getText().trim();
                    String password = txtPassword.getText().trim();
                    String type = null;
                    if(r1.isSelected()==true)
                        type = "user";
                    else
                        type = "developer";
                    //add new data into file by calling the method
                    new DataAccess().addUser(name, email, username, password, type);
                    //go back to issue list scene
                    primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
                }
            }
        });
        
        //action on cancel button
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //go back to issue list scene
                primaryStage.setScene(new IssueList().getScene(primaryStage, user, username));
            }
        });
        //add hbox and add radio buttons
        HBox hbType = getHBox();
        hbType.getChildren().addAll(r1, r2);
        
        //add gridpane and add lables and textfields and hbox
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblName, 0, 0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(lblEmail, 0, 1);
        gridPane.add(txtEmail, 1, 1);
        gridPane.add(lblUsername, 0, 2);
        gridPane.add(txtUsername, 1, 2);
        gridPane.add(lblPassword, 0, 3);
        gridPane.add(txtPassword, 1, 3);
        gridPane.add(lblType, 0, 4);
        gridPane.add(hbType, 1, 4);
        gridPane.add(lblError, 1, 5);
        
        //add hbox and add buttons
        HBox hbButton = getHBox();
        hbButton.getChildren().addAll(btnSubmit, btnCancel);
        hbButton.setAlignment(Pos.BOTTOM_CENTER);
        
        //add vbox and add lable title, gridpane and hbox of buttons 
        VBox vbPane = getVBox();
        vbPane.getChildren().addAll(lblTitle, gridPane, hbButton);
        vbPane.setStyle("-fx-background-color: BEIGE; -fx-alignment: center ;");
        
        //create scene and add vbox and return scene
        Scene scene = new Scene(vbPane, 500, 600);
        return scene;
    }
    
    //method for getting textfield
    public TextField getTextField() {
        TextField textField = new TextField();
        textField.setFont(Font.font("Arial", 18));
        textField.setMaxWidth(200);
        textField.setMaxHeight(30);
        return textField;
    }
    
    // method for getting hbox
    public HBox getHBox() {
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }
    
    //method for getting vbox
    public VBox getVBox() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        return vbox;
    }
}

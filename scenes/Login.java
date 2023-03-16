//Class which handle the functionality and view for Login Scene
package scenes;
import Model.Users;
import issuetracker.DataAccess;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login {

    public Scene getScene(Stage primaryStage) {
        //add labels and set styles
        Label lblTitle = new Label("Issue Tracker Login");
        lblTitle.setStyle("-fx-text-fill: blue; -fx-font: normal bold 40px 'serif'  ");
        Label lblUsername = new Label("Username: ");
        lblUsername.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblPassword = new Label("Password: ");
        lblPassword.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblType = new Label("Type: ");
        lblType.setStyle("-fx-text-fill: blue; -fx-font: normal bold 20px 'serif'  ");
        Label lblError = new Label("");
        lblError.setStyle("-fx-text-fill: red; -fx-font: normal bold 12px 'serif'  ");
        lblError.setVisible(false);

        //add textfields and set styles
        TextField txtUsername = new TextField();
        txtUsername.setFont(Font.font("Arial", 18));
        txtUsername.setMaxWidth(200);
        txtUsername.setMaxHeight(30);
        
        //add passwordfield
        PasswordField pfPassword = new PasswordField();
        TextField txPassword = new TextField();
        pfPassword.setMaxWidth(200);
        pfPassword.setMaxHeight(30);
        txPassword.setMaxHeight(30);
        txPassword.setMaxWidth(200);
        pfPassword.setMinWidth(200);
        pfPassword.setMinHeight(30);
        txPassword.setMinHeight(30);
        txPassword.setMinWidth(200);
        pfPassword.textProperty().bindBidirectional(txPassword.textProperty());
        //add stackpane and add passordfield and textfield of passord and set style
        StackPane txtPassword = new StackPane(txPassword, pfPassword);
        txtPassword.setMaxSize(200, 30);
        txtPassword.setStyle("-fx-font: normal 18px 'serif'  ");
        
        //add check box for showing password and set style
        CheckBox showPassword = new CheckBox("Show Password");
        showPassword.setStyle("-fx-text-fill: green; -fx-font: normal bold 20px 'serif'  ");
        //add action on checkbox and take textfielf of password on front or back accorfing to value
        showPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                txPassword.toFront();
            } else {
                pfPassword.toFront();
            }
        });
        
        //add toggle group and radio buttons for user types
        ToggleGroup tgTpye = new ToggleGroup();
        RadioButton r1 = new RadioButton("User");
        RadioButton r2 = new RadioButton("Manager");
        RadioButton r3 = new RadioButton("Developer");
        r1.setId("user");
        r2.setId("manager");
        r3.setId("developer");
        r1.setToggleGroup(tgTpye);
        r2.setToggleGroup(tgTpye);
        r3.setToggleGroup(tgTpye);
        
        //add hbox and add all radio buttons into hbox and set style
        HBox hbType = new HBox();
        hbType.setSpacing(10);
        hbType.getChildren().addAll(r1, r2, r3);
        hbType.setStyle("-fx-text-fill: orange; -fx-font: normal bold 13px 'serif'  ");
        
        //add gridpane and add lable and textfields and other
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(showPassword, 1, 2);
        gridPane.add(lblType, 0, 3);
        gridPane.add(hbType, 1, 3);
        gridPane.add(lblError, 1, 4);
        
        //add buttons and set styles
        Button btnLogin = new Button("Login");
        btnLogin.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif';");
        Button btnCancel = new Button("Cancel");
        btnCancel.setStyle("-fx-background-color: lightblue; -fx-font: normal bold 20px 'serif'; ");
        
        //add action event on login button
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //check if textfields are empty and show error
                if(txtUsername.getText().trim().equals("") || txtUsername.getText().trim().equals(null)){
                    lblError.setText("Please Enter Username!");
                    lblError.setVisible(true);
                }
                else if(txPassword.getText().trim().equals("") || txPassword.getText().trim().equals(null)){
                    lblError.setText("Please Enter Password!");
                    lblError.setVisible(true);
                }
                else if(r1.isSelected() == false && r2.isSelected() == false && r3.isSelected() == false){
                    lblError.setText("Please Select Type!");
                    lblError.setVisible(true);
                }
                //if conditions are true
                else{
                    //declare variables and initialize by getting value from textfields
                    String username = txtUsername.getText().trim();
                    String password = txPassword.getText().trim();
                    String type=null;
                    if(r1.isSelected()==true)
                        type = r1.getId();
                    else if(r2.isSelected()==true)
                        type = r2.getId();
                    else
                        type = r3.getId();
                    boolean userFound = false;
                    //create an arraylist of users and inialize by getting data from Data Access Class
                    ArrayList<Users> user = null;
                    try{
                        user = new DataAccess().getUsers();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    //loop for checking data
                    for(int i=0; i<user.size();i++){
                        //if user is authenetic
                        if(user.get(i).getUsername().equals(username) && user.get(i).getPassword().equals(password) && user.get(i).getType().equals(type)){
                            userFound = true;
                            //set scene to issueList scene
                            primaryStage.setScene(new IssueList().getScene(primaryStage, type, username));
                        }
                    }
                    //if user not found
                    if(userFound == false){
                        //show error
                        lblError.setText("Invalid!");
                        lblError.setVisible(true);
                    }
                }
            }
        });
        
        //add action on cancel event
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //exit the application
                System.exit(0);
            }
        });
        
        //hbox for adding buttons
        HBox hbButton = new HBox();
        hbButton.setSpacing(20);
        hbButton.getChildren().addAll(btnLogin, btnCancel);
        hbButton.setAlignment(Pos.BOTTOM_CENTER);
        
        //vbox for creating a scene and set style
        VBox vb1 = new VBox();
        vb1.setSpacing(40);
        vb1.getChildren().addAll(lblTitle, gridPane, hbButton);
        vb1.setAlignment(Pos.CENTER);
        vb1.setStyle("-fx-background-color: BEIGE; -fx-alignment: center ;");
        
        //create a scene
        Scene scene = new Scene(vb1, 500, 600);
        return scene;

    }
}

package com.pevir.StudentManagment.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pevir.StudentManagment.Dao.Database;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable{

	@FXML
	private Button btnClose;

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField pswUser;

	@FXML
	private TextField txtUserName;

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

//    Méthode appélé lors de la connexion
	public void loginAdmin() {
		String sql = "SELECT * FROM admin WHERE username = ? AND userpassword = ?";

		connect = Database.connectDb();

		try {
			Alert alert;

			prepare = connect.prepareStatement(sql);

			prepare.setString(1, txtUserName.getText());

			prepare.setString(2, pswUser.getText());

			result = prepare.executeQuery();

//    		Check if fields not empty
			if (txtUserName.getText().isEmpty() || pswUser.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);

				alert.setTitle("ERROR MESSAGE");

				alert.setHeaderText(null);

				alert.setContentText("Please fill all blank fields ");

				alert.showAndWait();

			} else {

//				Connexion réussit
				if (result.next()) {
					System.out.println("Succes login");
					
					alert = new Alert(AlertType.INFORMATION);

					alert.setTitle("INFORMATION MESSAGE");

					alert.setHeaderText(null);

					alert.setContentText("Successfull login!");

					alert.showAndWait();

//					Caché le formulaire de connexion
					btnLogin.getScene().getWindow().hide();

					Parent root = FXMLLoader.load(getClass().getResource("../VIews/DashBoard.fxml"));

					Stage stage = new Stage();

					Scene scene = new Scene(root);
					

					stage.setScene(scene);
					
					stage.initStyle(StageStyle.TRANSPARENT);

					stage.show();

				} else {
					alert = new Alert(AlertType.ERROR);

					alert.setTitle("ERROR MESSAGE");

					alert.setHeaderText(null);

					alert.setContentText("WRONG LOGIN OR PASSWORD");

					alert.showAndWait();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function to close window
	 */
	public void btnClose() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION MESSAGE");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to quit !");
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get().equals(ButtonType.OK)) {
			System.out.println("exit.");
			System.exit(0);
		}else return;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}

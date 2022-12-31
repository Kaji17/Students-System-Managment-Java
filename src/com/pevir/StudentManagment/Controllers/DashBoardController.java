package com.pevir.StudentManagment.Controllers;

import java.lang.StackWalker.Option;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pevir.StudentManagment.Dao.Database;
import com.pevir.StudentManagment.Model.Course;
import com.pevir.StudentManagment.Model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashBoardController implements Initializable, MainController {

	@FXML
	private AnchorPane addStudent_Form;

	@FXML
	private ImageView addStudent_ImageView;

	@FXML
	private Button addStudent_btn;

	@FXML
	private Button addStudent_btn_add;

	@FXML
	private Button addStudent_btn_clear;

	@FXML
	private Button addStudent_btn_delete;

	@FXML
	private Button addStudent_btn_insert;

	@FXML
	private Button addStudent_btn_update;

	@FXML
	private ComboBox<?> addStudent_choiceBox_course;

	@FXML
	private ComboBox<?> addStudent_choiceBox_gender;

	@FXML
	private ComboBox<?> addStudent_choiceBox_status;

	@FXML
	private ComboBox<?> addStudent_choiceBox_year;

	@FXML
	private TableColumn<Student, String> addStudent_col_birthDate;

	@FXML
	private TableColumn<Student, String> addStudent_col_course;

	@FXML
	private TableColumn<Student, String> addStudent_col_firstname;

	@FXML
	private TableColumn<Student, String> addStudent_col_gender;

	@FXML
	private TableColumn<Student, String> addStudent_col_lastname;

	@FXML
	private TableColumn<Student, String> addStudent_col_status;

	@FXML
	private TableColumn<Student, String> addStudent_col_student;

	@FXML
	private TableColumn<Student, String> addStudent_col_year;

	@FXML
	private DatePicker addStudent_datePicker_birthdate;

	@FXML
	private TableView<Student> addStudent_tableView;

	@FXML
	private TextField addStudent_txt_firstName;

	@FXML
	private TextField addStudent_txt_lastname;

	@FXML
	private TextField addStudent_txt_search;

	@FXML
	private TextField addStudent_txt_student;

	@FXML
	private AnchorPane availableCourse_Form;

	@FXML
	private Button availableCourse_btn;

	@FXML
	private Button availableCourse_btn_add;

	@FXML
	private Button availableCourse_btn_clear;

	@FXML
	private Button availableCourse_btn_delete;

	@FXML
	private Button availableCourse_btn_update;

	@FXML
	private TableColumn<Course, String> availableCourse_col_course;

	@FXML
	private TableColumn<Course, String> availableCourse_col_degree;

	@FXML
	private TableColumn<Course, String> availableCourse_col_description;

	@FXML
	private TableView<Course> availableCourse_tableView;

	@FXML
	private TextField availableCourse_txt_course;

	@FXML
	private TextField availableCourse_txt_degree;

	@FXML
	private TextField availableCourse_txt_description;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimize;

	@FXML
	private AnchorPane home_Form;

	@FXML
	private Button home_btn;

	@FXML
	private AreaChart<?, ?> home_femaleEnrolleChart;

	@FXML
	private Label home_femaleEnrolled;

	@FXML
	private Label home_malEnrolled;

	@FXML
	private LineChart<?, ?> home_malEnrolledChart;

	@FXML
	private BarChart<?, ?> home_totalEnrolleChart;

	@FXML
	private Label home_totalEnrolled;

	@FXML
	private Button logoutbtn;

	@FXML
	private BorderPane main_form;

	@FXML
	private AnchorPane studentGrade_Form;

	@FXML
	private Button studentGrade_btn;

	@FXML
	private Button studentGrade_btn_clear;

	@FXML
	private Button studentGrade_btn_update;

	@FXML
	private TableColumn<?, ?> studentGrade_col_Course;

	@FXML
	private TableColumn<?, ?> studentGrade_col_Final;

	@FXML
	private TableColumn<?, ?> studentGrade_col_FirstSem;

	@FXML
	private TableColumn<?, ?> studentGrade_col_SecondSem;

	@FXML
	private TableColumn<?, ?> studentGrade_col_Student;

	@FXML
	private TableColumn<?, ?> studentGrade_col_Year;

	@FXML
	private TextField studentGrade_search;

	@FXML
	private TableView<?> studentGrade_tableView;

	@FXML
	private TextField studentGrade_txt_course;

	@FXML
	private TextField studentGrade_txt_firstSem;

	@FXML
	private TextField studentGrade_txt_secondSem;

	@FXML
	private TextField studentGrade_txt_student;

	@FXML
	private TextField studentGrade_txt_year;

	private double x;

	private double y;

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	private ObservableList<Student> addStudentList;

	private Image image;

	private String[] yearList = { "fisrt year", "second year", "third year" };

	private String[] courseList = { "MIAGE", "SEA", "3EA", "ASSRI" };

	private String[] genderList = { "Male", "Female", "Other" };

	private String[] StatusList = { "Enrolled", "Unenrolled", "Inactive" };

	private ObservableList<Course> availableList;

	private Statement statement;

	/**
	 * methode pour fermer la fenetre
	 * 
	 * @return void
	 */
	public void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION MESSAGE");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to quit !");
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get().equals(ButtonType.OK)) {
			System.exit(0);
		} else
			return;

	}

	/**
	 * méthode pour réduire une fénetre
	 * 
	 * @return void
	 */
	public void minimize() {
		Stage stage = (Stage) main_form.getScene().getWindow();
		stage.setIconified(true);
	}

	/**
	 * méthode permettant d'ajouter une couleur au choix au background d'un boutton
	 * 
	 * @param btn   Button au quel on veut ajouter la couleur
	 * @param color String couleur choisit en exadecimale ex: #34a39c
	 */
	private void addStyle(Button btn, String color) {
		btn.setStyle("-fx-background-color:" + color);
	}

	/**
	 * méthode permettant de donner une coleur transparente au background des
	 * buttons passés en paramètre
	 * 
	 * @param btn1 Button
	 * @param btn2 Button
	 * @param btn3 button
	 * @return void
	 * @author pevir
	 */
	private void removeStyleBtn(Button btn1, Button btn2, Button btn3) {
		btn1.setStyle("-fx-background-color: transparant");
		btn2.setStyle("-fx-background-color: transparant");
		btn3.setStyle("-fx-background-color: transparant");
	}

	/**
	 * 
	 * Méthode permettant d'afficher les différent menu du Dashboard en cachant les
	 * autres non concerné
	 * 
	 * @param event ActionEvent
	 * @return void
	 * 
	 */
	public void switchForm(ActionEvent event) {
		if (event.getSource() == home_btn) {
			home_Form.setVisible(true);
			addStudent_Form.setVisible(false);
			availableCourse_Form.setVisible(false);
			studentGrade_Form.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(home_btn, "#34a39c");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(addStudent_btn, availableCourse_btn, studentGrade_btn);

		} else if (event.getSource() == addStudent_btn) {
			addStudent_Form.setVisible(true);
			home_Form.setVisible(false);
			availableCourse_Form.setVisible(false);
			studentGrade_Form.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(addStudent_btn, "#34a39c");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(home_btn, availableCourse_btn, studentGrade_btn);

//			Permettre d'actualiser la liste des étudiants de la base de donnée lors du click sur le button addStudent
			addStudentShowList();

			addStudentYearListChoicBox();
			addStudentCourseListChoicBox();
			addStudentStatusListChoicBox();
			addStudentGenderListChoicBox();

		} else if (event.getSource() == availableCourse_btn) {
			availableCourse_Form.setVisible(true);
			addStudent_Form.setVisible(false);
			home_Form.setVisible(false);
			studentGrade_Form.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(availableCourse_btn, "#34a39c");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(home_btn, addStudent_btn, studentGrade_btn);

			availableShowList();
		} else if (event.getSource() == studentGrade_btn) {
			studentGrade_Form.setVisible(true);
			availableCourse_Form.setVisible(false);
			addStudent_Form.setVisible(false);
			home_Form.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(studentGrade_btn, "#34a39c");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(home_btn, addStudent_btn, availableCourse_btn);
		}

	}

	/**
	 * méthode pour se déconnecter de l'application
	 */
	public void logout() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to logout !");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {

//				Permet de cacher la fenetre du dashboard
				logoutbtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("../VIews/Login.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.initStyle(StageStyle.TRANSPARENT);

				// Permet de faire bouger la fenetre et d'éviter de la redimensionner
				root.setOnMousePressed((MouseEvent event) -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});

				root.setOnMouseDragged((MouseEvent event) -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});

				stage.show();
			} else
				return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * méthode qui récupère tout les étudiant de la base de donné et l'ajoute à une
	 * liste d'observable
	 * 
	 * @return ObservableList<Student>
	 */
	public ObservableList<Student> addStudentList() {

		ObservableList<Student> listStudents = FXCollections.observableArrayList();

		String sql = "SELECT * FROM Student";

		connect = Database.connectDb();

		try {

			Student student;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

			while (result.next()) {
				student = new Student(result.getInt("studentNum"), result.getString("years"),
						result.getString("course"), result.getString("firstName"), result.getString("lastName"),
						result.getString("gender"), result.getDate("birthDate"), result.getString("statue"),
						result.getString("image"));
				listStudents.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStudents;
	}

	/**
	 * méthode permettant d'afficher les champs sur l'objet student elle ajoute au
	 * diférentes colone les attirubuts correspondant
	 */
	public void addStudentShowList() {
//		On récupère la liste des Students de la base de donnée  
		addStudentList = addStudentList();

		addStudent_col_student.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
		addStudent_col_year.setCellValueFactory(new PropertyValueFactory<>("years"));
		addStudent_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		addStudent_col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		addStudent_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		addStudent_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		addStudent_col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		addStudent_col_status.setCellValueFactory(new PropertyValueFactory<>("statue"));

		addStudent_tableView.setItems(addStudentList);
	}

	/**
	 * méthode permetant de remplir les différent champs au niveau du menu ajout
	 * lorsque un champs du table views et selectionner
	 * 
	 * @return void
	 */
	public void addStudentSelected() {
		Student student = addStudent_tableView.getSelectionModel().getSelectedItem();

		int num = addStudent_tableView.getSelectionModel().getSelectedIndex();

//		Dans la mesure ou il n'existe aucun étudiant on fait rien
		if (num - 1 < -1) {
			return;
		}

		addStudent_txt_student.setText(String.valueOf(student.getStudentNum()));
		addStudent_txt_firstName.setText(student.getFirstName());
		addStudent_txt_lastname.setText(student.getLastName());

		String url = student.getImage();

		Image image = new Image(url, 200, 176, false, true);
		addStudent_ImageView.setImage(image);

	}

	/**
	 * méthode qui récupère tous les courses de la base de donné et l'ajoute à une
	 * liste d'observable
	 * 
	 * @return ObservableList<Course>
	 */
	public ObservableList<Course> availableCourseList() {
		ObservableList<Course> listcourse = FXCollections.observableArrayList();

		String sql = "SELECT * FROM Course";

		connect = Database.connectDb();

		try {

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

			while (result.next()) {
				Course course;

				course = new Course(result.getString("course"), result.getString("description"),
						result.getString("degree"));

				listcourse.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listcourse;
	}

	/**
	 * méthode permettant d'afficher les champs sur l'objet course elle ajoute au
	 * diférentes colone les attirubuts correspondant
	 */
	public void availableShowList() {
		availableList = availableCourseList();

		availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

		availableCourse_tableView.setItems(availableList);
	}

	public void availableSelected() {
		Course course = availableCourse_tableView.getSelectionModel().getSelectedItem();

		Integer num = availableCourse_tableView.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		availableCourse_txt_course.setText(course.getCourse());
		availableCourse_txt_description.setText(course.getDescription());
		availableCourse_txt_degree.setText(course.getDegree());

	}

	/**
	 * méthode permetant d'ajouter des élément a notre Choice box year
	 */
	public void addStudentYearListChoicBox() {
		List<String> yearlist = new ArrayList<>();

		for (String data : yearList) {
			yearlist.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(yearlist);

		addStudent_choiceBox_year.setItems(oblist);
	}

	/**
	 * méthode permetant d'ajouter des élément a notre Choicebox course
	 */
	public void addStudentCourseListChoicBox() {
		List<String> courseList = new ArrayList<>();

		for (String data : this.courseList) {
			courseList.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(courseList);

		addStudent_choiceBox_course.setItems(oblist);
	}

	/**
	 * méthode permetant d'ajouter des élément a notre Choicebox gender
	 */
	public void addStudentGenderListChoicBox() {
		List<String> genderlist = new ArrayList<>();

		for (String data : genderList) {
			genderlist.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(genderlist);

		addStudent_choiceBox_gender.setItems(oblist);
	}

	/**
	 * méthode permetant d'ajouter des élément a notre Choicebox status
	 */
	public void addStudentStatusListChoicBox() {
		List<String> statuslist = new ArrayList<>();

		for (String data : StatusList) {
			statuslist.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(statuslist);

		addStudent_choiceBox_status.setItems(oblist);
	}

	/**
	 * méthode créer pour pouvoir ajouter de nouveau cour à la base de donnée
	 * 
	 */
	public void addAvailableCourse() {
		Alert alert;

		String sqlInsert = "INSERT INTO Course (course, description, degree) VALUES (?, ?, ?)";

		connect = Database.connectDb();

		try {
			prepare = connect.prepareStatement(sqlInsert);

//			Vérifier si les champs ne sont pas vides
			if (availableCourse_txt_course.getText().isEmpty() || availableCourse_txt_description.getText().isEmpty()
					|| availableCourse_txt_degree.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields ");
				alert.showAndWait();
			} else {
//				Vérifier si l'élement qu'on veut insérer n'existe pas

				String sqplCheck = "SELECT course FROM course WHERE course = '" + availableCourse_txt_course.getText()
						+ "'";

				statement = connect.createStatement();

				result = statement.executeQuery(sqplCheck);

				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Course: " + availableCourse_txt_course.getText() + " was already exist");
					alert.showAndWait();
				} else {

					prepare.setString(1, availableCourse_txt_course.getText());

					prepare.setString(2, availableCourse_txt_description.getText());

					prepare.setString(3, availableCourse_txt_degree.getText());

					result = prepare.executeQuery();

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION Message");
					alert.setHeaderText(null);
					alert.setContentText("Succesfully added!");
					alert.showAndWait();

					availableShowList();
					availableCourseClear();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * méthode pour rénitialiser tout les champs pour les available course
	 */
	public void availableCourseClear() {
		availableCourse_txt_course.setText("");
		availableCourse_txt_description.setText("");
		availableCourse_txt_degree.setText("");
	}

	/**
	 * méthode créer pour pouvoir supprimer des cours de la base de donnée
	 * 
	 */
	public void availableCourseDelete() {
		Alert alert;

		String sqlDelete = "DELETE FROM course WHERE course =?";

		connect = Database.connectDb();

		try {

			if (availableCourse_txt_course.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields ");
				alert.showAndWait();
			} else {

				String sqlCheck = "SELECT course FROM course WHERE course = '" + availableCourse_txt_course.getText()
						+ "'";
				statement = connect.createStatement();

				result = statement.executeQuery(sqlCheck);

				if (result.next()) {

					alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("CONFIRMATION Message");
					alert.setHeaderText(null);
					alert.setContentText(
							"Are you sure you want deleted: '" + availableCourse_txt_course.getText() + "'!");
					Optional<ButtonType> option = alert.showAndWait();

					if (option.get().equals(ButtonType.OK)) {

						prepare = connect.prepareStatement(sqlDelete);

						prepare.setString(1, availableCourse_txt_course.getText());

						result = prepare.executeQuery();

						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("INFORMATION Message");
						alert.setHeaderText(null);
						alert.setContentText("Deleted!");
						availableCourseClear();
						availableShowList();
						alert.showAndWait();

					}
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Course: " + availableCourse_txt_course.getText() + " not found ");
					alert.showAndWait();
					return;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void availableCourseUpdate() {
		Alert alert;

		String sqlUpdate = "UPDATE course SET description=?, degree=? WHERE course=?";

		connect = Database.connectDb();

		try {

			if (availableCourse_txt_course.getText().isEmpty() || availableCourse_txt_description.getText().isEmpty()
					|| availableCourse_txt_degree.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields ");
				alert.showAndWait();
			} else {

				String sqlCheck = "SELECT course FROM course WHERE course = '" + availableCourse_txt_course.getText()
						+ "'";
				statement = connect.createStatement();

				result = statement.executeQuery(sqlCheck);

				if (result.next()) {

					alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("CONFIRMATION Message");
					alert.setHeaderText(null);
					alert.setContentText(
							"Are you sure you want update information of : '" + availableCourse_txt_course.getText() + "'!");
					Optional<ButtonType> option = alert.showAndWait();

					if (option.get().equals(ButtonType.OK)) {

						prepare = connect.prepareStatement(sqlUpdate);

						prepare.setString(1, availableCourse_txt_description.getText());

						prepare.setString(2, availableCourse_txt_degree.getText());
						
						prepare.setString(3, availableCourse_txt_course.getText());


						result = prepare.executeQuery();

						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("INFORMATION Message");
						alert.setHeaderText(null);
						alert.setContentText("Update!");
						availableCourseClear();
						availableShowList();
						alert.showAndWait();

					}
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Course: " + availableCourse_txt_course.getText() + " not found ");
					alert.showAndWait();
					return;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addStudentShowList();
		availableShowList();
		addStudentYearListChoicBox();
		addStudentCourseListChoicBox();
		addStudentStatusListChoicBox();
		addStudentGenderListChoicBox();
	}

}

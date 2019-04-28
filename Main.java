package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static List<Assignment> assignments = new ArrayList<Assignment>();

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			Scene scene = new Scene(grid, 700, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Assignment Tracker");
			grid.setHgap(10);
			grid.setVgap(10);
			primaryStage.setScene(scene);
			primaryStage.show();

			Label a = new Label("Assignment");
			grid.add(a, 1, 0);

			TextField name = new TextField();
			name.setPromptText("Enter assignment name");
			GridPane.setConstraints(name, 2, 1);
			grid.getChildren().add(name);

			TextField course = new TextField();
			course.setPromptText("Enter course name");
			GridPane.setConstraints(course, 2, 2);
			grid.getChildren().add(course);

			DatePicker assignDate = new DatePicker();
			assignDate.setValue(LocalDate.now());
			GridPane.setConstraints(assignDate, 2, 4);
			grid.getChildren().add(assignDate);
			Label d = new Label("Assigned date:");
			grid.add(d, 2, 3);

			DatePicker dueDate = new DatePicker();
			dueDate.setValue(LocalDate.now());
			GridPane.setConstraints(dueDate, 2, 6);
			grid.getChildren().add(dueDate);
			Label p = new Label("Due date:");
			grid.add(p, 2, 5);

			// Label b = new Label();
			// grid.add(b, 2, 6);

			Button save = new Button("Save");
			save.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					saveToFile(name.getText(),course.getText(),assignDate.getValue(),dueDate.getValue());
				
				}
			});
			grid.add(save, 2, 7);

			readFileIntoArray();
			
			TableView<Assignment> tableView;
			tableView= new TableView<>();
			
			
			tableView.setItems( GetObsList());
			tableView=CreateTableView(tableView);
			 
			
			

			grid.add(tableView, 2, 8);

			/*
			 * TableView tableView = new TableView();
			 * 
			 * TableColumn<String, Main> nameColumn = new TableColumn<>("Assignment Name");
			 * nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			 * 
			 * 
			 * TableColumn<String, Main> courseColumn = new TableColumn<>("Course Name");
			 * courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
			 * 
			 * 
			 * tableView.getColumns().add(nameColumn);
			 * tableView.getColumns().add(courseColumn);
			 * 
			 * tableView.getItems().add(name); tableView.getItems().add(course);
			 * 
			 * 
			 * Tableview tableview= CreateTableView();
			 * 
			 * VBox vbox = new VBox(tableView);
			 * 
			 * Scene scene2 = new Scene(vbox);
			 * 
			 * primaryStage.setScene(scene2);
			 * 
			 * primaryStage.show();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static TableView CreateTableView(TableView inTableView) {

		//TableView tableView = new TableView();

		// **** Assignment Name Column
		TableColumn<Assignment, String> nameColumn = new TableColumn<>("Assignment Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		// **** Assignment Name Column
		TableColumn<Assignment, String> classNameColumn = new TableColumn<>("Class Name");
		classNameColumn.setMinWidth(150);
		classNameColumn.setCellValueFactory(new PropertyValueFactory<>("ClassName"));

		// **** Assignment Name Column
		TableColumn<Assignment, String> assignDateColumn = new TableColumn<>("Assigned");
		assignDateColumn.setMinWidth(25);
		assignDateColumn.setCellValueFactory(new PropertyValueFactory<>("AssignedDate"));
		
		// **** Assignment Name Column
		TableColumn<Assignment, String> dueDateColumn = new TableColumn<>("Due");
		dueDateColumn.setMinWidth(25);
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

		inTableView.getColumns().addAll(nameColumn, classNameColumn, assignDateColumn, dueDateColumn);

		return inTableView;
	}

	private static ObservableList<Assignment> GetObsList(){
		ObservableList<Assignment> oAssignments= FXCollections.observableArrayList();
		
		for(Assignment assignment: assignments) {
			oAssignments.add(assignment);
		}
		return oAssignments;
	}
	
	public static void saveToFile(String name, String course, String assignDate, String dueDate) {
		Assignment assignment= new Assignment();
		
		assignment.Name= name;
		assignment.ClassName= course;
		assignment.AssignedDate= assignDate;
		assignment.DueDate= dueDate;
		
		assignments.add(assignment);
		
		String text= name+","+course+","+assignDate+","+dueDate;
		
		File file= new File("C:\\Users\\alper\\eclipse-workspace\\Interface\\src\\application\\class.csv");
		String FilePath= file.getPath();
		FileReader fr= new FileReader(file);
		BufferedWriter writer = new BufferedWriter(fr);
		writer.newLine();
		writer.write(text);
		writer.close();
	}
	
	
	
	public static void readFileIntoArray() throws IOException {
		
		File file= new File("C:\\Users\\alper\\eclipse-workspace\\Interface\\src\\application\\class.csv");
		String FilePath= file.getPath();
		FileReader fr= new FileReader(file);
		BufferedReader reader = new BufferedReader(fr); 
		
			
			String input;
			while ((input = reader.readLine()) != null) {
				String[] values = input.split(",");
				Assignment assignment= new Assignment();
				assignment.Name= values[0];
				assignment.ClassName= values[1];
				assignment.AssignedDate= values[2];
				assignment.DueDate= values[3];
				
				assignments.add(assignment);
				
							}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

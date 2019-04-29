package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static List<Assignment> assignments = new ArrayList<Assignment>();
	private static TableView tableView= new TableView<>();

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
					
					String assignedDateStr = assignDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					String dueDateStr = dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					try {
						saveToFile(name.getText(),course.getText(),assignedDateStr,dueDateStr);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ReloadTableview();
				
				}
			});
			grid.add(save, 2, 7);

			readFileIntoArray();
			
			
			
		
			//TableView tableView= new TableView<>();
			
			tableView.setItems( GetObsList());
			tableView=CreateTableView(tableView);
			
			 			

			grid.add(tableView, 2, 8);
		
			
			  primaryStage.show();
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ReloadTableview()
	{		
		tableView.setItems( GetObsList());
		
	}
	private static TableView CreateTableView(TableView inTableView) {

		
		// **** Assignment Name Column
		TableColumn<Assignment, String> nameColumn = new TableColumn("Assignment Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory("Name"));

		// **** Assignment Name Column
		TableColumn<Assignment, String> classNameColumn = new TableColumn("Class Name");
		classNameColumn.setMinWidth(150);
		classNameColumn.setCellValueFactory(new PropertyValueFactory("ClassName"));

		// **** Assignment Name Column
		TableColumn<Assignment, String> assignDateColumn = new TableColumn("Assigned");
		assignDateColumn.setMinWidth(25);
		assignDateColumn.setCellValueFactory(new PropertyValueFactory("AssignedDate"));
		
		// **** Assignment Name Column
		TableColumn<Assignment, String> dueDateColumn = new TableColumn("Due");
		dueDateColumn.setMinWidth(25);
		dueDateColumn.setCellValueFactory(new PropertyValueFactory("DueDate"));

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
	
	public static void saveToFile(String name, String course, String assignDate, String dueDate) throws IOException {
		Assignment assignment= new Assignment();
		
		assignment.setName(name);
		assignment.setClassName(course);
		assignment.setAssignedDate(assignDate);
		assignment.setDueDate(dueDate);
		
		assignments.add(assignment);
		
		String text= name+","+course+","+assignDate+","+dueDate;
		
		File file= new File("C:\\Users\\tperkins\\eclipse-workspace\\Interface\\src\\application\\class.csv");
		
		FileWriter fw= new FileWriter(file, true);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.newLine();
		writer.write(text);
		writer.flush();
	}
	
	
	
	public static void readFileIntoArray() throws IOException {
		
		File file= new File("C:\\Users\\tperkins\\eclipse-workspace\\Interface\\src\\application\\class.csv");
		String FilePath= file.getPath();
		FileReader fr= new FileReader(file);
		BufferedReader reader = new BufferedReader(fr); 
		
			
			String input;
			while ((input = reader.readLine()) != null) {
				String[] values = input.split(",");
				Assignment assignment= new Assignment();
				assignment.setName(values[0]);
				assignment.setClassName(values[1]);
				assignment.setAssignedDate(values[2]);
				assignment.setDueDate(values[3]);
				
				assignments.add(assignment);
				
							}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
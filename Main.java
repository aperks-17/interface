package application;
	
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid= new GridPane();
			Scene scene = new Scene(grid,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Assignment Tracker");
			grid.setHgap(10);
			grid.setVgap(10);
			primaryStage.setScene(scene);
			primaryStage.show();
	
			Label a= new Label("Assignment");
			grid.add(a, 1, 0);
			
			TextField name=new TextField();
			name.setPromptText("Enter assignment name");
			GridPane.setConstraints(name,2,1);
			grid.getChildren().add(name);
		
			
			TextField course=new TextField();
			course.setPromptText("Enter course name");
			GridPane.setConstraints(course,2,2);
			grid.getChildren().add(course);
			

			DatePicker assignDate =new DatePicker();
			assignDate.setValue(LocalDate.now());
			GridPane.setConstraints(assignDate,2,4);
			grid.getChildren().add(assignDate);
			Label d= new Label("Assigned date:");
			grid.add(d, 2, 3);
			
			DatePicker dueDate =new DatePicker();
			dueDate.setValue(LocalDate.now());
			GridPane.setConstraints(dueDate,3,4);
			grid.getChildren().add(dueDate);
			Label p= new Label("Due date:");
			grid.add(p, 3, 3);
			
			Button save=new Button("Save");
			grid.add(save, 4, 4);
			save.setDisable(false);
			save.setOnAction(e->{
				String name1= String.valueOf(name.getText());
				String course1=String.valueOf(course.getText());
				String main= System.out.println("Assignment"+ name1);
				save.setText(main.toString());
			});
			
		

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;
/*
 * Main.java By Steven Kalis
 * -----------------------
 * This runs the application.
 * 
 * The Media Player Controller is in charge of the main functionality
 * 
 * The MyFile is in charge of determining file type attributes
 * 
 * The PlayedMedia is a class that stores a file's information to maybe display in a .txt
 */
import java.io.File;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;  

public class Main extends Application  
{  

	File file;
	@FXML Button btnOpen, btnFact;
	
	
    @Override  
    public void start(Stage primaryStage) throws Exception {  
    	
    	 try {
    		 	//grabs the FXML file to determine how the GUI will look
    		 	Parent root = FXMLLoader.load(Main.class.getResource("/application/myMediaPlayer.fxml"));
    		 	//sets a scene from the file
    		 	Scene scene = new Scene(root);
    		 	//stylesheet addition (didn't end up using it but it was recommended to add it on forms just in case)
    		 	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		 	
    		 	//set title and show the window
    		 	primaryStage.setTitle("SK Productions - Media Player");
    		 	primaryStage.setScene(scene);
    		 	primaryStage.show();
    	 
    	 
    	 	}	
    	 catch(Exception e) {
    		 	e.printStackTrace();
    	 }

    }  
    public static void main(String[] args) {  
        launch(args);  
    }  
    
   
  
      
	
	
	
      
}  
package application;
/*
 * myMediaPlayerController.java by Steven Kalis
 * -----------------------------------------------
 * @FXML means that it was created from the FXML file and has code in this file
 * 
 * This will allow users to play music, add it to a playlist and print out total amounted time!
 * 
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class myMediaPlayerController  {
	ArrayList<File> file;
	ArrayList<PlayedMedia> fileList;
	
	static File currentFile;
	//Media view created from FXML file
	@FXML
	private MediaView view;
	//Media player and media 
	private MediaPlayer player;
	private Media media;
	//Buttons created from FXML
	@FXML private Button btnOpen;
	@FXML private Button btnFact;
	@FXML private Button btnMediaToFile;
	@FXML private Button btnPlay;
	@FXML private Button btnStop;
	@FXML private Button btnPause;
	@FXML private Button btnRewind;
	@FXML private Button btnFastForward;
	
	@FXML HBox hbxTop;
	@FXML HBox hbxBottom;
	@FXML BorderPane bdrWindow;
	
	MyFile fileType;
	FileChooser fc;
	FileChooser.ExtensionFilter fileExtensions;
	private boolean playing;
	private int addToPlaylist;
	//create variable to store total time spent
	private double totalPlayTime;
	
	//default constructor
	public myMediaPlayerController() {
		file = new ArrayList<>();
		fileList = new ArrayList<>();
		playing = false;
		totalPlayTime = 0;
	}
	
	
	// Button action events
	public void videoSettings(ActionEvent e) {
		Object source = e.getSource();
		
		//Button method for Open File
		if(source.equals(btnOpen))
		{
			// create a file chooser to grab a file
			 fc = new FileChooser(); 
			// Set extension filter so we only see certain files
			 fileExtensions = new FileChooser.ExtensionFilter(
					    "Video / Audio Files", "*.mp3", "*.mp4", "*.flv","*.m4a","*.m4v", "*.wav");

					fc.getExtensionFilters().add(fileExtensions);
			File aFile = fc.showOpenDialog(null);
			file.add(aFile);
			
			//if the filechooser doesn't work
	        if( file.get(0) !=null ){
	        	
	        	
	        	
	        	//if there is nothing playing
	        	if(!playing)
	        	{	
	        		//setup the player
	        		media = new Media(file.get(0).toURI().toString());
	        		player = new MediaPlayer(media);
	        		view.setMediaPlayer(player);
	    		
	    		
	        		
	        	}
	        	//if there is something playing
	        	else
	        	{
	        		//alert the user that something is playing and what they want to do
	        		createAlert(source);
	        		
	        		//if we want to ignore what's playing and play something new
	        		if(addToPlaylist == 0)
	        		{
	        			playing = true;
	        			player.stop();
	        			media = new Media(aFile.toURI().toString());
	        			player = new MediaPlayer(media);
	        			view.setMediaPlayer(player);
	        			player.play();
	        		}
	        		
	        		
	        	}
	        	
	        	

	    		
	    		
	    			//when the media ends, we will go to the next one in the playlist
	        	
	    			player.setOnEndOfMedia(new Runnable() {
		    			@Override
		    			public void run() {
		    				
		    				//play the next video by removing the current one and playing the next one.
		    				playing = true;
		    				file.remove(0);
		    				if(file.size() > 0)
		    				{
		    					//get information on the next file!
		    					if(file.get(0).toString().contains(".mp3"))
	    	    				{
	    	    					fileType = new MP3();
	    	    				}
	    	    				else if(file.get(0).toString().contains(".mp4"))
	    	    				{
	    	    					fileType = new MP4();
	    	    				}
		    					//play and show file
		    					media = new Media(file.get(0).toURI().toString());
		    					player = new MediaPlayer(media);
		    					view.setMediaPlayer(player);
		    					player.setAutoPlay(true);
		    				}
		    			}
		    		});
	    		
	    		//what the player should do when it's ready to play
	    		player.setOnReady(new Runnable() {
	    			@Override
	    			public void run() {
	    				
	    				//depending on if we stop the media halfway through for a new one.
	    				if(addToPlaylist == 0)
    					{
	    					// if the file we want to play is a certain file
	    					if(aFile.toString().contains(".mp3"))
		    				{
	    						//if the media is audio, set it to the audio class
		    					fileType = new MP3();
		    				}
		    				else if(aFile.toString().contains(".mp4"))
		    				{
		    					//if the media is video, set it to the video class
		    					fileType = new MP4();
		    				}
	    					//add the known information about the file to the PlayedMedia arraylist
	    					fileList.add(new PlayedMedia(aFile.getName(),fileType,media.getDuration()));
	    					//play the new media
	    					player.play();
	    					playing = true;
    					}
	    				//if the media is playing after one is fully done
    					else
    					{
    						//same if statement as above but with the local playlist instead of the file
    						if(file.get(0).toString().contains(".mp3"))
    	    				{
    	    					fileType = new MP3();
    	    				}
    	    				else if(file.get(0).toString().contains(".mp4"))
    	    				{
    	    					fileType = new MP4();
    	    				}
    						//add known information and play
    						fileList.add(new PlayedMedia(file.get(0).getName(),fileType,media.getDuration()));
	    					player.play();
	    					playing = true;
    					}
	    				
	    			}
	    		});
	    		
	    		//dynamically change the size of the media view to fit the window
	    		DoubleProperty width = view.fitWidthProperty();
	    		DoubleProperty height = view.fitHeightProperty();
	    		width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
	    		height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
	        }
			
			
		}
		
		//button that will print the playlist info to a file
		if(source.equals(btnMediaToFile))
		{
			if(fileList.size() > 0)
			{	
				//create a new file chooser that will be used to create a file
				fc = new FileChooser();
				//Set extension filter to text files
				fileExtensions = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fc.getExtensionFilters().add(fileExtensions);
				
				
				//Show save file dialog
				File file = fc.showSaveDialog(null);
				FileWriter writer;
				try {
					//object writing block
					writer= new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(writer);
					PrintWriter pw = new PrintWriter(bw);
					
					//create a counting variable to count the number of media played
					int x = 1;
					//Using the PlayedMedia arraylist, display information about each media
					for(PlayedMedia list: fileList)
					{
						totalPlayTime = list.getDuration().toMinutes();
						pw.write("Media No." + x + " - " + list.myString() + "\n");
						x++;
					}
					//format total time played to a recognizable time as well as making it a string
					String time = String.format("%2.2f", totalPlayTime);
					pw.write("--------------------\n");
					pw.write("Total Play Time: " + time + " Minutes");

					//close the file
					pw.close();
					
					} 
				//if something happened with the writing of file
				catch (IOException eIO) {
		        eIO.printStackTrace();
				}

			}
			else {
				//if there has been no media played yet, display an error alert
				createAlert(source);
			}
		}
		//Button method for File Fact
		if(source.equals(btnFact))
		{
			//show a message depending on some information
			createAlert(source);			
		}
		
		//Button method for Play Media
		if(source.equals(btnPlay))
		{
			if(playing)
			{
				//set normal play rate and play
				player.setRate(1);
				player.play();
			}
			else
			{
				createAlert(source);			
			}
		}
		//Button method for Stop Media
		if(source.equals(btnStop))
		{
			if(playing)
			{
				//stop media and remove it from the local playlist
				player.stop();
				file.remove(0);
			}
			else
			{
				createAlert(source);			
			}
		}
		
		//Button method for Pause Media
		if(source.equals(btnPause))
		{
			if(playing)
			{
				//pause current media
				player.pause();
			}
			else
			{
				createAlert(source);			
			}
		}
		//Button method for Rewind Media
		if(source.equals(btnRewind))
		{
			if(playing)
			{
				//go back to begining of video
				player.seek(player.getStartTime());
			}
			else
			{
				createAlert(source);			
			}
		}
		//Button method for Fast-Forward Media
		if(source.equals(btnFastForward))
		{
			if(playing)
			{
				//make video appear to go faster
				player.setRate(2);
			}
			else
			{
				createAlert(source);			
			}
		}
		
	}
	//Alert method for all alerts
	private void createAlert(Object source)
	{
		Alert alert;
		
		//Button method for File Fact
		if(source.equals(btnFact) && playing)
		{
			
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("File Fact about " + fileType.toString() +"'s");
			alert.setContentText(fileType.fileTypeInfo());
			
			alert.showAndWait();
		}
		else if(source.equals(btnOpen) && playing)
		{
			//create three buttons on the alert for what the user can do
			ButtonType playNow = new ButtonType("Play\nMedia Now", ButtonData.NO);
			ButtonType addToPlaylist = new ButtonType("Add To\nPlaylist", ButtonData.YES);
			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert = new Alert(AlertType.WARNING,
			        "Media is already being played,\nWhat would you like to do?.",
			        playNow,
			        addToPlaylist,
			        cancel);
			alert.setTitle("Media Issue");
			//grab the button the user picked
			Optional<ButtonType> result = alert.showAndWait();
			//save information to field variable for later use
			if (result.orElse(playNow) == playNow) {
			    this.addToPlaylist = 0;
			}
			else if(result.orElse(addToPlaylist) == addToPlaylist)
			{
				this.addToPlaylist = 1;
			}
			else
			{
				this.addToPlaylist = 2;
			}
			
		}
		else
		{
			
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setContentText("NO MEDIA HAS BEEN LOADED YET");
			
			alert.showAndWait();
		}
		
		
	}
	
	//send the currentFile to a class that needs it
	public static String getFile() {
		return currentFile.toString();
	}
		
	
}

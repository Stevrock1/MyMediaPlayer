package application;
/*
 * PlayedMedia.java by Steven Kalis
 * --------------------------------
 * Store information about each media played and display it when user would like to see it.
 */
import javafx.util.Duration;
interface Message{
	public String myString();
}
public class PlayedMedia implements Message {

	private String fileName;
	private MyFile fileType;
	private Duration duration;
	public PlayedMedia(String fileName, MyFile fileType, Duration duration) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.duration = duration;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileType()
	{
		return fileType.toString();
	}
	public Duration getDuration()
	{
		return duration;
	}
	
	public String myString()
	{
		// create message that will show for each line of the filename, filetype and duration
		String message = "Media Name: " + getFileName().substring(0,getFileName().indexOf('.')) + ", Media Type: " + getFileType();
		return message;
		
	}
}

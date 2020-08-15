MyMediaPlayer
By Steven Kalis
12/12/2019
--------------------------
This program can read in media files and play them to the user. If they wish to play more media they can
add it to a playlist. IF you're impatient you can have a media play instantly. By pressing a button, you can
display a fact about the current file type. The user can also display information about the media that has been viewed so far
and display it in a file.

Required Elements
----------
GUI Interface
	- I have a GUI that allows you to select your media and play it
Classes
	- I have a class that stores Media types as well as my media information
Arrays/Lists
	- I have an ArrayList of my played videos and a local ArrayList of my current play queue
Inheritance
	- For the MyFile class, I inherit the fileType field so i can change it depending on the type of file it is.
Polymorphism
	- In the same MyFile class, I change a method that sends information to be something else depending on the file type.
Interface
	- It was hard finding a way to include interfaces, they're a cool concept but I didn't really have a use for them
		so instead of using toString I have myString from my interface Message in the PlayedMedia.java.


Learning Points
--------------------
I learned a lot while making this project. I only just got the dang thing working on 12/11/2019 (one day ago while typing this). The first problem I
encountered is getting JavaFX. Luckfully this was an easy part and I was able to download the javafx directory to my local computer. This was about
two weeks ago, and more problems ensued. I learned how to import a directory to Eclipse IDE. I also worked on this in Eclipse IDE, mainly because
I've become comfortable working with it from the Software Engineering class. Even when I imported the directory, some compile errors were occuring.
None of the compile errors were my fault but after a week and a half of online searching and learning how to read long error messages, I learned that
apparently I was required to add java run arguments due to missing information. For future use of this project, just in case it doesn't work on 
another computer, the agruments I typed out are like so:
-p C:\directory\to\javafx-sdk-11.0.2\lib 
-Xmx2048m
--add-modules javafx.controls,javafx.base,javafx.graphics,javafx.fxml
--add-exports javafx.graphics/com.sun.glass.utils=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.util=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.tk=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.geom=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.javafx.geom.transform=ALL-UNNAMED
--add-exports javafx.graphics/com.sun.prism=ALL-UNNAMED
After typing all of those, my media player started working. Now I could actually start coding!
The tricky part was letting the user select a file to use, as most of the resources online had people put files into the package of Eclipse.
I found the JavaFX alternative called FileChooser which allows me to choose files as well as save files!
Then I learned about Alerts and how they are the JavaFX alternative to JOptionPanes, but with a twist.

Final Thoughts
--------------------
JavaFX was really hard to understand at first, and by looking at online resources it seemed like there was only a few ways to go around it or you'll
run into an error. That is why I dont have much functionality in the Main.java, and all of the functionality is in myMediaPlayerControler.java

This was a huge step for me as attempting a project like this is what made me start becoming a programmer. I will always have the heart and passion
of my insperation to become a Broadcaster and that's what pushed me to get this project done as soon as possible!

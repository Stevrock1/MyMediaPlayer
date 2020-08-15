package application;
/*
 * MyFile.java by Steven Kalis
 * -------------------------------------
 * When we determine what type of file type a file is,
 * This class will give that file specific characteristics!
 */
class MyFile
{
   protected String type = "No File";
   
   public String fileTypeInfo()
	{
		return "Test";
		
	}
   
   public String toString() {
	   return type;
   }
}

class MP3 extends MyFile
{
	//change the value of the type variable
	public MP3()
	{
		super();
		this.type = "MP3";
	}
   
   
   @Override
   public String fileTypeInfo()
	{
		return "With the rapid growth of the internet back in the early 2000's,\nthese file types were widley spread on piracy software\nsuch as Limewire and Napster  ";
		
	}
   
}

class MP4 extends MyFile
{
	//change the value of the type variable
	public MP4()
	{
		super();
		this.type = "MP4";
	}
	 
	 @Override
	 public String fileTypeInfo()
		{
			return "MP4 is a file extention of MPEG-4 Part 14";
			
		}
	 
}


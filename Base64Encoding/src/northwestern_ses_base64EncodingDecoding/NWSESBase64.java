package northwestern_ses_base64EncodingDecoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.FileReader;


import java.util.Base64;

public class NWSESBase64 {

private static PrintWriter out_f;


public static void main(String[] args) throws IOException {
	
	NWSESBase64 oNWBase64 = new NWSESBase64();
	oNWBase64.NW_CheckStringForUTF_8("test");
	//oNWBase64.NW_Encoding("test");

	
    File in_f = new File("C:/Temp/MainRequirements_Orig5.txt");
    PrintStream out_f = new PrintStream(new FileOutputStream("MainRequirements_Orig5_new"));

	


    //File in_f = new File("C:/Temp/AAR_LongDescr.txt");
    //PrintStream out_f = new PrintStream(new FileOutputStream("C:/Temp/AAR_LongDescr_new.txt"));
    
    
    BufferedReader b = new BufferedReader(new FileReader(in_f));
    String aLine=b.readLine();
    String aLine_new ="";

    while (aLine!=null) {
    	aLine = aLine.trim();
    	aLine_new =oNWBase64.stripNonValidXMLCharacters(aLine);
        out_f.println(aLine_new);
        aLine=b.readLine();
    }
    
    b.close();
    out_f.close();
	
    System.out.println("Done");
    
	//System.out.println(oNWBase64.NW_Encoding("Hello"));	
	//System.out.println(oNWBase64.stripNonValidXMLCharacters("***COURSES NOT USED FOR DEGREE/MAJOR/MINOR REQUIREMENTS*** (R 831)"));
	
}


public boolean NW_CheckStringForUTF_8(String in_String) {
    byte[] myBytes;
	try {

	      String msg = "¿– Equivalent of a full year of advanced study; may";
	      try 
	      {
	    	  myBytes = msg.getBytes("UTF-8");
	    	  for (int i=0; i < myBytes.length; i++) {
	    		    System.out.println(myBytes[i]);
	    		}

	      } 
	      catch (UnsupportedEncodingException e)
	      {
	          e.printStackTrace();
	          //System.exit(-1);
	      }

	      
		return true;

	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}

}

public boolean NW_Encoding(String in_String) {
	try {

	      String msg = "¿– Equivalent of a full year of advanced study; mayHello, Base64!\n<button onClick=\"window.open('https://forms.apps.northwestern.edu/view.php?id=204199')\">AP/IB DISTRO REQUEST FORM</button>";
	      Base64.Encoder enc = Base64.getEncoder();
	      byte[] encbytes = enc.encode(msg.getBytes());
	      for (int i = 0; i < encbytes.length; i++)
	      {
		     //System.out.printf("%c", (char) encbytes[i]);
		    System.out.print((char) encbytes[i]);
	         if (i != 0 && i % 4 == 0)
	            System.out.print(' ');
	      }
	      System.out.println();	      
	      System.out.println(encbytes.length);	   
	      
	      this.NW_Decoding(encbytes);
	      
		return true;

	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}

}

/* public static void main(String[] args) throws IOException { */
public boolean NW_Decoding(byte[] in_bytesArr) {
	try {

	      Base64.Decoder dec = Base64.getDecoder();
	      byte[] decbytes = dec.decode(in_bytesArr);
	      System.out.println(new String(decbytes));
		return true;

	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}

}


public String stripNonValidXMLCharacters(String in_String)
{
    StringBuilder textOut = new StringBuilder(); // Used to hold the output.
    char current; // Used to reference the current character.

    for (int i = 0; i < in_String.length(); i++) {
        current=in_String.charAt(i);

       // if ((current == 0x9 || current == 0xA || current == 0xD) ||
       //     ((current >= 0x20) && (current <= 0xD7FF)) ||
        //    ((current >= 0xE000) && (current <= 0xFFFD)) ||
        //    ((current >= 0x10000) && (current <= 0x10FFFF)))
        
        if ((current == 0x9 || current == 0xA || current == 0xD) ||
                ((current >=0x00) && (current <= 0x7f)))  
        {
            textOut.append(current);
        }
    }
    return textOut.toString();
}

/*
public static String EscapeNonXMLChars(String in_String)
{
    StringBuilder textOut = new StringBuilder();
    char current; // Used to reference the current character.
    
    for (int i = 0; i < in_String.length(); i++) {
        current=in_String.charAt(i);

        if (XmlConvert.IsXmlChar(current))
        {
            textOut.append(current);
        }
    }
    return textOut.toString();
}
*/
}

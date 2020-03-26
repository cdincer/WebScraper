import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       int counter=0;
       String TemporaryHolder="";
       ArrayList<String> MyArrayList= new ArrayList<String>();
       
    	Document doc = Jsoup.connect("https://www.isbank.com.tr/fiyatoran/FiyatTabloGosterV2.asp?trkd=*EUB&tip=HTML").get();
    	String title = doc.title();

Elements body = doc.getElementsByClass("table_header_grey");
Elements links =  body.select("tr");
	for (Element link : links)
	{
	
			String Element= link.toString();
			
			Element = Cleaner(Element);
			TemporaryHolder+=Element;
			MyArrayList.add(TemporaryHolder);
			TemporaryHolder="";
			
		
	 
	}
		for(String Item : MyArrayList)
		{
	      	System.out.println(Item);	
		}
    	

    }

   
 public static String Cleaner(String ItemsToBeCleaned)
 {

     
	 String Result="";
	 String[] replacements = {"<td valign=\"top\" colspan=\"1\">","<td>","</td>","<tr>","</tr>"};
	 for(String Item : replacements)
	 {
	     ItemsToBeCleaned = ItemsToBeCleaned.replaceAll(Item, "");
	 }
	
     
     
		String[] ItemsArray = ItemsToBeCleaned.split("\\r?\\n");

 
		 for(String SplitItem : ItemsArray)
		 {
			 Result+=SplitItem;
		 }
 
	 
	 return Result;
 }
    
    
}

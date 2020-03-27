
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.junit.JWebUnit.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * Scraping eurobond table.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       String TemporaryHolder="";
       String StartingUrl="https://www.isbank.com.tr/fiyatoran/FiyatTabloGosterV2.asp?trkd=*EUB&tip=HTML";
       String EuroBondCnt="btnEuroBondDevam";
	   String ButtonName="WSUB"; 
	   ArrayList<String> ReceivedItemsList = new ArrayList<String>();

	 WebClient webClient = new WebClient();
	 HtmlPage page = webClient.getPage(StartingUrl);
	 HtmlForm form = page.getFormByName("frm1");
	 
	 HtmlSubmitInput  ItemButton =  form.getInputByName(ButtonName);
	 HtmlPage page2 = ItemButton.click();
	 //Item is in string.
	 String pageAsText = page.asText();
	 String pageAsText2 = page2.asText();

	System.out.println("First Page");
    ReceivedItemsList = Cleaner(pageAsText);
	
    for(String Item : ReceivedItemsList)
	 {
		 	System.out.println(Item);

	 }
    

	        
    }

   
    //Not needed thanks to HtmlUnit
 public static  ArrayList<String> Cleaner(String ItemsToBeCleaned)
 {

	 ArrayList<String> MyItemsList = new ArrayList<String>();
     
	// String Result="";
	 //String[] replacements = {"<td valign=\"top\" colspan=\"1\">","<td>","</td>","<tr>","</tr>"};
	 String[] ItemsArray = ItemsToBeCleaned.split("\\r?\\n");
	 
	 
	 //Initial cleaning up for top and bottom of tables and the bond warnings.
	 for(String Item : ItemsArray)
	 {

		 	if(Item != null && !Item.isEmpty())
		 		if(Item.charAt(0) != '*' && Character.isDigit(Item.charAt(0)))
		 		{
		 		MyItemsList.add(Item);
		 		}
	 }
	
	 return MyItemsList;
 }
    
    
}

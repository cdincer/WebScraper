
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


/**
 * Scraping eurobond table.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       String TemporaryHolder="";
       String StartingUrl="https://www.isbank.com.tr/fiyatoran/FiyatTabloGosterV2.asp?trkd=*EUB&tip=HTML";
       String EuroBondCnt="btnEuroBondDevam";

	String ButtonName="WSUB";

	 WebClient webClient = new WebClient();
	 HtmlPage page = webClient.getPage(StartingUrl);
	 HtmlForm form = page.getFormByName("frm1");
	 
	 HtmlSubmitInput  ItemButton =  form.getInputByName(ButtonName);
	 HtmlPage page2 = ItemButton.click();
	 //Item is in string.
	 String pageAsText = page.asText();
	 String pageAsText2 = page2.asText();

	System.out.println("First Page");
	System.out.println(pageAsText);
	System.out.println("Second Page");
	System.out.println(pageAsText2);

	        
	        
    }

   
    //Not needed thanks to HtmlUnit
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

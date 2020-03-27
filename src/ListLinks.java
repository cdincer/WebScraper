
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
	 ReceivedItemsList = Cleaner(page.asText());
	 Printer(ReceivedItemsList);
	 
	 while(ItemButton != null)
	 {
		 page = ItemButton.click();
		 String pageAsText = page.asText();
		 ReceivedItemsList = Cleaner(pageAsText);
		 Printer(ReceivedItemsList);
		 form = page.getFormByName("frm1");
		 try
		 {
			 ItemButton= form.getInputByName(ButtonName);
		 } 
		 catch (Exception e)
		 {
			 break;
		 } 
	 }
	 
   }
   
    //Not needed thanks to HtmlUnit
 public static  ArrayList<String> Cleaner(String ItemsToBeCleaned)
 {

	 ArrayList<String> MyItemsList = new ArrayList<String>();
	 ArrayList<String> MyItemsList2 = new ArrayList<String>();//I will remove this when I have the energy to deal with concurrentoperation error.
	 int counter=0;
	 int FirstPrice,LastPrice,DateToPayment;
	 DateToPayment=5;
	 FirstPrice=9;//10 Table Spots
	 LastPrice=10;//11
	 String Result="";
	 String[] ItemsArray = ItemsToBeCleaned.split("\\r?\\n");
	 
	 
	 //Initial cleaning up for top and bottom of tables and the bond warnings.
	 for(String Item : ItemsArray)
	 {

		 	if(Item != null && !Item.isEmpty())
		 		if(Item.charAt(0) != '*' && Character.isDigit(Item.charAt(0)))
		 		{
		 	    Item= Item.replace("***", "");
		 		MyItemsList.add(Item);
		 		}
	 }
	 
	 //Attempt at preparing it for database
	 for(String Item : MyItemsList)
	 {
		 Item = Item.replace("\t", " ");
		 String[] Temp = Item.split(" ");
		 Result="";
		 counter=0;
		 for(int i=0; i<Temp.length;i++)
		 {
			 if(counter==DateToPayment  && Temp[i].length()<3)
			 {
				Temp[i] = Temp[i].length() ==2 ? Temp[i].toString()+ " " : Temp[i].toString()+ "  ";
			 }
			 
			 if(counter==FirstPrice || counter==LastPrice  && Temp[i].length()<7)
			 {
				 Temp[i]="  "+Temp[i].toString(); 
			 }
			 Result+=Temp[i]+"     ";

			 counter++;
		 }
		 MyItemsList2.add(Result);
	 }
	 
	 
	 
	 
	 
	
	 return MyItemsList2;
 }
    
 public static void Printer(ArrayList<String> ItemToBePrinted)
{
    	
	    for(String Item : ItemToBePrinted)
		 {
			 	System.out.println(Item);
		 }

}
 
}

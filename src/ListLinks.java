import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import javax.print.Doc;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       
    	Document doc = Jsoup.connect("https://www.isbank.com.tr/fiyatoran/FiyatTabloGosterV2.asp?trkd=*EUB&tip=HTML").get();
    	String title = doc.title();

Elements body = doc.getElementsByClass("table_header_grey");
Elements links =  body.select("tr");
for (Element link : links)
{
  String linkHref = link.attr("href");
  String linkText = link.text();
}
    	
System.out.println("Line gotten from the shenanigans"+title);

    }

   
}


import com.gargoylesoftware.htmlunit.WebClient;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.operations.BondCleaner;
import org.hibernate.operations.BondInserter;

/**
 * Scraping eurobond table.
 */
public class MainLink {
	public static void main(String[] args) throws IOException {
		String StartingUrl = "https://www.isbank.com.tr/fiyatoran/FiyatTabloGosterV2.asp?trkd=*EUB&tip=HTML";
		String ButtonName = "WSUB";
		ArrayList<String> ReceivedItemsList = new ArrayList<String>();
		BondInserter MyTest = new BondInserter();

		@SuppressWarnings("resource")
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(StartingUrl);
		HtmlForm form = page.getFormByName("frm1");
		HtmlSubmitInput ItemButton = form.getInputByName(ButtonName);
		ReceivedItemsList = BondCleaner.Cleaner(page.asText());
		Printer(ReceivedItemsList);
		MyTest.BondAdder(ReceivedItemsList);

		while (ItemButton != null) {
			page = ItemButton.click();
			String pageAsText = page.asText();
			ReceivedItemsList = BondCleaner.Cleaner(pageAsText);
			Printer(ReceivedItemsList);
			MyTest.BondAdder(ReceivedItemsList);
			form = page.getFormByName("frm1");
			try {
				ItemButton = form.getInputByName(ButtonName);
			} catch (Exception e) {
				break;
			}
		}

		// MyTest.BondAddTest();

	}

	// Not needed thanks to HtmlUnit

	public static void Printer(ArrayList<String> ItemToBePrinted) {
		for (String Item : ItemToBePrinted) {
			System.out.println(Item);
		}

	}

}

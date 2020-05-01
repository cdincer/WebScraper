package org.hibernate.operations;

import java.util.ArrayList;

public class BondCleaner {
	
	
	 public static  ArrayList<String> Cleaner(String ItemsToBeCleaned)
	 {

		 ArrayList<String> bondListP1 = new ArrayList<String>();
		 ArrayList<String> bondListP2 = new ArrayList<String>();//I will remove this when I have the energy to deal with concurrentoperation error.
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
			 		bondListP1.add(Item);
			 		}
		 }
		 
		 //Attempt at preparing it for database
		 for(String Item : bondListP1)
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
				 Result+=Temp[i]+" ";

				 counter++;
			 }
			 bondListP2.add(Result);
		 }
		 
		 
		 
		 
		
		 return bondListP2;
	 }

}

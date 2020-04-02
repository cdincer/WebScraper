/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.standard.DateTimeAtCompleted;

import junit.framework.TestCase;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 *
 * @author Steve Ebersole
 */
public class BondInserter  {
	private EntityManagerFactory entityManagerFactory;



	public void BondAddTest() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );

		
		Bond myBond = new Bond();
		myBond.setBondNumber(104);
		myBond.setBondCurrency("EUR");
		Date date = new Date(System.currentTimeMillis());

		myBond.setNextPaymentDate(date);
		
		myBond.setItemEntered(date);
		// create a couple of events...
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(myBond );
		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
        List<Bond> result = entityManager.createQuery( "from Bond",Bond.class).getResultList();
        
        
		for ( Bond event : result ) {
			System.out.println( "Event (" + event.getBondCurrency() + ") : " + event.getBondNumber() );
		}
        entityManager.getTransaction().commit();
        entityManager.close();
		entityManagerFactory.close();

	}
	
	
	
	public void BondAdder( ArrayList<String> MyBondList)
	{	try {
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
			}	 catch (Exception e)
	        {
				System.out.println("Single error");
	        }
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        entityManager.getTransaction().begin();
	        
	        //Trying to take care of splitting with flip flopping between multiple spaces 
	        for(String Item : MyBondList)
	   	    {
	        	
	   		Item = Item.replace("  ", " ");		
	   	    String[] Temp = Item.split(" ");
	 
	        Bond myBond = new Bond();
			myBond.setBondNumber(Integer.parseInt(Temp[0].toString()));
			Date Date2 = DateTransformer(Temp[1].toString());
			myBond.setEndOfBond(Date2);
			myBond.setBondCurrency(Temp[2].toString());
			
			//Format Payment Date
		    String sDate1=Temp[4].toString();  
		    SimpleDateFormat formatter1 =new SimpleDateFormat("dd/MM/yyyy");  
		    Date Date1 = new Date();
		    try {
				 Date1 = formatter1.parse(sDate1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myBond.setNextPaymentDate(Date1);
			
			//Interest Rate : for comparing good interest rates.
			String itemSanitization =  Temp[6].toString();		
			itemSanitization = itemSanitization.isBlank()  ?  Temp[7].toString() : Temp[6].toString();

			itemSanitization =itemSanitization.replace(",","");
			myBond.setInterestRate(Double.parseDouble(itemSanitization));
			
	
			
			if(Temp.length>13) 
			{
			//Bank Buys
			 itemSanitization =  Temp[12].toString();
				if(!itemSanitization.isEmpty() && !itemSanitization.isBlank()) 
				{
				itemSanitization =itemSanitization.replace(",","");
				myBond.setBankBuy( Double.parseDouble(itemSanitization));
				}
		
				//Bank Sells
				itemSanitization =  Temp[13].toString();			
				if(!itemSanitization.isEmpty() && !itemSanitization.isBlank())
				{
				itemSanitization =itemSanitization.replace(",","");
				myBond.setBankSell(Double.parseDouble(itemSanitization));
				}
			}else
			{
				itemSanitization =  Temp[10].toString();
				if(!itemSanitization.isEmpty() && !itemSanitization.isBlank()) 
				{
				itemSanitization =itemSanitization.replace(",","");
				myBond.setBankBuy( Double.parseDouble(itemSanitization));
				}
		
				//Bank Sells
				itemSanitization =  Temp[11].toString();			
				if(!itemSanitization.isEmpty() && !itemSanitization.isBlank())
				{
				itemSanitization =itemSanitization.replace(",","");
				myBond.setBankSell(Double.parseDouble(itemSanitization));
				}
			}
			
			
			if(Temp[0].toString().equals("680") || Temp[0].toString().equals("666"))
			{
				//Bank buys
				itemSanitization =  Temp[11].toString();
					if(!itemSanitization.isEmpty() && !itemSanitization.isBlank()) 
					{
					itemSanitization =itemSanitization.replace(",","");
					myBond.setBankBuy( Double.parseDouble(itemSanitization));
					}
			
					//Bank Sells
					itemSanitization =  Temp[12].toString();			
					if(!itemSanitization.isEmpty() && !itemSanitization.isBlank())
					{
					itemSanitization =itemSanitization.replace(",","");
					myBond.setBankSell(Double.parseDouble(itemSanitization));
				
			        }
			}
		
			//SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd '-' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			myBond.setItemEntered(date);
	        entityManager.persist(myBond );
	        
	   	    }
	        entityManager.getTransaction().commit();

	        entityManager.close();
	
			entityManagerFactory.close();	
	}
	
	
	
	public Date DateTransformer(String DateOut)
	{
		String Result = DateOut;
		
		Result = Result.replaceAll("-", "");
		
			String sDate1=Result.toString();  
		    SimpleDateFormat formatter1 =new SimpleDateFormat("dd/MM/yyyy");  
		    Date Date1 = new Date();
		    try {
				 Date1 = formatter1.parse(sDate1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return Date1;
	}
}

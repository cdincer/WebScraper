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



	public void testBasicUsage() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );

		
		Bond myBond = new Bond();
		myBond.setBondNumber(104);
		myBond.setBondCurrency("EUR");
		myBond.setLastPaymentDate("08-06-2022");
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd '-' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		myBond.setItemEntered(formatter.format(date));
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
	{		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        entityManager.getTransaction().begin();
	        
	        
	        for(String Item : MyBondList)
	   	    {
	   	    String[] Temp = Item.split(" ");
	        Bond myBond = new Bond();
			myBond.setBondNumber(104);
			myBond.setBondCurrency("EUR");
			myBond.setLastPaymentDate("08-06-2022");
	        entityManager.persist(myBond );
	   	    }
	        entityManager.getTransaction().commit();
	        entityManager.close();
	
			entityManagerFactory.close();	
	}
	
	
}

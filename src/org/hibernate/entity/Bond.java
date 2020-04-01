package org.hibernate.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Bonds" )
public class Bond {
	
	   Integer BondNumber;
	
	   String LastPaymentDate;
	   String BondCurrency;
	   String ItemEntered;
	    //Other code
	   
	@Id
	public String getItemEntered() {
    return ItemEntered;
	}
	public void setItemEntered(String itemEntered) {
		ItemEntered = itemEntered;
	}
		@Column(name = "Bond_No")
	public Integer getBondNumber() {
		return BondNumber;
	}
	public void setBondNumber(Integer bondNumber) {
		BondNumber = bondNumber;
	}
	public String getLastPaymentDate() {
		return LastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		LastPaymentDate = lastPaymentDate;
	}
	public String getBondCurrency() {
		return BondCurrency;
	}
	public void setBondCurrency(String bondCurrency) {
		BondCurrency = bondCurrency;
	}

}

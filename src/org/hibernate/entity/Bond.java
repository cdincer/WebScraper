package org.hibernate.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Bonds" )
public class Bond {
	
	Integer IdItem;
		
		@Id
		@Column(name = "TransactionId")
		@GeneratedValue(strategy=GenerationType.AUTO)
	    public Integer getIdItem() {
			return IdItem;
		}
		public void setIdItem(Integer idItem) {
			IdItem = idItem;
		}
		
		
	   Integer BondNumber;	
	   String LastPaymentDate;
	   String BondCurrency;
	   Date ItemEntered;
	    //Other code
	   
	public Date getItemEntered() {
    return ItemEntered;
	}
	public void setItemEntered(Date itemEntered) {
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

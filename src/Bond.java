
//@Entity
//@Table(name ="BondsTable")
public class Bond
{
   Integer BondNumber;
   String LastPaymentDate;
   String BondCurrency;
    //Other code
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
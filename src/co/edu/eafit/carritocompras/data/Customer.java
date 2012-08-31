package co.edu.eafit.carritocompras.data;

import java.math.BigDecimal;
import java.util.List;

public class Customer {

	private String code;
	private String name;
	private List<Purchase> purchases;
	public int points;
	public static int pointsPurchase;
	public BigDecimal discountPoints;

	public Customer(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int calculatePoints(Purchase result) 
	{
		// TODO Auto-generated method stub
		pointsPurchase = (result.getTotalPrice().intValue())/1000;
		return pointsPurchase;
	}

	public static Object addPoints(int points, Purchase result) {
		// TODO Auto-generated method stub
		points = points + pointsPurchase;
		return null;
	}

	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	public BigDecimal calculateDiscountPoints(Purchase result) {
		// TODO Auto-generated method stub
		discountPoints = result.getTotalPrice().multiply(new BigDecimal(0.02));
		return discountPoints;
	}

}

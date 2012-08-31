package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal; 
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Product;
import co.edu.eafit.carritocompras.data.Purchase;
import co.edu.eafit.carritocompras.data.PurchaseStatus;
import co.edu.eafit.carritocompras.data.util.ChangeStatusException;

public class BillingCalculator {

	/**
	 * @param productsFlatFile
	 *            Comma-separated codes of products (e.g. code1, code2, code3)
	 * 
	 *            Postcondition: totalPrice attribute is updated with the
	 *            totalAmount of prices of products included
	 */
	public static Purchase calculateTotalPurchase(Customer customer, String productsFlatFile) {
		Purchase purchase = new Purchase(customer);
		List<Product>products = new ArrayList<Product>();
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal value = BigDecimal.ZERO;
		for (String code : productsFlatFile.split(",")) {
			Product p = Product.buildProduct(code);
			value = CalculateIva.iva(p.getCode());
			total = total.add(p.getPrice()).add(p.getPrice()).multiply(value);
			total = total.subtract(p.calculateDiscount());
			products.add(p);
		}
		int point = Customer.calculatePoints(purchase);
		Customer.addPoints(point, purchase);
		int pointDiscount = purchase.getCustomer().getPoints();
		if (pointDiscount > 1000)
		{
			total = total.subtract(total.multiply(new BigDecimal(0.02)));
		}
		purchase.setProducts(products);
		purchase.setTotalPrice(total);
		try {
			purchase.setStatus(PurchaseStatus.PENDING);
		} catch (ChangeStatusException e) {
			throw new RuntimeException("Error setting pending status");
		}
		
		return purchase;
	}
}

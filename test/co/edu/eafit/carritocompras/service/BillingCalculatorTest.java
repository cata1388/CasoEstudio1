package co.edu.eafit.carritocompras.service;

import co.edu.eafit.carritocompras.data.Customer; 
import co.edu.eafit.carritocompras.data.Purchase;
import co.edu.eafit.carritocompras.data.Product;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.*;
import org.mockito.Mockito;

//import static org.mockito.*;
//import org.junit.Test;
//import org.junit.Before;
import static org.mockito.Mockito.when;

@SuppressWarnings("unused")
public class BillingCalculatorTest {
	
    private Customer customer;
    private BillingCalculator billingCalculator;
    
    
    @Before
    public void before(){
        customer = new Customer("a11","b");
        billingCalculator = new BillingCalculator();
    }
        
    /**
     * Test of calculateTotalPurchase method, of class BillingCalculator.
     */
    @Test
    public void testCalculateTotalPurchase() {
        String productsFlatFile = "EL-002,FU-007,FU-008";
        Purchase result = null;
        BigDecimal discounts = BigDecimal.ZERO;
        BigDecimal price = BigDecimal.ZERO;
        result = BillingCalculator.calculateTotalPurchase(customer, productsFlatFile);
        for (String code : productsFlatFile.split(","))
        {
            Product p = Product.buildProduct(code);
            if (code.startsWith("EL"))
            {
                //true values
                price = price.add(p.getPrice());
                // just discounts
                discounts = discounts.add(p.getPrice().multiply(new BigDecimal(0.05)));
            }
            else if (code.startsWith("FU"))
            {
                price = price.add(p.getPrice());
                discounts = discounts.add(p.getPrice().multiply(new BigDecimal (0.1)));
            }
        }
        assertEquals(result.getTotalPrice().add(discounts),price);  
    }
    
    /** 
     * test of iva method of class CalculateIva
     */
    @Test
    public void testCalculateIva()
    {
    	CalculateIva calculateIva = Mockito.mock(CalculateIva.class);
    	Purchase p = BillingCalculator.calculateTotalPurchase(customer, "EL-003,FU-001");
    	
    	when(CalculateIva.iva(p.getCode())).thenReturn(new BigDecimal(Math.random()*0.05));
    	
    } 

}

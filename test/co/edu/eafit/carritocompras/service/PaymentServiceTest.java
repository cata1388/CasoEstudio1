package co.edu.eafit.carritocompras.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Purchase;

public class PaymentServiceTest {

	private Customer customer;
	private PaymentService paymentService;
	
	@Before
	private void setUp() {
		customer = new Customer("xx1", "xxName");
		paymentService = new PaymentService();
	}
	
	@Test
	public void testPay() {
		GenericCreditCardService creditCardService = Mockito.mock(GenericCreditCardService.class);
		Purchase p = BillingCalculator.calculateTotalPurchase(customer, "EL-001,FU-002");
		
		//Mocking external service behavior
//		when(creditCardService.pay("xxxx111xxxx", p.getTotalPrice())).thenReturn(true);
		when(creditCardService.pay("xxxx111xxxx", p.getTotalPrice())).thenReturn(true);
		
		paymentService.pay(customer, p, "xxxx111xxxx", creditCardService);
		
	}

}

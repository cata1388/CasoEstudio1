package co.edu.eafit.carritocompras.service;
import java.math.*;

//import co.edu.eafit.carritocompras.data.Product;

public class CalculateIva {
	
	public static BigDecimal iva(String code)
	{
		//complex code...
		BigDecimal constIva = new BigDecimal(Math.random()*0.05);
		return constIva;
	}

}

package example.client;

import java.math.BigDecimal;

/**
 * 
 * @author manisha
 *
 */
public class Settings {
	
	//source bank who published the price
	public static final String bank1 = "Deutsche Bank";
	public static final String bank2 = "Sparkasse";
	public static final String bank3 = "Commerz Bank";
	public static final String bank4 = "HVB Bank";
	
	//for example "EUR/USD"
	public static final String symbol1 = "GBP";
	public static final String symbol2 = "EUR";
	public static final String symbol3 = "CHF";
	public static final String symbol4 = "USD";
	public static final String symbol5 = "CAD";
	
	//current price update
	public static final BigDecimal price1 = new BigDecimal(0.7844);
	public static final BigDecimal price2 = new BigDecimal(0.7846);
	public static final BigDecimal price3 = new BigDecimal(1.0989);
	public static final BigDecimal price4 = new BigDecimal(1.2537);
	public static final BigDecimal price5 = new BigDecimal(1.2537);
	
}

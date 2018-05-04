import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testSum() {
		double result;
		result = Calculator.sum(4, 5);
		assertTrue("The result should be 9 : " , (result==9));
//		fail("Not yet implemented");
	}

	@Test
	void testSubtract() {
		double result;
		result = Calculator.subtract(5, 4);
		assertTrue("The result should be 1 : " , (result==1));
		
	}

	@Test
	void testMultiply() {
		double result;
		result = Calculator.multiply(4 , 5);
		assertTrue("The result should be 20 : " , (result==20));
	}

	@Test
	void testDivide() {
		double result;
		result = Calculator.divide(10, 2);
		assertTrue("The result should be 5 : " , (result==5));
	}

}

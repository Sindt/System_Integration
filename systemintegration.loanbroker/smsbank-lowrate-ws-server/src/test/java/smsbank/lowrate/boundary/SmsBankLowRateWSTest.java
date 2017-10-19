package smsbank.lowrate.boundary;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SmsBankLowRateWSTest {

	SmsBankLowRateWS bank = new SmsBankLowRateWS();

	@Test
	public void testGetInterestRate() {
		int loanDuration = 10;
		int loanAmount = 45000;
		double result = bank.getInterestRate(loanDuration, 800, loanAmount);
		double expected = 9.465094947931416;
		assertThat(result, is(equalTo(expected)));
	}

}

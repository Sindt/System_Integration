package smsbank.lowrate.boundary;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import smsbank.lowrate.entity.LoanResponse;

public class SmsBankLowRateWSTest {

	SmsBankLowRateWS bank = new SmsBankLowRateWS();

	@Test
	public void test() {
		int loanDuration = 10;
		int loanAmount = 45000;
		String ssn = "112233-4455";
		LoanResponse result = bank.loanRequest(loanDuration, 123, loanAmount, ssn);
		assertThat(result.getSsn(), is(equalTo(ssn)));
	}

}

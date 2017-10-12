package rulebase.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService
public class RuleBaseWS {

	private final String smsBankHighRate = "smsBankHighRate";
	private final String smsBankLowRate = "smsBankLowRate";
	private final String jsonBank = "jsonBank";
	private final String xmlBank = "xmlBank";
	private List<String> banks = new ArrayList<>();

	/*
	 * RULES : Amount: XML, JSON : Min 25.000 SMS : MAX 50.000
	 * 
	 * Duration: XML, JSON if amount < 50.000 = MAX : 10 else MAX: 30
	 * 
	 * SMSLow = MAX 10
	 * 
	 * 
	 * CreditScore
	 * 
	 * 0-100 - Bad: SMS HIGH, 100-400 - Fair: SMSHigh, XML, 400-700 - Good: SMSHigh,
	 * SMSLow XML 700-800 - Excellent: SMSHigh, SMSLow XML, JSON
	 * 
	 * 
	 */
	public List<String> getBanks(int loanAmount, int loanDuration, int creditScore) {

		if (creditScore < 101) {
			banks.add(smsBankHighRate);
		} else if (creditScore < 401) {
			banks.add(smsBankHighRate);
			banks.add(xmlBank);
		} else if (creditScore < 701) {
			banks.add(smsBankHighRate);
			banks.add(xmlBank);
			banks.add(smsBankLowRate);
		} else {
			banks.add(smsBankHighRate);
			banks.add(xmlBank);
			banks.add(smsBankLowRate);
			banks.add(jsonBank);
		}

		if (loanAmount <= 25000) {
			banks.remove(jsonBank);
			banks.remove(xmlBank);
			if (loanDuration > 10) {
				banks.remove(smsBankLowRate);
			}

		} else if (loanAmount <= 50000) {
			if (loanDuration > 10) {
				banks.remove(jsonBank);
				banks.remove(xmlBank);
				banks.remove(smsBankLowRate);
			}
		} else {
			banks.remove(smsBankLowRate);
			banks.remove(smsBankHighRate);
			if (loanDuration > 30) {
				banks.remove(jsonBank);
				banks.remove(xmlBank);
			}
		}
		return banks;

	}
}

package rulebase.boundary;

import javax.xml.ws.Endpoint;

public class Exporter {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:3000/loanbroker-receiver/rulebase", new RuleBaseWS());
	}

}

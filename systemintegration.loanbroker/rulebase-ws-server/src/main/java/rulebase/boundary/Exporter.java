package rulebase.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.ws.Endpoint;

@Singleton
@Startup
public class Exporter {

	@PostConstruct
	public void init() {
		Endpoint.publish("http://localhost:3000/loanbroker-receiver/rulebase", new RuleBaseWS());
	}

}

package loanbroker.aggregator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer extends EndPoint {

	public Producer(String endpointName) throws IOException, TimeoutException {
		super(endpointName);
	}

	public void sendMessageBasic(byte[] message) throws IOException  {
	    channel.basicPublish("",endPointName, null, message);
	}
}

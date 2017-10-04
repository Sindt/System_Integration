package loanbroker.endpoint;

import java.io.IOException;

public class Producer extends EndPoint {

	public Producer(String endpointName) throws IOException {
		super(endpointName);
	}

	public void sendMessageBasic(byte[] message) throws IOException  {
	    channel.basicPublish("",endPointName, null, message);
	}
}

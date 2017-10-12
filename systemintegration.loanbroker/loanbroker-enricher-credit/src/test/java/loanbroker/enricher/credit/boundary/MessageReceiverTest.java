package loanbroker.enricher.credit.boundary;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Incubating;

import loanbroker.enricher.credit.boundary.MessageReceiver;

public class MessageReceiverTest {

	private final MessageReceiver receiver = new MessageReceiver();

	@Test
	public void testInit() {
		receiver.init();
	}

}

package loanbroker.enricher.bank.boundary;

import static org.junit.Assert.*;

import javax.resource.spi.ResourceAdapterInternalException;

import org.junit.Ignore;
import org.junit.Test;

public class MessageReceiverTest {

	private final MessageReceiver receiver = new MessageReceiver();

	@Test
	@Ignore
	public void testInit() throws ResourceAdapterInternalException {
		receiver.init();
	}

}

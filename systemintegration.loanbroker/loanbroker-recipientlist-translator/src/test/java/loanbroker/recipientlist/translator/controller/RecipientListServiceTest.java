package loanbroker.recipientlist.translator.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class RecipientListServiceTest {

	RecipientListService service = new RecipientListService();

	@Test
	public void testGetRecepientsWithBanks() {
		String[] banks = new String[] { "xmlBank", "jsonBank" };
		JSONObject message = new JSONObject().put("banks", banks).put("ssn", "121290-2142");

		List<String> result = service.getRecipients(message);

		assertThat(result, is(equalTo(Arrays.asList(banks))));
	}
	@Test
	public void testGetRecepientsWithJSONArray() {
		String[] banks = new String[] { "xmlBank", "jsonBank" };
		JSONArray arr = new JSONArray(banks);
		JSONObject message = new JSONObject().put("banks", arr).put("ssn", "121290-2142");
		
		List<String> result = service.getRecipients(message);
		
		assertThat(result, is(equalTo(Arrays.asList(banks))));
	}

	@Test
	public void testGetRecepientsWithOutBanks() {
		String[] banks = new String[0];
		JSONObject message = new JSONObject().put("banks", banks);

		List<String> result = service.getRecipients(message);

		assertThat(result, is(equalTo(Arrays.asList(banks))));
	}

}

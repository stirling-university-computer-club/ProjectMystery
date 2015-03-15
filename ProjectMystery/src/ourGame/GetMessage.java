package ourGame;
// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;

public class GetMessage {
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "ACb9d7bee53aa45fc1a880c42d85ed820d";
	public static final String AUTH_TOKEN = "48039ebccab9277419dec8465461250b";

	public String returnMessage(String sid) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		// Get an object from its sid. If you do not have a sid,
		// check out the list resource examples on this page
		Message message = client.getAccount().getMessage(sid); //format: "MM800f449d0399ed014aae2bcc0cc2f2ec"
		System.out.println(message.getBody());
		return(message.getBody());
	}
}
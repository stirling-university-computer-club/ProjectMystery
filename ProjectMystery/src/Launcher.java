import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;
public class Launcher {
	
	 // Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "ACb9d7bee53aa45fc1a880c42d85ed820d";
	public static final String AUTH_TOKEN = "48039ebccab9277419dec8465461250b";
	public static void main(String[] args) throws TwilioRestException 
	{
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		// Build a filter for the MessageList
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", "Horse Horse Horse"));
		params.add(new BasicNameValuePair("To", "+447581650196"));
		params.add(new BasicNameValuePair("From", "+44 121 285 5617"));
		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = messageFactory.create(params);
		System.out.println(message.getSid());
	}

}

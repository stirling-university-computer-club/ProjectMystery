package com.twilio;

import ourGame.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Sms;

public class TwilioServlet extends HttpServlet {
	public static final String ACCOUNT_SID = "ACb9d7bee53aa45fc1a880c42d85ed820d";
	public static final String AUTH_TOKEN = "48039ebccab9277419dec8465461250b";
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession(true);
		String fromNumber = request.getParameter("From");
		String includedMessage = request.getParameter("Body");
		try {
			sendOutput(includedMessage, fromNumber);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//		String message = fromName + " has messaged " + toNumber + " "
//				+ String.valueOf(count) + " times.";
		// Create a TwiML response and add our friendly message.
//		TwiMLResponse twiml = new TwiMLResponse();
//		Sms sms = new Sms(message);
//		try {
//			twiml.append(sms);
//		} catch (TwiMLException e) {
//			e.printStackTrace();
//		}
//		response.setContentType("application/xml");
//		response.getWriter().print(twiml.toXML());
	public void sendOutput(String gameOutput, String phoneNumber) throws TwilioRestException 
	{
		System.out.println(phoneNumber);
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		// Build a filter for the MessageList
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", gameOutput)); //format: "Horse Horse Horse"
		params.add(new BasicNameValuePair("To", phoneNumber)); // format: "+44 121 285 5617"
		params.add(new BasicNameValuePair("From", "+441212855617"));
		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = messageFactory.create(params);
		System.out.println(message.getSid());
	}
}

package com.twilio;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Sms;

public class TwilioServlet2 extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession(true);
		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter = new Integer(0);
		}
		/* Increment the counter by one, and store the count in the session. */
		int count = counter.intValue();
		count++;
		session.setAttribute("counter", new Integer(count));
		// Create a dict of people we know.
		HashMap<String, String> callers = new HashMap<String, String>();
		callers.put("+14158675309", "Curious George");
		callers.put("+14158675310", "Boots");
		callers.put("+14158675311", "Virgil");
		String fromNumber = request.getParameter("From");
		String toNumber = request.getParameter("To");
		String fromName = callers.get(fromNumber);
		if (fromName == null) {
			// Use the caller's name
			fromName = "Monkey";
		}
		String message = fromName + " has messaged " + toNumber + " "
				+ String.valueOf(count) + " times.";
		// Create a TwiML response and add our friendly message.
		TwiMLResponse twiml = new TwiMLResponse();
		Sms sms = new Sms(message);
		try {
			twiml.append(sms);
		} catch (TwiMLException e) {
			e.printStackTrace();
		}
		response.setContentType("application/xml");
		response.getWriter().print(twiml.toXML());
	}
}
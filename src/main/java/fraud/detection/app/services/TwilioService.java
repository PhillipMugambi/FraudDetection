package fraud.detection.app.services;

import com.twilio.http.TwilioRestClient;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import fraud.detection.app.models.User;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.MessageFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
    public class TwilioService {
    public void sendSms(User user, String otp) {

        Message.creator(
                new PhoneNumber("+254 112 016790"), // your Twilio number (sender phone number)
                new PhoneNumber("+254 112 016790"), // receiver phone number
                otp
        ).create();
    }
}



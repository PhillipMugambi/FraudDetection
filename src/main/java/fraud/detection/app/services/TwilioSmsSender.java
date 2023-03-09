package fraud.detection.app.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import fraud.detection.app.dto.SmsRequest;
import fraud.detection.app.configurations.TwilioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender  {
    private final TwilioConfig twilioConfig;
    private final static Logger LOGGER= LoggerFactory.getLogger(TwilioSmsSender.class);

    @Autowired
    public TwilioSmsSender(TwilioConfig twilioConfig) {
        super();
        this.twilioConfig = twilioConfig;
    }

    public void SendSms(SmsRequest smsRequest) {
        try {
            //if (isPhoneNumbervalid(smsRequest.getPhoneNumber())){
            Message.creator(new PhoneNumber(smsRequest.getPhoneNumber()),
                    new PhoneNumber(twilioConfig.
                            getTrial_number()), smsRequest.getMessage()).create();
            LOGGER.info("Send sms {}" + smsRequest);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        }
        //else{
   // throw new IllegalArgumentException("Phone Number [" +smsRequest
           // .getPhoneNumber()+"] is Invalid");
    }


   // }

//    private boolean isPhoneNumbervalid(String phoneNumber) {
//        //TODO implement PhoneNumber Validator
//        return true;
//    }
//}

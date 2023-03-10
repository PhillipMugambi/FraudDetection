package fraud.detection.app.services;

import fraud.detection.app.dto.SmsRequest;
import fraud.detection.app.models.User;
import fraud.detection.app.repositories.UserRepository;
import fraud.detection.app.dto.RegisterDTO;
import fraud.detection.app.responses.UniversalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor

public class RegistrationService {
    private  final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final TwilioSmsSender twilioSmsSender;
    public UniversalResponse register(RegisterDTO  request) {
        Random random= new Random();
        String otp = String.valueOf(random.nextInt(1000));
        String phone=request.getMobileNumber();
        try{ if
           (userRepository.findUserByMobileNumber(phone)==null);
            System.out.println("User Already Registered, Please Login");
            UniversalResponse response= UniversalResponse.builder()
                    .message("User Already Registered, Please Login")
                    .status(0)
                    .build();
            return  response;
       }
       catch (Exception ex){
           System.out.println(ex);

       }
        {
            try{
            User userObj = new User();
            var user = userObj.builder()
                    .city(request.getCity()).
                    country(request.getCountry())
                    .currentAddress(request.getCurrentAddress())
                    .dateOfBirth(request.getDateOfBirth())
                    .email(request.getEmail())
                    .firstName(request.getFirstName())
                    .gender(request.getGender())
                    .lastName(request.getLastName())
                    .mobileNumber(request.getMobileNumber())
                    .state(request.getState()).
                    middleName(request.getMiddleName())
                    .occupation(request.getOccupation())
                    .permanentAddress(request.getPermanentAddress())

                    .pinCode(request.getPinCode()).
                    password(passwordEncoder.encode(otp)).build();
            SmsRequest smsRequest = new SmsRequest(request.getMobileNumber(), otp);
            userRepository.save(user);
            try {
                twilioSmsSender.SendSms(smsRequest);
        } catch (Exception ex){
                System.out.println("Sending OTP Failed{}"+ex);
            }
}
            catch (Exception ex){
                System.out.println("Saving User Failed{}"+ex);


//TODO: Save method to be a bolean,if fails to save send error

        }

        }
        UniversalResponse response= UniversalResponse.builder()
                .message("Registration Successful")
                .status(200)
                .build();
        return  response;
    }

}

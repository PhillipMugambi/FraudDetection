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
    public UniversalResponse register(RegisterDTO  request)  throws Exception{
        Random random= new Random();
        String otp = String.valueOf(random.nextInt(1000));
        String phone=request.getMobileNumber();

        if (userRepository.findUserByMobileNumber(phone)==null){
            User userObj= new User();
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
                        .parmanentAddress(request.getParmanentAddress())
                        .pinCode(request.getPinCode()).
                        password(passwordEncoder.encode(otp)).build();
                 SmsRequest smsRequest = new SmsRequest(request.getMobileNumber(), otp) ;
             //System.out.println(smsRequest);
                twilioSmsSender.SendSms(smsRequest);
                userRepository.save(user);


            UniversalResponse response= UniversalResponse.builder()
                    .message("Registration Successful")
                    .status(200).data(user.toString()
                            )
                    .build();
            return  response;

        }
        else {
            UniversalResponse response= UniversalResponse.builder()
                    .message("User Already Exists,Please Login")
                    .build();
            return  response;
        }

    }

}

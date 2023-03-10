package fraud.detection.app.services;

import fraud.detection.app.Utils.JwtTokenUtil;
import fraud.detection.app.dto.AuthDTO;
import fraud.detection.app.repositories.UserRepository;
import fraud.detection.app.responses.UniversalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticateService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public UniversalResponse login(AuthDTO request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getMobileNumber(), request.getPassword()
        ));
        var user = userRepository.findUserByMobileNumber(request. getMobileNumber());
        System.out.println("user is "+ user);
        var gentoken = "";
        if (user != null) {
            log.info("user found");
            gentoken = jwtTokenUtil.createToken(user);


        }
        else {

            UniversalResponse response= UniversalResponse.builder()
                    .message("User not found, Please Register")
                    .build();
            return  response;
        }
        UniversalResponse response= UniversalResponse.builder()
                .message("token{}"+gentoken)
                .build();
        return  response;
    }
}

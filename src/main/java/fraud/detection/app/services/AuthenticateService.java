package fraud.detection.app.services;

import fraud.detection.app.Utils.JwtTokenUtil;
import fraud.detection.app.dto.AuthDTO;
import fraud.detection.app.models.User;
import fraud.detection.app.repositories.UserRepository;
import fraud.detection.app.responses.UniversalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class AuthenticateService {
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    private  UserRepository userRepository;
    public UniversalResponse response;
    public UniversalResponse login(AuthDTO request) {

try {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getMobileNumber(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
}catch (Exception ex){
    UniversalResponse response = UniversalResponse.builder()
                        .message("Username or Password incorrect")
                        .build();
                return response;
}
    try {
        User user = userRepository.findUserByMobileNumber(request.getMobileNumber());
        if (user == null) {
            UniversalResponse response = UniversalResponse.builder()
                    .message("User not found, Please Register")
                    .build();
            return response;
        } else {
            String jwt = jwtTokenUtil.createToken(request.getMobileNumber());
            response.setMessage(jwt);
            UniversalResponse response = UniversalResponse.builder()
                    .message(jwt)
                    .build();
            return response;

        }
    }catch (Exception ex){
        System.out.println(ex);
    }
        return response;
}

}


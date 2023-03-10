package fraud.detection.app.services;

import fraud.detection.app.Utils.JwtTokenUtil;
import fraud.detection.app.dto.AuthDTO;
import fraud.detection.app.models.User;
import fraud.detection.app.repositories.UserRepository;
import fraud.detection.app.responses.UniversalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticateService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public UniversalResponse login(AuthDTO request) {
try {
    Authentication authentication = authenticationManager.authenticate(SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenUtil.createToken(request.getMobileNumber());
    User user = userRepository.findUserByMobileNumber(request.getMobileNumber());
    try {
        if (user == null) {
            UniversalResponse response = UniversalResponse.builder()
                    .message("User not found, Please Register")
                    .build();
            return response;
        } else {
            UniversalResponse response = UniversalResponse.builder()
                    .message(jwt)
                    .build();
            return response;
        }
    }catch (Exception ex){
        System.out.println(ex);
    }
}catch (Exception ex){
    System.out.println(ex);

}



//        var gentoken = "";
//        try {
//            var user = userRepository.findUserByMobileNumber(request.getMobileNumber());
//                if (user != null) {
//                    log.info("user found");
//                       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                               request.getMobileNumber(), request.getPassword()
//                        ));
//                        gentoken = jwtTokenUtil.createToken(user);
//
////                        System.out.println("UsernamePassword{}"+ex);
////                    }
//                }
//            }catch (Exception ex){
//                UniversalResponse response = UniversalResponse.builder()
//                        .message("User not found, Please Register")
//                        .build();
//                return response;
//            }
//
//    }
        UniversalResponse response = UniversalResponse.builder()
                .message("ytytytyty")
                .build();
        return response;
}

}


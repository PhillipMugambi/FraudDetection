package fraud.detection.app.controllers;

import fraud.detection.app.dto.RegisterDTO;
import fraud.detection.app.services.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/fraud/app")
@Validated
@Slf4j
public class RegistrationController {
    @Autowired
      RegistrationService registrationService;
    @PostMapping("/register")
    public ResponseEntity<?> Register( @Valid @RequestBody  RegisterDTO request)
    {
        try{
            return ResponseEntity.ok(registrationService.register(request));
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        ;}


    }
}


package fraud.detection.app.controllers;

import fraud.detection.app.dto.RegisterDTO;
import fraud.detection.app.services.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/fraud/app")
@RequiredArgsConstructor
@Validated
public class RegistrationController {
    public final RegistrationService registrationService;
    @PostMapping("/register")
    public ResponseEntity<?> Register( @Valid @RequestBody  RegisterDTO request) {
       return ResponseEntity.ok(registrationService.register(request));
    }
}


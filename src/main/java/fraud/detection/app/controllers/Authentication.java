package fraud.detection.app.controllers;
import fraud.detection.app.dto.AuthDTO;
import fraud.detection.app.services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/fraud/app")
public class Authentication {
   private final AuthenticateService authenticateService;
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthDTO request) {
      return ResponseEntity.ok(authenticateService.login(request));
   }
   }



package fraud.detection.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthDTO {
    private  String mobileNumber;
    private String password;
}

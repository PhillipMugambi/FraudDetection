package fraud.detection.app.responses;

import lombok.*;

@Builder
@Getter
@Setter
public class UniversalResponse {
    private String status;
    private String message;
    private String data;
}

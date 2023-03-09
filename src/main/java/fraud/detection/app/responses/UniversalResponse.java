package fraud.detection.app.responses;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class UniversalResponse implements Serializable {
    private int status;
    private String message;
    private String data;
}

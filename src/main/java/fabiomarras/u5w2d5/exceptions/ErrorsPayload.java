package fabiomarras.u5w2d5.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Setter
@Getter
public class ErrorsPayload {
    private String message;
    private int httpStatus;
    private Date timestamp;
}

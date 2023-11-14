package fabiomarras.u5w2d5.payloads;

import java.util.Date;
import java.util.List;

public record ErrorsListReponseDTO(String message,
                                   Date timestamp,
                                   List<String> errorsList) {
}

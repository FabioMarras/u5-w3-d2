package fabiomarras.u5w2d5.payloads;

import fabiomarras.u5w2d5.Enum.StatusDispositivi;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewDispositivoRequestDTO(
        @NotNull(message = "Devi inserire obbligatoriamente il tipo")
                @Size(min = 2, message = "Scrivi più di 2 caratteri")
        String type,
        @NotNull
        StatusDispositivi  statusDispositivi,
        List<String> errorsList) {
}

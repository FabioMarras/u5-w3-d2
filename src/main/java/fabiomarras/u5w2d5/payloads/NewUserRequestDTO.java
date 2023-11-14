package fabiomarras.u5w2d5.payloads;

import fabiomarras.u5w2d5.entities.Dispositivo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewUserRequestDTO(
        @NotNull(message = "Username obbligatorio")
        @Size(min = 4, message = "Scrivi un username con più di 4 caratteri")
        String username,
        @NotNull(message = "Inserisci il tuo nome obbligatoriamente")
        @Size(min = 2, message = "Il nome deve avere più di 2 caratteri")
        String name,
        @NotNull(message = "Non hai un LastName? :(")
        @Size(min = 2, message = "LastName deve avere più di 2 caratteri")
        String lastName,
        @NotBlank(message = "L'email non può essere vuota")
        @Email(message = "Inserisci un indirizzo email valido")
        String email,
        String avatar,
        String password,
        List<Dispositivo> dispositivo,

        List<String> errorsList
                                ) {
}

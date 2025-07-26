package app.demo.payment.auth.api.domain.model;
import jakarta.validation.constraints.NotBlank;

public record AuthToken(@NotBlank String token) {

}

package app.demo.payment.auth.api.domain.model;

import app.demo.payment.common.api.domain.exception.validation.ValidUserField;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@ValidUserField String user,
                          @NotBlank String password) {

}

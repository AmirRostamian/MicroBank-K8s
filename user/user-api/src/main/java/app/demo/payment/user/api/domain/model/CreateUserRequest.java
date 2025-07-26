package app.demo.payment.user.api.domain.model;

import app.demo.payment.common.api.domain.exception.validation.ValidUserField;
import jakarta.validation.constraints.NotBlank;


public record CreateUserRequest(@ValidUserField String user,
                                @NotBlank String passwordHash) {

}

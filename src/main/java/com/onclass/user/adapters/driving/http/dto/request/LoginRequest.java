package com.onclass.user.adapters.driving.http.dto.request;

import com.onclass.user.adapters.driving.http.util.RequestConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Email(message = RequestConstants.FIELD_EMAIL_INVALID_FORMAT_MESSAGE)
    String email;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Pattern(regexp = RequestConstants.PASSWORD_PATTERN, message = RequestConstants.FIELD_PASSWORD_INVALID_FORMAT_MESSAGE)
    String password;
}
package com.onclass.user.adapters.driving.http.dto.request;




import com.onclass.user.adapters.driving.http.util.RequestConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddUserRequest {
    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    private String firstName;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    private String lastName;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Pattern(regexp = RequestConstants.NUMERIC_PATTERN, message = RequestConstants.FIELD_CONTAINS_ONLY_NUMBERS_MESSAGE)
    private String document;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Pattern(regexp = RequestConstants.NUMERIC_PATTERN, message = RequestConstants.FIELD_CONTAINS_ONLY_NUMBERS_MESSAGE)
    private String phone;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Email(message = RequestConstants.FIELD_EMAIL_INVALID_FORMAT_MESSAGE)
    private String email;

    @NotBlank(message = RequestConstants.FIELD_EMPTY_MESSAGE)
    @Pattern(regexp = RequestConstants.PASSWORD_PATTERN, message = RequestConstants.FIELD_PASSWORD_INVALID_FORMAT_MESSAGE)
    private String password;

}

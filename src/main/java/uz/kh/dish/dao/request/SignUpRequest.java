package uz.kh.dish.dao.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @NotNull
    private String email;
    @NotNull
    private String password;
}

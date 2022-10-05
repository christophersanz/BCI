package cl.bci.expose.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneRequest {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "number is mandatory")
    private String number;

    @NotBlank(message = "citycode is mandatory")
    private String citycode;

    @NotBlank(message = "contrycode is mandatory")
    private String contrycode;

}

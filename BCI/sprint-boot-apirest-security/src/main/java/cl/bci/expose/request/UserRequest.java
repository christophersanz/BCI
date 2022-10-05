package cl.bci.expose.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is mandatory")
	private String name;

	@NotBlank(message = "email is mandatory")
	private String email;

	@NotBlank(message = "password is mandatory")
	private String password;

	@NotBlank(message = "phones is mandatory")
	private List<UserPhoneRequest> phones;

}

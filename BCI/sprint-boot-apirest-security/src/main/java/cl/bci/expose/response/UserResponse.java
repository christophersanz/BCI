package cl.bci.expose.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String created;
	private String modified;
	private String last_login;
	private String token;
	private String isactive;

}

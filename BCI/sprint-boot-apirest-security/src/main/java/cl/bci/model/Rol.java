package cl.bci.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities", uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id", "authority"})})
@Getter
@Setter
public class Rol {
	
	public Rol() {}
	
	public Rol(String authority) {
		this.authority = authority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authority;

}

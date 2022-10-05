package cl.bci;

import cl.bci.model.Rol;
import cl.bci.model.User;
import cl.bci.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableWebSecurity
public class SpringBootApplicationBCISecurity implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserJpaRepository userRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootApplicationBCISecurity.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//users
		List<User> usuarios = new ArrayList<>();

		User user1 = User.builder()
				.username("admin")
				.password("")
				.email("admin@admin.cl")
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(new Rol("ROLE_ADMIN"), new Rol("ROLE_USER")))
				.build();

		User user2 = User.builder()
				.username("christopher")
				.password("")
				.email("admin@admin.cl")
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(new Rol("ROLE_USER")))
				.build();

		usuarios.add(user1);
		usuarios.add(user2);

		String password = "12345";
		for(User usuario: usuarios) {
			String bcryptPassword = passwordEncoder.encode(password);
			usuario.setPassword(bcryptPassword);
			userRepository.save(usuario);
			log.info("Insert: " + usuario.getUsername() + " " + usuario.getPassword());
		}
	}

}
package cl.bci.business.service.impl;

import cl.bci.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cl.bci.business.service.UserService;
import cl.bci.model.Rol;
import cl.bci.model.User;
import cl.bci.repository.UserJpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        User usuario = userJpaRepository.findByUsername(username);
        
        if(usuario == null) {
        	logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(Rol role: usuario.getRoles()) {
        	logger.info("Rol: ".concat(role.getAuthority()));
        	authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	public User create(User user) {
		User userBuilder = User.builder()
				.username(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword()))
				.email(user.getEmail())
				.phones(user.getPhones())
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(new Rol("ROLE_USER")))
				.build();

		User userFindByEmail = userJpaRepository.findByEmail(user.getEmail());
		if (userFindByEmail!=null)
			throw new BadRequestException("El correo ya se encuentra registrado");

		final User userReq = userJpaRepository.save(userBuilder);
		log.info("Insert: " + userReq.getUsername() + " " + userReq.getPassword());

		return userReq;
	}
}

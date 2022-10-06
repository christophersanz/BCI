package cl.bci.business.impl;

import cl.bci.business.UserService;
import cl.bci.exception.GeneralException;
import cl.bci.expose.response.UserResponse;
import cl.bci.model.User;
import cl.bci.repository.UserJpaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Value("${api.auth.token.url}")
    private String apiAuthUrl;

    @Value("${api.auth.create.url}")
    private String apiAuthCreateUserUrl;

    private static final String regex_addresses = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String regex_password = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    @SneakyThrows
    @Override
    public UserResponse create(User user) {
        UserResponse userResponse = new UserResponse();

        // valid email addresses
        validate(regex_addresses, user.getEmail(), "Error en el formato del correo.");

        // valid password
        validate(regex_password, user.getPassword(), "Error en el formato del password.");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", getServiceToken("",""));

        User body = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .phones(user.getPhones())
                .build();

        HttpEntity<User> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiAuthCreateUserUrl, HttpMethod.POST, requestEntity, String.class);
        log.info("API response: {} - {}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            String token = getServiceToken(user.getUsername(), user.getPassword());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            userResponse.setId(root.path("id").asText());
            userResponse.setCreated(root.path("creationDate").asText());
            userResponse.setModified(root.path("lastModifiedDate").asText());
            userResponse.setLast_login(root.path("lastModifiedBy").asText());
            userResponse.setToken(token);
            userResponse.setIsactive(root.path("enabled").asText());
            return userResponse;
        }

        return userResponse;
    }

    public void validate(String regex, String valor, String mensaje) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);
        if (!matcher.matches()) {
            throw new GeneralException(mensaje);
        }
    }

    @SneakyThrows
    public String getServiceToken(String user, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("username", user.isEmpty()?"admin":user);
        body.add("password", password.isEmpty()?"12345":password);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String jwtToken = null;

        ResponseEntity<String> response = restTemplate.exchange(apiAuthUrl, HttpMethod.POST, requestEntity, String.class);
        log.info("API response: {} - {}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            jwtToken = "Bearer ".concat(root.path("token").asText());
        }

        return jwtToken;
    }

}

package rest.api.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.api.example.entity.Role;
import rest.api.example.entity.User;
import rest.api.example.repository.UserRepository;
import rest.api.example.util.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public String register(RegisterRequest registerRequest){
        User user = User.builder().username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
    public String authenticate(RegisterRequest registerRequest) throws BadCredentialsException,Exception {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getUsername(),registerRequest.getPassword()));
            var user = userRepository.findByUsername(registerRequest.getUsername()).orElseThrow();
            return jwtService.generateToken(user);
    }
}

package rest.api.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.example.entity.User;
import rest.api.example.service.AuthService;
import rest.api.example.util.ApiResponse;
import rest.api.example.util.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody RegisterRequest registerRequest){
        try{
            String token = authService.authenticate(registerRequest);
            return ResponseEntity.ok(token);
        }
        catch(BadCredentialsException ex){
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}

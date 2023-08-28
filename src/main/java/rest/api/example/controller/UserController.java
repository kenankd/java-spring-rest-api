package rest.api.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.example.entity.User;
import rest.api.example.service.UserService;
import rest.api.example.util.ApiResponse;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/saveUsers")
    public List<User> saveUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if(user==null){
            ApiResponse<User> response = new ApiResponse<>(false, "User not found.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<User> response = new ApiResponse<>(true, "User found.", user);
        return ResponseEntity.ok(response);
    }

}

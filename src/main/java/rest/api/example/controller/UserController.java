package rest.api.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rest.api.example.entity.User;
import rest.api.example.service.UserService;
import rest.api.example.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/getUsers")
    //@PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ApiResponse<User>> getUserByUsername(@PathVariable String id){
        String a;
        User user = userService.findByUsername(id);
        if(user==null){
            ApiResponse<User> response = new ApiResponse<>(false, "User not found.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<User> response = new ApiResponse<>(true, "User found.", user);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable String id,@RequestBody User newUser){
        User user = userService.findByUsername(id);
        if(user==null){
            ApiResponse<User> response = new ApiResponse<>(false, "User not found.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            newUser.setId(user.getId());
            userService.saveUser(newUser);
            ApiResponse<User> response = new ApiResponse<>(true, "User updated.", newUser);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponse<User>(false,"Something went wrong, " + e.getMessage(),null));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        boolean success = userService.deleteUser(id);
        if(!success){
            ApiResponse<User> response = new ApiResponse<>(false, "User not found.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted user from database");
    }
}

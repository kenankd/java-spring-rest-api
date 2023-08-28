package rest.api.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rest.api.example.entity.User;
import rest.api.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return userRepository.saveAll(users);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return null;
        return user.get();
    }
    public ResponseEntity<User> deleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        try{
            userRepository.delete(user.get());
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}

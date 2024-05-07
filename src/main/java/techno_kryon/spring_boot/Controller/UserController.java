package techno_kryon.spring_boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techno_kryon.spring_boot.Entity.User;
import techno_kryon.spring_boot.Service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody String email) {
        userService.deleteUser(email);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @PostMapping("login")
    public ResponseEntity validateUser(@RequestBody User user) {
        if(userService.validateUser()){
            // send 200 ok
        }
        else {
            // send error
        }
    }

}

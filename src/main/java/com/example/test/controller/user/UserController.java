package com.example.test.controller.user;

import com.example.test.constants.user.Constants;
import com.example.test.exceptions.user.MerchantPhoneAlreadyExistsException;
import com.example.test.exceptions.user.UserConstraintException;
import com.example.test.model.user.User;
import com.example.test.repository.user.UserRepository;
import com.example.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> allUsers = userService.getAllUser();
            return ResponseEntity.ok().body(allUsers);
        }
        catch (Exception e) {
            System.out.println("Exception " + e + "occured for getting all users");
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@Validated @RequestBody User user) {
        try {
            userService.saveNewUser(user);
        } catch (UserConstraintException uce) {
            return (ResponseEntity<String>) ResponseEntity.badRequest().body(uce.getMessage());
        } catch (MerchantPhoneAlreadyExistsException mpaee) {
            return (ResponseEntity<String>) ResponseEntity.badRequest().body(mpaee.getMessage());
        }

        return (ResponseEntity<String>) ResponseEntity.ok(Constants.ResponseConstants.USER_CREATED);
    }
}

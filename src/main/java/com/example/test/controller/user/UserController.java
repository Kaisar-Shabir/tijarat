package com.example.test.controller.user;

import com.example.test.constants.user.Constants;
import com.example.test.enums.user.MerchantType;
import com.example.test.exceptions.user.MerchantPhoneAlreadyExistsException;
import com.example.test.exceptions.user.UserConstraintException;
import com.example.test.exceptions.user.UserNotFoundException;
import com.example.test.model.user.User;
import com.example.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

//    TODO: Remove after testing or introduce security access for end points
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

    @GetMapping("/{merchantType}/{phone}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "merchantType") MerchantType merchantType,
                                             @PathVariable(value = "phone") String phone) {
        User user;
        try {
            user = userService.getUser(phone, merchantType);
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
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

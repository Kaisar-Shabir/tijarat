package com.example.test.controller.user;

import com.example.test.dao.request.user.CreateUserRequestDao;
import com.example.test.dao.response.user.UserByIdResponseDao;
import com.example.test.dao.request.user.UserByMerchantTypeAndPhoneRequestDao;
import com.example.test.dao.response.user.CreateUserResponseDao;
import com.example.test.dao.response.user.UserMerchantTypeAndPhoneResponseDao;
import com.example.test.exceptions.user.MerchantPhoneAlreadyExistsException;
import com.example.test.exceptions.user.UserConstraintException;
import com.example.test.exceptions.user.UserEmailAlreadyExistsException;
import com.example.test.exceptions.user.UserNotFoundException;
import com.example.test.model.user.User;
import com.example.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.test.constants.user.Constants.ResponseConstants.EMAIL_UPDATED;

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

    @GetMapping("/getUserByMerchantTypeAndPhone")
    public ResponseEntity<UserMerchantTypeAndPhoneResponseDao> getUserByMerchantTypeAndPhone(
            @Validated @RequestBody UserByMerchantTypeAndPhoneRequestDao userMerchantTypeAndPhoneRequestDao) {
        UserMerchantTypeAndPhoneResponseDao userMerchantTypeAndPhoneResponseDao;
        try {
            userMerchantTypeAndPhoneResponseDao = userService.getUser(userMerchantTypeAndPhoneRequestDao);
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userMerchantTypeAndPhoneResponseDao);
    }

    @GetMapping("/getUserById/{Id}")
    public ResponseEntity<UserByIdResponseDao> getUserById(
            @PathVariable("Id") Long Id) {
        UserByIdResponseDao userByIdResponseDao = new UserByIdResponseDao();
        try {
            userByIdResponseDao = userService.getUser(Id);
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userByIdResponseDao);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDao> saveUser(
            @Validated @RequestBody CreateUserRequestDao createUserRequestDao) {
        CreateUserResponseDao createUserResponseDao = new CreateUserResponseDao();

        try {
            createUserResponseDao = userService.saveNewUser(createUserRequestDao);
        } catch (UserConstraintException uce) {
            return ResponseEntity.badRequest().build();
        } catch (MerchantPhoneAlreadyExistsException mpaee) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(createUserResponseDao);
    }

    @PostMapping("/updateEmail/{Id}")
    public ResponseEntity<String> updateUserEmail(
            @PathVariable("Id") Long Id,
            @Validated @RequestBody String email) {
        try {
            userService.updateUserEmail(Id, email);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (UserEmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(EMAIL_UPDATED);
    }
}

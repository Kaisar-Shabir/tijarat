package com.example.test.service.user;

import com.example.test.constants.user.Constants;
import com.example.test.enums.user.MerchantType;
import com.example.test.exceptions.user.MerchantPhoneAlreadyExistsException;
import com.example.test.exceptions.user.UserConstraintException;
import com.example.test.exceptions.user.UserNotFoundException;
import com.example.test.model.user.User;
import com.example.test.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.test.constants.user.Constants.ExceptionMessageConstants.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(String phone, MerchantType merchantType) throws UserNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByMobileNumberAndMerchantType(phone, merchantType));

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format(USER_WITH_MERCHANT_AND_PHONE_NOT_FOUNT_MESSAGE, merchantType.name(), phone));
        }

        return optionalUser.get();
    }

    public void saveNewUser(User user) throws MerchantPhoneAlreadyExistsException, UserConstraintException {
        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByMobileNumberAndMerchantType(user.getMobileNumber(), user.getMerchantType()));
        if(optionalUser.isPresent()) {
            throw new MerchantPhoneAlreadyExistsException(String.format(MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE,
                    user.getMerchantType().name(), user.getMobileNumber()));
        }

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException cve) {
            throw new UserConstraintException(String.format(USER_MODEL_CONSTRAINT_EXCEPTION_MESSAGE,
                    user, cve));
        }
    }
}

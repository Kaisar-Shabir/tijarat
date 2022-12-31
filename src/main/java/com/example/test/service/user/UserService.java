package com.example.test.service.user;

import com.example.test.constants.user.Constants;
import com.example.test.exceptions.user.MerchantPhoneAlreadyExistsException;
import com.example.test.exceptions.user.UserConstraintException;
import com.example.test.model.user.User;
import com.example.test.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public void saveNewUser(User user) throws MerchantPhoneAlreadyExistsException, UserConstraintException {
        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByMobileNumberAndMerchantType(user.getMobileNumber(), user.getMerchantType()));
        if(optionalUser.isPresent()) {
            throw new MerchantPhoneAlreadyExistsException(String.format(Constants.ExceptionMessageConstants.MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE,
                    user.getMerchantType().name(), user.getMobileNumber()));
        }

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException cve) {
            throw new UserConstraintException(String.format(Constants.ExceptionMessageConstants.USER_MODEL_CONSTRAINT_EXCEPTION_MESSAGE,
                    user, cve));
        }
    }
}

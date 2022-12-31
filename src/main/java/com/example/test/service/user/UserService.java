package com.example.test.service.user;

import com.example.test.dao.request.user.CreateUserRequestDao;
import com.example.test.dao.request.user.UserMerchantTypeAndPhoneRequestDao;
import com.example.test.dao.response.user.CreateUserResponseDao;
import com.example.test.dao.response.user.UserMerchantTypeAndPhoneResponseDao;
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

    public UserMerchantTypeAndPhoneResponseDao getUser(UserMerchantTypeAndPhoneRequestDao userMerchantTypeAndPhoneRequestDao)
            throws UserNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByMobileNumberAndMerchantType(
                        userMerchantTypeAndPhoneRequestDao.getPhone(),
                        userMerchantTypeAndPhoneRequestDao.getMerchantType()));

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format(USER_WITH_MERCHANT_AND_PHONE_NOT_FOUNT_MESSAGE,
                    userMerchantTypeAndPhoneRequestDao.getMerchantType().name(),
                    userMerchantTypeAndPhoneRequestDao.getPhone()));
        }

        User user = optionalUser.get();

        UserMerchantTypeAndPhoneResponseDao userMerchantTypeAndPhoneResponseDao = new UserMerchantTypeAndPhoneResponseDao();
        userMerchantTypeAndPhoneResponseDao.setUserId(user.getId());
        userMerchantTypeAndPhoneResponseDao.setFirstName(user.getFirstName());
        userMerchantTypeAndPhoneResponseDao.setEmail(user.getEmail());

        return userMerchantTypeAndPhoneResponseDao;
    }

    public CreateUserResponseDao saveNewUser(CreateUserRequestDao createUserRequestDao)
            throws MerchantPhoneAlreadyExistsException, UserConstraintException {
        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByMobileNumberAndMerchantType(createUserRequestDao.getMobileNumber(),
                        createUserRequestDao.getMerchantType()));
        if(optionalUser.isPresent()) {
            throw new MerchantPhoneAlreadyExistsException(String.format(MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE,
                    createUserRequestDao.getMerchantType().name(), createUserRequestDao.getMobileNumber()));
        }

        User user = new User();

        user.setFirstName(createUserRequestDao.getFirstName());
        user.setLastName(createUserRequestDao.getLastName());
        user.setMerchantType(createUserRequestDao.getMerchantType());
        user.setMobileNumber(createUserRequestDao.getMobileNumber());
        user.setEmail(createUserRequestDao.getEmail());

        CreateUserResponseDao createUserResponseDao = new CreateUserResponseDao();

        try {
            createUserResponseDao.setUserId(userRepository.save(user).getId());
        } catch (DataIntegrityViolationException cve) {
            throw new UserConstraintException(String.format(USER_MODEL_CONSTRAINT_EXCEPTION_MESSAGE,
                    user, cve));
        }

        return createUserResponseDao;
    }
}

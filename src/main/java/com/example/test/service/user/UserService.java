package com.example.test.service.user;

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

    public UserMerchantTypeAndPhoneResponseDao getUser(UserByMerchantTypeAndPhoneRequestDao userMerchantTypeAndPhoneRequestDao)
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

    public UserByIdResponseDao getUser(Long Id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(Id);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format(USER_WITH_ID_NOT_FOUNT_MESSAGE, Id));
        }

        User user = optionalUser.get();

        UserByIdResponseDao userByIdResponseDao = new UserByIdResponseDao();
        userByIdResponseDao.setFirstName(user.getFirstName());
        userByIdResponseDao.setPhone(user.getMobileNumber());
        userByIdResponseDao.setEmail(user.getEmail());
        userByIdResponseDao.setMerchantType(user.getMerchantType());

        return userByIdResponseDao;
    }

    public CreateUserResponseDao saveNewUser(CreateUserRequestDao createUserRequestDao)
            throws MerchantPhoneAlreadyExistsException, UserConstraintException {
        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByMobileNumberAndMerchantType(createUserRequestDao.getMobileNumber(),
                        createUserRequestDao.getMerchantType()));
        if(optionalUser.isPresent()) {
            throw new MerchantPhoneAlreadyExistsException(
                    String.format(MERCHANT_PHONE_ALREADY_EXIST_EXCEPTION_MESSAGE,
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

    public void updateUserEmail(Long userId, String email)
            throws UserNotFoundException, UserEmailAlreadyExistsException {
        Optional<User> optionalUser = Optional.of(userRepository.getReferenceById(userId));

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(
                    String.format(USER_WITH_ID_NOT_FOUNT_MESSAGE, userId));
        }

//        Optional<User> optionalUserWithEmail = Optional.ofNullable(userRepository.findByEmail(email));
//
//        if(optionalUserWithEmail.isPresent()) {
//            throw new UserEmailAlreadyExistsException(
//                    String.format(USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE, email));
//        }

        try {
            User user = optionalUser.get();
            user.setEmail(email);
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserEmailAlreadyExistsException(
                    String.format(USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE, email));
        }

    }
}

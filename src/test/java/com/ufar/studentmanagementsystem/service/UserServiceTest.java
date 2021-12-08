package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.exception.AlreadyExistsException;
import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.service.impl.UserServiceImpl;
import com.ufar.studentmanagementsystem.service.validation.UserValidation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private User updateUser;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<>();
        user = new User(1, "user", "user");
        updateUser = new User(1, "updated_user", "updated_user");
        userList.add(user);
    }

    @AfterEach
    public void tearDown() {
        user = null;
        userList = null;
    }

    @Test
    @DisplayName("addUser Return Added Valid User")
    public void addUser_return_added_valid_user() {
        when(userRepository.add(any())).thenReturn(user);
        userService.addUser(user);
        verify(userRepository).add(any());
    }

    @Test
    @DisplayName("addUser When User Is Null")
    public void addUser_when_user_is_null() {
        Assertions.assertFalse(UserValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.addUser(null));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("addUser When User's Username Is Empty")
    public void addUser_when_user_username_is_empty() {
        User emptyUsernameUser = new User("", "notEmpty");

        Assertions.assertFalse(UserValidation.isValid(emptyUsernameUser));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.addUser(emptyUsernameUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("addUser When User's Password Is Empty")
    public void addUser_when_user_password_is_empty() {
        User emptyPasswordUser = new User("notEmpty", "");

        Assertions.assertFalse(UserValidation.isValid(emptyPasswordUser));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.addUser(emptyPasswordUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("addUser When User Already Exists")
    public void addUser_when_user_already_exists() {
        when(userRepository.findByUsername("user")).thenReturn(Optional.ofNullable(user));
        AlreadyExistsException alreadyExistsException = Assertions.assertThrows(AlreadyExistsException.class,
                () -> userService.addUser(user));
        Assertions.assertEquals(alreadyExistsException.getMessage(), "The user already exists with username " + user.getUserName());
    }

    @Test
    @DisplayName("findUsers Return List Of All Users")
    public void findUsers_return_list_of_all_users() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> productList = userService.findUsers();
        Assertions.assertEquals(productList, userList);
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("findUserById Return User Of That Valid Id")
    public void findUserById_return_user_of_that_valid_id() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        Assertions.assertEquals(userService.findUserById(user.getId()), user);
    }

    @Test
    @DisplayName("findUserById Throws NotFoundException As User Is Not Found")
    public void findUserById_throws_notfoundexception_as_user_is_not_found() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> userService.findUserById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The user with id 2 is not found");
    }

    @Test
    @DisplayName("updateUser Return Updated Valid User")
    public void updateUser_return_updated_valid_user() {
        when(userRepository.update(any())).thenReturn(Optional.ofNullable(updateUser));
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        userService.updateUser(user);
        verify(userRepository).update(any());
    }

    @Test
    @DisplayName("updateUser When User Is Null")
    public void updateUser_when_user_is_null() {
        Assertions.assertFalse(UserValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.updateUser(null));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("updateUser When User's Username Is Empty")
    public void updateUser_when_user_username_is_empty() {
        User emptyUsernameUser = new User(1, "", "notEmpty");

        Assertions.assertFalse(UserValidation.isValid(emptyUsernameUser));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.updateUser(emptyUsernameUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("updateUser When User's Password Is Empty")
    public void updateUser_when_user_password_is_empty() {
        User emptyPasswordUser = new User(1, "notEmpty", "");

        Assertions.assertFalse(UserValidation.isValid(emptyPasswordUser));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.updateUser(emptyPasswordUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("updateUser When User's Id Is Null")
    public void updateUser_when_user_id_id_null() {
        User nullIdUser = new User(null, "notEmpty", "notEmpty");

        Assertions.assertNull(nullIdUser.getId());
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.updateUser(nullIdUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("updateUser When User's Id Is Non positive")
    public void updateUser_when_user_password_is_non_positive() {
        User nonPositiveIdUser = new User(-1, "notEmpty", "");

        Assertions.assertTrue(nonPositiveIdUser.getId() <= 0);
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> userService.updateUser(nonPositiveIdUser));
        Assertions.assertEquals(notValidException.getMessage(), "The user is not valid");
    }

    @Test
    @DisplayName("updateUser Throws NotFoundException As User Is Not Found")
    public void updateUser_throws_notfoundexception_as_user_is_not_found() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> userService.deleteUserById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The user with id 2 is not found");
    }

    @Test
    @DisplayName("deleteUserById Throws NotFoundException As User Is Not Found")
    public void deleteUserById_throws_notfoundexception_as_user_is_not_found() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> userService.deleteUserById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The user with id 2 is not found");
    }
}

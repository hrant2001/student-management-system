package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.exception.AlreadyExistsException;
import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.service.impl.UniversityServiceImpl;
import com.ufar.studentmanagementsystem.service.validation.UniversityValidation;
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
public class UniversityServiceTest {

    @Mock
    private UniversityRepository universityRepository;


    @InjectMocks
    private UniversityServiceImpl universityService;

    private University university;
    private University updateUniversity;
    private List<University> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        university = new University(1,"UFAR", "Davit Anhaxt", 1);
        updateUniversity = new University ("French University in Armenia", "Davit Anhaxt 10", 2);
        list.add(university);
    }

    @AfterEach
    public void tearDown() {
        university = null;
        list = null;
    }

    @Test
    @DisplayName("addUniversity Return Added Valid University")
    public void addUniversity_return_added_valid_university() {
        when(universityRepository.add(any())).thenReturn(university);
        universityService.addUniversity(university);
        verify(universityRepository).add(any());
    }

    @Test
    @DisplayName("addUniversity When University Is Null")
    public void addUniversity_when_university_is_null() {
        Assertions.assertFalse(UniversityValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.addUniversity(null));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("addUniversity When University's Username Is Empty")
    public void addUniversity_when_university_universityname_is_empty() {
        University emptyUsernameUniversity = new University("", "Baghramyan", 1);

        Assertions.assertFalse(UniversityValidation.isValid(emptyUsernameUniversity));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.addUniversity(emptyUsernameUniversity));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("addUser When University's Password Is Empty")
    public void addUniversity_when_university_location_is_empty() {
        University emptyPasswordUniversity = new University("Agrarain", "", 1);

        Assertions.assertFalse(UniversityValidation.isValid(emptyPasswordUniversity));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.addUniversity(emptyPasswordUniversity));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }



    @Test
    @DisplayName("findUniversities Return List Of All Universities")
    public void findUniversities_return_list_of_all_university() {
        when(universityRepository.findAll()).thenReturn(list);
        List<University> productList = universityService.findUniversities();
        Assertions.assertEquals(productList, list);
        verify(universityRepository).findAll();
    }

    @Test
    @DisplayName("findUniversityById Return University Of That Valid Id")
    public void findUniversityById_return_university_of_that_valid_id() {
        when(universityRepository.findById(1)).thenReturn(Optional.ofNullable(university));
        Assertions.assertEquals(universityService.findUniversityById(university.getId()), university);
    }

    @Test
    @DisplayName("findUniversityById Throws NotFoundException As University Is Not Found")
    public void findUserById_throws_notfoundexception_as_university_is_not_found() {
        when(universityRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> universityService.findUniversityById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The university with id 2 is not found");
    }

    @Test
    @DisplayName("updateUniversity Return Updated Valid University")
    public void updateUniversity_return_updated_valid_university() {
        when(universityRepository.update(any())).thenReturn(Optional.ofNullable(updateUniversity));
        when(universityRepository.findById(university.getId())).thenReturn(Optional.ofNullable(university));
        universityService.updateUniversity(university);
        verify(universityRepository).update(any());
    }

    @Test
    @DisplayName("updateUniversity When University Is Null")
    public void updateUser_when_university_is_null() {
        Assertions.assertFalse(UniversityValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.updateUniversity(null));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("updateUniversity When University's Username Is Empty")
    public void updateUniversity_when_university_username_is_empty() {
        University emptyUsernameUniversity = new University(1,"", "New York", 1);

        Assertions.assertFalse(UniversityValidation.isValid(emptyUsernameUniversity));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.updateUniversity(emptyUsernameUniversity));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("updateUniversity When University's location Is Empty")
    public void updateUniversity_when_university_location_is_empty() {
        University emptyPasswordUniversity = new University(1,"Not empty", "", 1);

        Assertions.assertFalse(UniversityValidation.isValid(emptyPasswordUniversity));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.updateUniversity(emptyPasswordUniversity));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("updateUniversity When University's Id Is Null")
    public void updateUniversity_when_university_id_id_null() {
        University nullIdUser = new University(null,"not emtpy", "notEmpty", 1);

        Assertions.assertNull(nullIdUser.getId());
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.updateUniversity(nullIdUser));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("updateUniversity When University's Id Is Non positive")
    public void updateUniversity_when_university_password_is_non_positive() {
        University nonPositiveIdUniversity = new University(-1,"Not empty", "notEmpty", 1);

        Assertions.assertTrue(nonPositiveIdUniversity.getId() <= 0);
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> universityService.updateUniversity(nonPositiveIdUniversity));
        Assertions.assertEquals(notValidException.getMessage(), "The university is not valid");
    }

    @Test
    @DisplayName("updateUser Throws NotFoundException As User Is Not Found")
    public void updateUser_throws_notfoundexception_as_user_is_not_found() {
        when(universityRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> universityService.deleteUniversityById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The University with id 2 is not found");
    }

    @Test
    @DisplayName("deleteUniversityById Throws NotFoundException As University Is Not Found")
    public void deleteUniversityById_throws_notfoundexception_as_university_is_not_found() {
        when(universityRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> universityService.deleteUniversityById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The University with id 2 is not found");
    }
}

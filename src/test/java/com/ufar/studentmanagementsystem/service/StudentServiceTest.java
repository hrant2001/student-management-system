package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import com.ufar.studentmanagementsystem.service.impl.StudentServiceImpl;
import com.ufar.studentmanagementsystem.service.validation.StudentValidation;
import com.ufar.studentmanagementsystem.service.validation.UserValidation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private Student updateStudent;
    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        student = new Student(1, 1, 1, "Hrant", "Arakelyan ", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");
        updateStudent = new Student(2, 2, 2, "Sara", "Kostandyan", LocalDate.parse("2001-02-27"), "IMA", 4, "Bachelor's degree");
        studentList.add(student);
    }

    @AfterEach
    public void tearDown() {
        student = null;
        studentList = null;
    }

    @Test
    @DisplayName("addUser Return Added Valid Student")
    public void addStudent_return_added_valid_student() {
        when(studentRepository.add(any())).thenReturn(student);
        studentService.addStudent(student);
        verify(studentRepository).add(any());
    }

    @Test
    @DisplayName("addStudent When Student Is Null")
    public void addStudent_when_student_is_null() {
        Assertions.assertFalse(StudentValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(null));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }

    @Test
    @DisplayName("addStudent When Student's name Is Empty")
    public void addStudent_when_student_name_is_empty() {
        Student emptyStudentnameStudent = new Student(1, 1, "", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");

        Assertions.assertFalse(StudentValidation.isValid(emptyStudentnameStudent));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(emptyStudentnameStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }

    @Test
    @DisplayName("addStudent When Student's surname Is Empty")
    public void addStudent_when_student_surname_is_empty() {
        Student emptySurnameStudent = new Student(1, 1, "Albina ", "", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");

        Assertions.assertFalse(StudentValidation.isValid(emptySurnameStudent));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(emptySurnameStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }

    @Test
    @DisplayName("addStudent When Student's birthdate Is Empty")
    public void addStudent_when_student_birthdate_is_null() {
        Student emptyBirthdateStudent = new Student(1, 1, "Albina ", "Hakobyan", null, "IMA", 4, "Bachelor's degree");

        Assertions.assertFalse(StudentValidation.isValid(emptyBirthdateStudent));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(emptyBirthdateStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }


    @Test
    @DisplayName("addStudent When Student's year Is Empty")
    public void addStudent_when_student_year_is_negative() {
        Student emptyFacultyStudent = new Student(1, 1, "Albina ", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", -1, "Bachelor's degree");

        Assertions.assertFalse(StudentValidation.isValid(emptyFacultyStudent));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(emptyFacultyStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }


    @Test
    @DisplayName("addStudent When Student's degree Is Empty")
    public void addStudent_when_student_degree_is_empty() {
        Student emptyBirthdateStudent = new Student(1, 1, "Albina ", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", 4, "");

        Assertions.assertFalse(StudentValidation.isValid(emptyBirthdateStudent));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.addStudent(emptyBirthdateStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }


    @Test
    @DisplayName("findStudents Return List Of All Students")
    public void findStudents_return_list_of_all_students() {
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> productList = studentService.findStudents();
        Assertions.assertEquals(productList, studentList);
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("findStudentById Return Student Of That Valid Id")
    public void findStudentById_return_student_of_that_valid_id() {
        when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));
        Assertions.assertEquals(studentService.findStudentById(student.getId()), student);
    }

    @Test
    @DisplayName("findStudentById Throws NotFoundException As Student Is Not Found")
    public void findStudentById_throws_notfoundexception_as_student_is_not_found() {
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> studentService.findStudentById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The student with id 2 is not found");
    }

    @Test
    @DisplayName("updateStudent Return Updated Valid Student")
    public void updateStudent_return_updated_valid_student() {
        when(studentRepository.update(any())).thenReturn(Optional.ofNullable(updateStudent));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.ofNullable(student));
        studentService.updateStudent(student);
        verify(studentRepository).update(any());
    }

    @Test
    @DisplayName("updateStudent When Student Is Null")
    public void updateUser_when_user_is_null() {
        Assertions.assertFalse(UserValidation.isValid(null));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.updateStudent(null));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }


    @Test
    @DisplayName("updateStudent When Student's name Is Empty")
    public void updateStudent_when_student_name_is_empty() {
        Student emptyStudentName = new Student(1, 1, "", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");

        Assertions.assertFalse(StudentValidation.isValid(emptyStudentName));
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.updateStudent(emptyStudentName));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }


    @Test
    @DisplayName("updateStudent When Student's Id Is Null")
    public void updateStudent_when_student_id_is_null() {
        Student nullIdStudent = new Student(null, 1, 1, "Albina", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");

        Assertions.assertNull(nullIdStudent.getId());
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.updateStudent(nullIdStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }

    @Test
    @DisplayName("updateStudent When Student's Id Is Non positive")
    public void updateStudent_when_student_password_is_non_positive() {
        Student nonPositiveIdStudent = new Student(-2, 1, 1, "Albina", "Hakobyan", LocalDate.parse("2001-12-03"), "IMA", 4, "Bachelor's degree");

        Assertions.assertTrue(nonPositiveIdStudent.getId() <= 0);
        NotValidException notValidException = Assertions.assertThrows(NotValidException.class, () -> studentService.updateStudent(nonPositiveIdStudent));
        Assertions.assertEquals(notValidException.getMessage(), "The student is not valid");
    }

    @Test
    @DisplayName("updateUser Throws NotFoundException As User Is Not Found")
    public void updateStudent_throws_notfoundexception_as_student_is_not_found() {
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> studentService.deleteStudentById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The student with id 2 is not found");
    }

    @Test
    @DisplayName("deleteStudentById Throws NotFoundException As Student Is Not Found")
    public void deleteStudentById_throws_notfoundexception_as_student_is_not_found() {
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> studentService.deleteStudentById(2));
        Assertions.assertEquals(notFoundException.getMessage(), "The student with id 2 is not found");
    }
}

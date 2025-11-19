package com.example.Continuous.Integration;

import com.example.Continuous.Integration.model.Student;
import com.example.Continuous.Integration.repository.StudentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class ControllerTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	@Order(1)
	void shouldSaveStudent() {
		Student student = new Student();
		student.setName("Charlie");
		student.setAddress("Algeria");
		studentRepository.save(student);
		assertThat(studentRepository.count()).isEqualTo(1);
	}

	@Test
	@Order(2)
	void shouldFindAllStudents() {
		List<Student> students = studentRepository.findAll();
		assertThat(students).hasSize(1);
		assertThat(students.get(0).getName()).isEqualTo("Charlie");
	}
}

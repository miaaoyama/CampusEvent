package service;

import java.util.ArrayList;
import model.Student;

/**
 * Stores students while the program is running and handles student operations.
 */
public class StudentManager {
	private ArrayList<Student> students;

	public StudentManager() {
		students = new ArrayList<>();
	}

	/**
	 * Adds a student if their ID is not already in the list.
	 * Returns true if added, or false for a null student or duplicate ID.
	 */
	public boolean addStudent(Student student) {
		if (student == null || findStudentById(student.getStudentId()) != null) {
			return false;
		}

		students.add(student);
		return true;
	}

	/**
	 * Returns the student with the given ID, or null if none exists.
	 * Other parts of the program can use this to get the same Student object.
	 */
	public Student findStudentById(int id) {
		for (Student student : students) {
			if (student.getStudentId() == id) {
				return student;
			}
		}
		return null;
	}

	/** Displays one student, or a message if the ID is not found. */
	public void displayStudent(int id) {
		Student student = findStudentById(id);

		if (student == null) {
			System.out.println("Student not found.");
		} else {
			System.out.println(student);
		}
	}

	/** Displays every student, or a message if the list is empty. */
	public void displayAllStudents() {
		if (students.isEmpty()) {
			System.out.println("No students found.");
			return;
		}

		for (Student student : students) {
			System.out.println(student);
		}
	}
}
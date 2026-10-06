package model;

public class Student {
	private int studentId;
	private String name;
	private String email;
	private String major;

	public Student(int studentId, String name, String email, String major) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.major = major;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMajor() {
		return major;
	}

	@Override
	public String toString() {
		return "ID: " + studentId
				+ ", Name: " + name
				+ ", Email: " + email
				+ ", Major: " + major;
	}
}
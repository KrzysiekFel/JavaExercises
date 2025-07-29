package org.coding.exercise1;

import java.util.*;

public class UniversityService {
    private final List<Student> students = new ArrayList<>();
    private final List<Professor> professors = new ArrayList<>();
    private final Map<Professor, List<Student>> professorToStudents = new HashMap<>();

    public List<Student> getAllStudents() {
        return this.students;
    }

    public List<Professor> getAllProfessors() {
        return this.professors;
    }

    public void addStudentToUniversity(Student student) {
        this.students.add(student);
    }

    public void addProfessorToUniversity(Professor professor) {
        this.professors.add(professor);
        this.professorToStudents.put(professor, new ArrayList<>());
    }

    public void addStudentToProfessor(Professor professor, Student student) {
        this.professorToStudents.get(professor).add(student);
    }

    public List<Student> getAllStudentsForProfessor(Professor professor) {
        return this.professorToStudents.get(professor);
    }

    public void removeStudentFromUniversity(Student student) {
        this.students.remove(student);
        this.removeStudentFromProfessorsClasses(student);
    }

    public void removeStudentFromProfessorsClasses(Student student) {
        for (List<Student> students : this.professorToStudents.values()) {
            if (students.remove(student)) {
                break;
            }
        }
    }

    public List<Professor> getProfessorsWhoTeachMoreThanXSubjects(int subjectsCount) {
        List<Professor> resultProfessors = new ArrayList<>();
        for (Professor professor : this.professors) {
            if (professor.getSubjects().size() > subjectsCount) {
                resultProfessors.add(professor);
            }
        }
        return resultProfessors;
    }

    public Set<Student> getStudentsThatHaveMoreThanXCommonSubjectsWithGivenStudent(int subjectsCount, Student givenStudent) {
        Set<Student> resultStudents = new HashSet<>();
        for (Student student : this.students) {
            if (student == givenStudent) {
                continue;
            }
            Set<Subject> subjectsStudent = new HashSet<>(student.getSubjects());
            Set<Subject> commonSubjects = new HashSet<>(subjectsStudent);
            commonSubjects.retainAll(givenStudent.getSubjects());
            if (commonSubjects.size() > subjectsCount) {
                resultStudents.add(student);
            }
        }
        return resultStudents;
    }
}
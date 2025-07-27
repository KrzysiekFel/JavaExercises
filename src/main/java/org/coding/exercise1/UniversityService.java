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

    public List<Professor> professorsHwoTeachMoreThanXSubjects(int subjectsCount) {
        List<Professor> resultProfessors = new ArrayList<>();
        for (Professor professor : this.professors) {
            if (professor.getSubjects().size() > subjectsCount) {
                resultProfessors.add(professor);
            }
        }
        return resultProfessors;
    }

    public Set<Student> studentsHavingMoreTHanXCommonSubject(int subjectsCount) {
//uznalem ze ze taki uczen to ten ktory ma ze wszystkimi innymi takie same wspolne przedmioty
//bo mozna bylo zrobic tak ze ma wspolne z każdym ale te wspolne sa różne
//        Set<Student> resultStudents = new HashSet<>();
//
//        for (int i = 0; i < this.students.size() - 1; i++) {
//            Student student1 = this.students.get(i);
//            Set<Subject> subjectsStudent1 = new HashSet<>(student1.getSubjects());
//            Set<Subject> commonSubjects = new HashSet<>(subjectsStudent1);
//            for (int j = i + 1; j < this.students.size(); j++) {
//                Student student2 = this.students.get(j);
//                Set<Subject> subjectsStudent2 = new HashSet<>(student2.getSubjects());
//                commonSubjects.retainAll(subjectsStudent2);
//            }
//            if (commonSubjects.size() > subjectsCount) {
//                resultStudents.add(student1);
//                if (i == this.students.size() - 2) {
//                    resultStudents.add(this.students.get(i + 1));
//                }
//            }
//        }
//        return resultStudents;

        // a gdyby chodzilo ze liczy sie student ktory ze wwszystkimi (kazdym z osobna) ma jakiekolwiek wspolne to:
        Set<Student> resultStudents = new HashSet<>();
        for (Student student1 : this.students) {
            boolean hasEnoughCommonWithAll = true;
            Set<Subject> subjectsStudent1 = new HashSet<>(student1.getSubjects());
            Set<Subject> commonSubjects = new HashSet<>(subjectsStudent1);
            for (Student student2 : this.students) {
                if (student1 == student2) {
                    continue;
                }
                Set<Subject> subjectsStudent2 = new HashSet<>(student2.getSubjects());
                commonSubjects.retainAll(subjectsStudent2);
                if (commonSubjects.size() <= subjectsCount) {
                    hasEnoughCommonWithAll = false;
                    break;
                }
            }
            if (hasEnoughCommonWithAll) {
                resultStudents.add(student1);
            }
        }
        return resultStudents;


    }
}

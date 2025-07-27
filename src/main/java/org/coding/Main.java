package org.coding;

import org.coding.exercise1.Professor;
import org.coding.exercise1.Student;
import org.coding.exercise1.Subject;
import org.coding.exercise1.UniversityService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Subject> subjects = new ArrayList<>();
        UniversityService universityService = new UniversityService();
        subjects.add(new Subject("Math", 1));
        subjects.add(new Subject("Phisics", 2));

        Student student1 = new Student("Krzysiek", "Feliksiak", "90010199937", subjects, 3);
        Student student2 = new Student("Krisu", "Felix", "91010199937", subjects, 2);
        Professor professor = new Professor("Albert", "Einstein", "79031412357", subjects, 2);

        universityService.addProfessorToUniversity(professor);
        universityService.addStudentToUniversity(student1);
        universityService.addStudentToUniversity(student2);
        universityService.addStudentToProfessor(professor, student1);
        universityService.addStudentToProfessor(professor, student2);

        System.out.println(student1.greet());
        System.out.println(professor.greet());
        System.out.println(universityService.getAllStudentsForProfessor(professor));
        System.out.println(universityService.professorsHwoTeachMoreThanXSubjects(1));
        System.out.println(universityService.studentsHavingMoreTHanXCommonSubject(1));
    }
}




/*
Stwórz program do zarządzania uczelnią
Studenci, Profesorowie
1. Chce mieć możliwość dodania studentów z konsoli np.
- Podaj imie i nazwisko:
- Jan Kowalski
- Podaj PESEL:
- 00000123 (walidacja), na podstawie peselu ustawiaj wiek
- Podaj Typ (Student,Profesor)
- Podaj np. Przedmioty których się uczy/Przedmioty których naucza
- dla profesora: staż pracy / a dla studenta: rok studiów
3. Wypisz:
- Wszystkich uczniów którzy mają więcej niż 2 wspólne przedmioty
- Profesorów, którzy uczą więcej niż 1 przedmiotu
- Wszystkich uczniów, których uczy dany profesor
 */
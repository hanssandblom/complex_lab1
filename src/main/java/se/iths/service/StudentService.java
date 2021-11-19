package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    public Student deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        if (foundStudent == null) {
            return null;
        }
        entityManager.remove(foundStudent);
        return foundStudent;
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findStudentByLastName(String lastName) {
        return entityManager.createQuery("SELECT i from Student i WHERE i.lastName LIKE :lastName", Student.class).setParameter("lastName", lastName).getResultList();
    }

    public Student updateFirstName(Long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setFirstName(name);
        return entityManager.merge(foundStudent);
    }

    public Student updateLastName(Long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(name);
        return entityManager.merge(foundStudent);
    }

    public Student updateEmail(Long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(name);
        return entityManager.merge(foundStudent);
    }

    public Student updatePhoneNumber(Long id, String name) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(name);
        return entityManager.merge(foundStudent);
    }

}

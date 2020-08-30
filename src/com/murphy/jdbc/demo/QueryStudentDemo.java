package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("FROM Student").getResultList();

            // display the students
            displayTheStudents(theStudents);

            // query students: lastName='Hsieh'
            // using Java property name
            theStudents = session.createQuery("FROM Student s WHERE s.lastName='Hsieh'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Hsieh");
            displayTheStudents(theStudents);

            //query students: lastName='Hsieh' OR firstName='Frank'
            theStudents = session.createQuery("FROM Student s WHERE " +
                                                "s.lastName='Hsieh' OR " +
                                                "s.firstName='Frank'").getResultList();
            System.out.println("\n\nStudents who have last name of Hsieh OR first name of Frank");
            displayTheStudents(theStudents);

            // query students where email LIKE '%luv2code.com'
            theStudents = session.createQuery("FROM Student s WHERE s.email LIKE '%luv2code.com'").getResultList();

            System.out.println("\n\nStudents whose email end with luv2code.com");
            displayTheStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }

    private static void displayTheStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}

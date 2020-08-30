package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 3;

            // now get a new session and start transaction
            session =  factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Deleting student: " + myStudent);
            session.delete(myStudent);


            System.out.println("Deleting student id=4");
            session.createQuery("DELETE FROM Student WHERE id=4")
                    .executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }
}

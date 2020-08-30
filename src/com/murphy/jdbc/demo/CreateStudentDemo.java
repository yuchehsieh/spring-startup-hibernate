package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating new student object...");
            String theDateOfBirthStr = "05/06/1997";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            Student tempStudent = new Student("Murphy", "Wall", "murphy@luv2code.com", theDateOfBirth);

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }
}

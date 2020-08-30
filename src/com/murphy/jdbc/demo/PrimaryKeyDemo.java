package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create 3 student object
            System.out.println("Creating 3 student object...");
            Student tempStudent1 = new Student("Murphy", "Wall", "murphy@luv2code.com");
            Student tempStudent2 = new Student("Sarah", "Hsieh", "sarah@luv2code.com");
            Student tempStudent3 = new Student("Ruby", "Lin", "ruby@luv2code.com");

            // start a transaction
            session.beginTransaction();

            // save 3 student object
            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

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

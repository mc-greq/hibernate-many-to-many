package example.nio;

import example.nio.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseFromStudent {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory;session){
            session.beginTransaction();

            // get the student from the database
            int id = 10;
            Course course = session.get(Course.class, id);

            // delete the course
            System.out.println("Deleting the course ...");
            session.delete(course);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}

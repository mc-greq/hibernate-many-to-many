package example.nio;

import example.nio.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

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
            int id = 2;
            Student student = session.get(Student.class, id);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses: " + student.getCourses());

            // delete the student
            session.delete(student);

            // commit
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}

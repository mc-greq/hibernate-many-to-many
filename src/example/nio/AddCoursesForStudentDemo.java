package example.nio;

import example.nio.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForStudentDemo {

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

            // create courses
            Course course1 = new Course("Course in linear algebra");
            Course course2 = new Course("Copy-paste from Stack Overflow");

            // add courses to the student
            course1.addStudent(student);
            course2.addStudent(student);

            // save the student
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}

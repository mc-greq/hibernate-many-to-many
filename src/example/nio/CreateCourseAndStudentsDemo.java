package example.nio;

import example.nio.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) {
	// write your code here
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

            Course tempCourse = new Course("Pacman - How to Score one Million Points");

            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved course: " + tempCourse);

            Student student1 = new Student("Grzegorz", "Rutkowski", "greg@gmail.com");
            Student student2 = new Student("Marta", "Florczak", "marta@gmail.com");

            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            System.out.println("\nSaving students ...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saving students: " + tempCourse.getStudents());

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}

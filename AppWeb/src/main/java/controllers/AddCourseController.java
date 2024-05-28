package controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Course;
import entities.Student;
import entities.Teacher;

@WebServlet("/addCourse")
public class AddCourseController extends HttpServlet {

    /** L'entity manager */
    @PersistenceContext(unitName = "MaPu")
    public static EntityManager em;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Récupérer les paramètres de la requête
            String name = request.getParameter("name");
            String students = request.getParameter("students");
            String teacherString = request.getParameter("teacher");
            String dateString = request.getParameter("date");
            String startTimeString = request.getParameter("startTime");
            String endTimeString = request.getParameter("endTime");

            // Réorganiser les élèves
            List<Student> studentList = traiterStudent(students);
            // Récupérer l'enseignant
            Teacher teacher = traiterTeacher(teacherString);
            // Transformer la date en LocalDate
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, dateFormatter);

            // Transformer l'heure de début et de fin en LocalTime
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime = LocalTime.parse(startTimeString, timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeString, timeFormatter);

            // Créer un nouveau cours
            Course course = new Course(name, studentList, teacher, date, startTime, endTime);

            // Commencer la transaction
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();

            // Rediriger vers la page d'accueil
            response.sendRedirect("/home.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public List<Student> traiterStudent(String students) {
        // Transformer la liste d'élèves en tableau
        String[] studentUsernames = students.split(",");
        List<Student> studentList = new ArrayList<>();

        for (String username : studentUsernames) {
            Student student = AddCourseController.em.createQuery("SELECT s FROM Student s WHERE s.username = :username", Student.class)
                                .setParameter("username", username.trim())
                                .getSingleResult();
            if (student != null) {
                studentList.add(student);
            }
        }
        return studentList;
    }

    public Teacher traiterTeacher(String teacherString) {
        // Récupérer l'enseignant par nom d'utilisateur
        return AddCourseController.em.createQuery("SELECT t FROM Teacher t WHERE t.username = :username", Teacher.class)
                 .setParameter("username", teacherString.trim())
                 .getSingleResult();
    }
}

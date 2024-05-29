
package controllers;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.*;

@WebServlet("/login")
public class loginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Récupérer les paramètres de la requête
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Vérifier les informations d'identification
            String role = isValidCredentials(username, password);
            if (role != null) {
                // Si c'est un etudiant en renvoie vers la page edt avec les cours d'aujourd'hui
                if (role.equals("student")) {
                response.sendRedirect("/edt?date=" + java.time.LocalDate.now());
                }
                // Si c'est un professeur en renvoie vers la page addCourse
                if (role.equals("teacher")) {
                    response.sendRedirect("/addCourse");
                }
            } else {
                // Si les informations d'identification sont incorrectes, renvoyer un message d'erreur
                response.getWriter().println("Identifiants incorrects");
            }
        } catch (Exception e) {
            // Ecrire une message d'erreur sur html
            response.getWriter().println("Erreur : " + e.getMessage());
        }
    }

    private String isValidCredentials(String username, String password) {
        // Rechercher l'utilisateur dans la base de données par nom d'utilisateur
        Student student = AddCourseController.em.createQuery("SELECT u FROM User u WHERE u.username = :username", Student.class)
                      .setParameter("username", username)
                      .getSingleResult();
        if (student != null) {
            // Vérifier si l'utilisateur existe et si le mot de passe correspond
            if (student.getPassword().equals(password)) {return "student";}
        }

        Teacher teacher = AddCourseController.em.createQuery("SELECT u FROM User u WHERE u.username = :username", Teacher.class)
                      .setParameter("username", username)
                      .getSingleResult();
        if (teacher != null) {
            // Vérifier si l'utilisateur existe et si le mot de passe correspond
            if (teacher.getPassword().equals(password)) {return "teacher";}
        }
        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        return null;
    }
}

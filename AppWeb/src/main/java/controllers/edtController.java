package controllers;

import entities.Course;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edt")
public class edtController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la date depuis les paramètres de la requête
        String dateString = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);

        try {
            // Créer la requête pour récupérer les cours pour cette date
            Query query = AddCourseController.em.createQuery("SELECT c FROM Course c WHERE c.date = :date");
            query.setParameter("date", date);

            // Exécuter la requête pour récupérer les cours
            @SuppressWarnings("unchecked")
            List<Course> courses = query.getResultList();

            // Ajouter la liste des cours à l'objet request
            request.setAttribute("courses", courses);

            // Transférer le contrôle à votre fichier JSP
            request.getRequestDispatcher("/edt.jsp").forward(request, response);
        } catch (Exception e) {
            // Gérer les erreurs
            e.printStackTrace();
            response.getWriter().println("Erreur : " + e.getMessage());
        }
    }
}

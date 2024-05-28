<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Emploi du temps</title>
</head>
<body>
    <h1>Emploi du temps</h1>
    <form method="get" action="/edt">
        <label for="date">Sélectionner une date :</label>
        <input type="date" id="date" name="date">
        <button type="submit">Afficher</button>
    </form>
    
    <% if (request.getAttribute("courses") != null) { %>
        <table>
            <tr>
                <th>Nom</th>
                <th>Date</th>
                <th>Heure de début</th>
                <th>Heure de fin</th>
                <th>Lieu</th>
            </tr>
            <% for (Course course : (List<Course>) request.getAttribute("courses")) { %>
                <tr>
                    <td><%= course.getName() %></td>
                    <td><%= course.getDate() %></td>
                    <td><%= course.getStartTime() %></td>
                    <td><%= course.getEndTime() %></td>
                    <td><%= course.getLieu() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>

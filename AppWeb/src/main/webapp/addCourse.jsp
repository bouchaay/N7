<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Course</title>
</head>
<body>
    <div>
        <h1>Add Course</h1>
        <form action="addCourse" method="post">
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter the course name" required>
            </div>
            <div>
                <label for="students">Students:</label>
                <input type="text" id="students" name="students" placeholder="student_user1, student_user2, ..." required>
            </div>
            <div>
                <label for="teacher">Teacher:</label>
                <input type="text" id="teacher" name="teacher" placeholder="teacher_user" required>
            </div>
            <div>
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" placeholder="yyyy-MM-dd" required>
            </div>
            <div>
                <label for="startTime">Start Time:</label>
                <input type="time" id="startTime" name="startTime" placeholder="HH:mm" required>
            </div>
            <div>
                <label for="endTime">End Time:</label>
                <input type="time" id="endTime" name="endTime" required>
            </div>
            <div>
                <button type="submit">Add Course</button>
            </div>
        </form>
    </div>

</body>
</html>
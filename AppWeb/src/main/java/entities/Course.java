package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {

    /** L'id du cours */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Le nom du cours */
    private String name;
    /** Les élèves du cours */
    @OneToMany(mappedBy = "courseStudents")
    private List<Student> students;
    /** Le professeur du cours */
    @ManyToOne
    private Teacher teacher;
    /** Date du cours */
    private LocalDate date;
    /** Heure du début du cours */
    private LocalTime startTime;
    /** Heure de fin du cours */
    private LocalTime endTime;

    /** Constructeur par défaut */
    public Course() {
    }

    /** Constructeur avec paramètres
     * @param name Le nom du cours
     * @param students Les élèves du cours
     * @param teacher Le professeur du cours
     * @param date La date du cours
     * @param startTime L'heure de début du cours
     * @param endTime L'heure de fin du cours
     */
    public Course(String name, List<Student> students, Teacher teacher, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.students = students;
        this.teacher = teacher;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** Constructeur avec paramètres
     * @param id L'id du cours
     * @param name Le nom du cours
     * @param teacher Le professeur du cours
     * @param date La date du cours
     * @param startTime L'heure de début du cours
     * @param endTime L'heure de fin du cours
     */
    public Course(int id, String name, Teacher teacher, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** Retourne l'id du cours
     * @return L'id du cours
     */
    public int getId() {
        return id;
    }

    /** Modifie l'id du cours
     * @param id Le nouvel id du cours
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Retourne le nom du cours
     * @return Le nom du cours
     */
    public String getName() {
        return name;
    }

    /** Modifie le nom du cours
     * @param name Le nouveau nom du cours
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Retourne les élèves du cours
     * @return Les élèves du cours
     */
    public List<Student> getStudents() {
        return students;
    }

    /** Modifie les élèves du cours
     * @param students Les nouveaux élèves du cours
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /** Ajouter un élève au cours
     * @param student L'élève à ajouter au cours
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /** Supprimer un élève du cours
     * @param student L'élève à supprimer du cours
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /** Supprimer un étudiant par id
     * @param id L'id de l'étudiant à supprimer
     */
    public void removeStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                break;
            }
        }
    }

    /** Retourne un élève par id
     * @param id L'id de l'élève à retourner
     * @return L'élève correspondant à l'id
     */
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /** Retourne l'élève par username
     * @param username Le nom d'utilisateur de l'élève à retourner
     * @return L'élève correspondant au nom d'utilisateur
     */
    public Student getStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    /** Supprime un élève par nom
     * @param name Le nom de l'élève à supprimer
     */
    public void removeStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                students.remove(student);
                break;
            }
        }
    }

    /** Retourne un élève par nom
     * @param name Le nom de l'élève à retourner
     * @return L'élève correspondant au nom
     */
    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    /** Retourne le professeur du cours
     * @return Le professeur du cours
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /** Modifie le professeur du cours
     * @param teacher Le nouveau professeur du cours
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /** Retourne la date du cours
     * @return La date du cours
     */
    public LocalDate getDate() {
        return date;
    }

    /** Modifie la date du cours
     * @param date La nouvelle date du cours
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** Retourne l'heure de début du cours
     * @return L'heure de début du cours
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /** Modifie l'heure de début du cours
     * @param startTime La nouvelle heure de début du cours
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /** Retourne l'heure de fin du cours
     * @return L'heure de fin du cours
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /** Modifie l'heure de fin du cours
     * @param endTime La nouvelle heure de fin du cours
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}

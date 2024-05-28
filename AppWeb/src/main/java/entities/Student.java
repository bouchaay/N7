package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    /** L'id de l'étudiant */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Le nom de l'étudiant */
    private String name;
    /** Le nom d'utilisateur de l'étudiant unique */
    @Column(unique = true)
    private String username;
    /** Le mot de passe de l'étudiant */
    private String password;

    /** Constructeur par défaut */
    public Student() {
    }

    /** Constructeur avec paramètres
     * @param name Le nom de l'étudiant
     * @param username Le nom d'utilisateur de l'étudiant
     * @param password Le mot de passe de l'étudiant
     */
    public Student(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /** Constructeur avec paramètres
     * @param id L'id de l'étudiant
     * @param name Le nom de l'étudiant
     * @param username Le nom d'utilisateur de l'étudiant
     * @param password Le mot de passe de l'étudiant
     */
    public Student(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /** Retourne l'id de l'étudiant
     * @return L'id de l'étudiant
     */
    public int getId() {
        return id;
    }

    /** Modifie l'id de l'étudiant
     * @param id Le nouvel id de l'étudiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Retourne le nom de l'étudiant
     * @return Le nom de l'étudiant
     */
    public String getName() {
        return name;
    }

    /** Modifie le nom de l'étudiant
     * @param name Le nouveau nom de l'étudiant
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Retourne le nom d'utilisateur de l'étudiant
     * @return Le nom d'utilisateur de l'étudiant
     */
    public String getUsername() {
        return username;
    }

    /** Modifie le nom d'utilisateur de l'étudiant
     * @param username Le nouveau nom d'utilisateur de l'étudiant
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Retourne le mot de passe de l'étudiant
     * @return Le mot de passe de l'étudiant
     */
    public String getPassword() {
        return password;
    }

    /** Modifie le mot de passe de l'étudiant
     * @param password Le nouveau mot de passe de l'étudiant
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher {

    /** L'id de l'enseignant */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Le nom de l'enseignant */
    private String name;
    /** Le nom d'utilisateur de l'enseignant */
    private String username;
    /** Le mot de passe de l'enseignant */
    private String password;

    /** Constructeur par défaut */
    public Teacher() {
    }

    /** Constructeur avec paramètres
     * @param name Le nom de l'enseignant
     * @param username Le nom d'utilisateur de l'enseignant
     * @param password Le mot de passe de l'enseignant
     */
    public Teacher(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /** Constructeur avec paramètres
     * @param id L'id de l'enseignant
     * @param name Le nom de l'enseignant
     * @param username Le nom d'utilisateur de l'enseignant
     * @param password Le mot de passe de l'enseignant
     */
    public Teacher(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /** Retourne l'id de l'enseignant
     * @return L'id de l'enseignant
     */
    public int getId() {
        return id;
    }

    /** Modifie l'id de l'enseignant
     * @param id Le nouvel id de l'enseignant
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Retourne le nom de l'enseignant
     * @return Le nom de l'enseignant
     */
    public String getName() {
        return name;
    }

    /** Modifie le nom de l'enseignant
     * @param name Le nouveau nom de l'enseignant
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Retourne le nom d'utilisateur de l'enseignant
     * @return Le nom d'utilisateur de l'enseignant
     */
    public String getUsername() {
        return username;
    }

    /** Modifie le nom d'utilisateur de l'enseignant
     * @param username Le nouveau nom d'utilisateur de l'enseignant
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Retourne le mot de passe de l'enseignant
     * @return Le mot de passe de l'enseignant
     */
    public String getPassword() {
        return password;
    }

    /** Modifie le mot de passe de l'enseignant
     * @param password Le nouveau mot de passe de l'enseignant
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

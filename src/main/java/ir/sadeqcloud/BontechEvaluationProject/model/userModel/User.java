package ir.sadeqcloud.BontechEvaluationProject.model.userModel;

import javax.persistence.*;

@Entity
@Table(name = "general_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

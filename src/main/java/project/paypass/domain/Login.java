package project.paypass.domain;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Login {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String mainId;

    @Column(nullable = false)
    private String password;

    public Login(String mainId, String password) {
        this.mainId = mainId;
        this.password = password;
    }
}

package pl.marcb.MRP.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(Views.Account.class)
    private Long id;

    @NotNull
    @Column(name = "name")
    @JsonView(Views.Account.class)
    private String name;

    @NotNull
    @Column(name = "surname")
    @JsonView(Views.Account.class)
    private String surname;

    @NotNull
    @Column(name = "login")
    @JsonView(Views.Account.class)
    private String login;

    @NotNull
    @Column(name = "email")
    @JsonView(Views.Account.class)
    private String email;

    @NotNull
    @Column(name = "pass")
    @JsonView(Views.Account.class)
    private String pass;
}

package pl.marcb.MRP.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;

@Entity(name = "storage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Storage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(Views.Storage.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    @JsonView(Views.Storage.class)
    private Product product;

    @Column(name = "value")
    @JsonView({Views.Storage.class, Views.Product.class})
    private int value;
}

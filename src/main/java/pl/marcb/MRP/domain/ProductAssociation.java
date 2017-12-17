package pl.marcb.MRP.domain;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "product_association")
public class ProductAssociation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(Views.ProductAssociation.class)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_parent", referencedColumnName = "id")
    @JsonView(Views.ProductAssociation.class)
    private Product productParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "id")
    @JsonView(Views.ProductAssociation.class)
    private Product product;

    @Column(name = "product_number")
    @JsonView(Views.ProductAssociation.class)
    private int number;
}

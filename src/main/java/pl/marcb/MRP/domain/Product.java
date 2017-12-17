package pl.marcb.MRP.domain;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.domain.enums.UnitEnum;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(Views.Product.class)
    private Long id;

    @Column(name = "product_name")
    @JsonView(Views.Product.class)
    private String productName;

    @Column(name = "product_foq")
    @JsonView(Views.Product.class)
    private Integer productFOQ;

    @Column(name = "product_unit")
    @JsonView(Views.Product.class)
    private UnitEnum productUnit;

    @Column(name = "product_lt")
    @JsonView(Views.Product.class)
    private Integer productLT;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonView(Views.Product.class)
    private List<ProductAssociation> products;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productParent")
    @JsonView(Views.Product.class)
    private List<ProductAssociation> productParents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonView(Views.Product.class)
    private List<PurchaseHistory> purchaseHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonView(Views.Product.class)
    private List<Storage> storage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonView(Views.Product.class)
    private List<StorageUpdate> storageUpdates;
}

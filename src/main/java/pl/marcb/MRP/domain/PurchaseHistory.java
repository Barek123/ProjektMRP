package pl.marcb.MRP.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "purchase_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(Views.PurchaseHistory.class)
    private Long id;

    @Column(name = "start_date")
    @JsonView(Views.PurchaseHistory.class)
    private Date startDate;

    @Column(name = "end_date")
    @JsonView(Views.PurchaseHistory.class)
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product", referencedColumnName = "id")
    @JsonView(Views.PurchaseHistory.class)
    private Product product;

    @Column(name = "is_order")
    @JsonView(Views.PurchaseHistory.class)
    private boolean isOrder;

    @Column(name = "netto")
    @JsonView(Views.PurchaseHistory.class)
    private int netto;

    @Column(name = "brutto")
    @JsonView(Views.PurchaseHistory.class)
    private int brutto;
}

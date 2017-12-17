package pl.marcb.MRP.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.view.Views;

import javax.persistence.*;
import java.util.Date;

@Entity(name="storage_update")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageUpdate {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(Views.StorageUpdate.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    @JsonView(Views.StorageUpdate.class)
    private Product product;

    @Column(name = "date")
    @JsonView(Views.StorageUpdate.class)
    private Date date;

    @Column(name = "number")
    @JsonView(Views.StorageUpdate.class)
    private int number;
}

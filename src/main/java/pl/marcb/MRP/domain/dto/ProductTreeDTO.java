package pl.marcb.MRP.domain.dto;

import lombok.*;
import pl.marcb.MRP.domain.Product;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTreeDTO {
    private int level;
    private int number;
    private Product parent;
    private List<ProductTreeDTO> products;
}

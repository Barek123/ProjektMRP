package pl.marcb.MRP.domain.dto;

import lombok.*;
import pl.marcb.MRP.domain.Product;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MrpDto {
    private Date date;
    private Product product;
    private Integer number;
}
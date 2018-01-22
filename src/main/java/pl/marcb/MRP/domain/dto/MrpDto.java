package pl.marcb.MRP.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.view.Views;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MrpDto {
    private Date date;
    private Long productId;
    private Integer number;
}
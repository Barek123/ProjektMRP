package pl.marcb.MRP.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.marcb.MRP.domain.enums.UnitEnum;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto  implements Serializable {
    private Integer id;
    private String productName;
    private Integer productFOQ;
    private UnitEnum productUnit;
    private Integer productLT;
    private Integer storage;
}

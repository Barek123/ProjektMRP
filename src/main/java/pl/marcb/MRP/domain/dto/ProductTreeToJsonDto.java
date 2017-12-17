package pl.marcb.MRP.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTreeToJsonDto {
    private int level;
    private int number;
    private Long parentId;
    private String parentname;
    private List<ProductTreeToJsonDto> products;
}

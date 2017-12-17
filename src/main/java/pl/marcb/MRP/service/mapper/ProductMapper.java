package pl.marcb.MRP.service.mapper;

import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.dto.ProductDto;

import java.util.Optional;

public class ProductMapper {
    private ProductDto productDto;

    public ProductMapper(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Product wrap(){
        Product product = new Product();
        Optional.ofNullable(productDto.getProductName()).ifPresent(c -> product.setProductName(c));
        Optional.ofNullable(productDto.getProductFOQ()).ifPresent(c -> product.setProductFOQ(c));
        Optional.ofNullable(productDto.getProductUnit()).ifPresent(c -> product.setProductUnit(c));
        Optional.ofNullable(productDto.getProductLT()).ifPresent(c -> product.setProductLT(c));

        return product;
    }
}

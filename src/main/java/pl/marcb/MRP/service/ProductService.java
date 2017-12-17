package pl.marcb.MRP.service;

import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.dto.ProductDto;
import pl.marcb.MRP.domain.dto.ProductTreeDTO;
import pl.marcb.MRP.domain.dto.ProductTreeToJsonDto;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;

import java.util.List;

public interface ProductService {
    Product findProductById(Long id);
    Product insertProduct(ProductDto product);
    Product updateProduct(Long id, ProductDto product);
    List<Product> findAllProducts();
    List<Product> findAllProductsByQuery(String query);
    List<Product> findAllProductsExceptOne(Long exceptProductId);
    List<Product> findAllProductsByQueryExceptOne(Long exceptProductId, String query);
    void removeProduct(Long id);
//    TreeItem<String> getItemTreeByProduct(Product product); //Integer number;
ProductTreeToJsonDto getProductsTreeById(Long productId, int number) throws BusinessEntityNotFoundException;
    ProductTreeDTO getProductsTree(Product product, int number);
}

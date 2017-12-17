package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.marcb.MRP.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT l from product l where l.productName like CONCAT('%', :query, '%')")
    List<Product> findAllProductsByQuery(@Param("query") String query);

    @Query("SELECT l from product l where l.id <> :exceptedProductId")
    List<Product> findAllProductsExceptOne(@Param("exceptedProductId") Long exceptedProductId);

    @Query("SELECT l from product l where l.id <> :exceptedProductId and l.productName like CONCAT('%', :query, '%')")
    List<Product> findAllProductsByQueryExceptOne(@Param("exceptedProductId") Long exceptedProductId, @Param("query") String query);
}

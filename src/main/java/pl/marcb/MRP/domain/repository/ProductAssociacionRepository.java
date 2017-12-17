package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.ProductAssociation;

import java.util.List;

public interface ProductAssociacionRepository extends JpaRepository<ProductAssociation, Long> {
    List<ProductAssociation> findAllByProductParent(Product productParent);
    List<ProductAssociation> findAllByProductAndProductParent(Product product, Product productParent);
    void deleteByProductOrProductParent(Product product, Product productParent);
}

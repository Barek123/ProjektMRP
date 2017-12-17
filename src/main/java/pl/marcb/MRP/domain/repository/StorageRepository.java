package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.Storage;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findAllByProduct(Product product);
    void deleteByProduct(Product product);
}

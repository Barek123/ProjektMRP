package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.StorageUpdate;

import java.util.Date;
import java.util.List;

public interface StorageUpdateRepository extends JpaRepository<StorageUpdate, Long> {
    List<StorageUpdate> findAllByDate(Date date);
    void deleteByProduct(Product product);

}

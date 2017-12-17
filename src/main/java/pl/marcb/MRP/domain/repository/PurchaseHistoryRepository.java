package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.PurchaseHistory;

import java.util.Date;
import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    @Query("SELECT ph from purchase_history ph where ph.startDate>= :from and ph.endDate <= :to")
    List<PurchaseHistory> findAllByRange(@Param("from") Date from, @Param("to") Date to);

    List<PurchaseHistory> findAllByProduct(Product product);

    void deleteByProduct(Product product);
}

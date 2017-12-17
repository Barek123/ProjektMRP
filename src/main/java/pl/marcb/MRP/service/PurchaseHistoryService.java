package pl.marcb.MRP.service;

import pl.marcb.MRP.domain.PurchaseHistory;

import java.util.Date;
import java.util.List;

public interface PurchaseHistoryService {
    void removeAllPurchases();
    PurchaseHistory insertNewPurchase(PurchaseHistory purchaseHistory);
    PurchaseHistory findPurchaseById(Long purchaseHistoryId);
    List<PurchaseHistory> findAll();
    List<PurchaseHistory> findAllByRange(Date from, Date to);
}

package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcb.MRP.domain.PurchaseHistory;
import pl.marcb.MRP.domain.repository.PurchaseHistoryRepository;
import pl.marcb.MRP.service.PurchaseHistoryService;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService{
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public void removeAllPurchases() {
        this.purchaseHistoryRepository.deleteAll();
    }

    @Override
    public PurchaseHistory insertNewPurchase(PurchaseHistory purchaseHistory) {
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory findPurchaseById(Long purchaseHistoryId) {
        return purchaseHistoryRepository.findOne(purchaseHistoryId);
    }

    @Override
    public List<PurchaseHistory> findAll() {
        return purchaseHistoryRepository.findAll();
    }

    @Override
    public List<PurchaseHistory> findAllByRange(Date from, Date to) {
        return purchaseHistoryRepository.findAllByRange(from, to);
    }
}

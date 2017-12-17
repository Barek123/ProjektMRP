package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.PurchaseHistory;
import pl.marcb.MRP.domain.Storage;
import pl.marcb.MRP.domain.StorageUpdate;
import pl.marcb.MRP.domain.dto.ProductTreeDTO;
import pl.marcb.MRP.domain.repository.ProductRepository;
import pl.marcb.MRP.domain.repository.PurchaseHistoryRepository;
import pl.marcb.MRP.domain.repository.StorageRepository;
import pl.marcb.MRP.domain.repository.StorageUpdateRepository;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.LogicException;
import pl.marcb.MRP.service.MrpService;
import pl.marcb.MRP.service.ProductService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrpServiceImpl implements MrpService {
    private ProductRepository productRepository;
    private StorageRepository storageRepository;
    private StorageUpdateRepository storageUpdateRepository;
    private PurchaseHistoryRepository purchaseHistoryRepository;
    private ProductService productService;

    private List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();


    @Autowired
    public MrpServiceImpl(ProductRepository productRepository, StorageRepository storageRepository, StorageUpdateRepository storageUpdateRepository, PurchaseHistoryRepository purchaseHistoryRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.storageUpdateRepository = storageUpdateRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.productService = productService;
    }

    @Override
    public void generateMRP(Date finishDate, Product product, Integer number) throws BusinessEntityNotFoundException, LogicException {
        List<Storage> storageList = storageRepository.findAllByProduct(product);
        if (storageList.size() == 0) {
            throw new BusinessEntityNotFoundException("not found storage list");
        }

        ProductTreeDTO productsTree = productService.getProductsTree(product, number);

        purchaseHistoryList.clear();
        addPurchase(productsTree, finishDate);

        Date now = new Date();
        if(purchaseHistoryList.stream()
                .map(c -> c.getStartDate().getTime()> now.getTime())
                .filter(c -> c.equals(true))
                .count() > 0){
            throw new LogicException("not correct purchase history list");
        }

        purchaseHistoryList.forEach(c -> {
            purchaseHistoryRepository.save(c);
            updateStorage(c);
        });
    }

    private void updateStorage(PurchaseHistory purchaseHistory){
        List<Storage> allByProduct = storageRepository.findAllByProduct(purchaseHistory.getProduct());
        if(allByProduct.size() ==0){
            Storage currentStorage = allByProduct.get(0);

            if (purchaseHistory.getNetto() > 0) {

                int valueToStorageUpdate = purchaseHistory.getProduct().getProductFOQ() -
                        (purchaseHistory.getNetto() % purchaseHistory.getProduct().getProductFOQ());

                StorageUpdate storageUpdate = StorageUpdate.builder()
                        .product(purchaseHistory.getProduct())
                        .date(purchaseHistory.getEndDate())
                        .number(valueToStorageUpdate).build();

                storageUpdateRepository.save(storageUpdate);
            }
            currentStorage.setValue(0);
            storageRepository.save(currentStorage);
        }
    }

    private void addPurchase(ProductTreeDTO productTree, Date endDate) {
        Date endDateCopy = endDate; //in lambda variable must be final
        List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.findAllByProduct(productTree.getParent());

        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .netto(productTree.getNumber() - storageRepository.findAllByProduct(productTree.getParent()).get(0).getValue())
                .endDate(endDateCopy)
                .isOrder(productTree.getLevel() == 0)
                .product(productTree.getParent())
                .brutto(productTree.getNumber()).build();

        Date startDate = getNewDate(endDateCopy, purchaseHistory, productTree);
        purchaseHistory.setStartDate(startDate);

        List<PurchaseHistory> conflicts = purchaseHistories.stream().filter(c -> {
            boolean endDateCorrect = endDateCopy.getTime() > c.getStartDate().getTime() && endDateCopy.getTime() < c.getEndDate().getTime();
            boolean startDateCorrect = startDate.getTime() > c.getStartDate().getTime() && startDate.getTime() < c.getEndDate().getTime();

            return endDateCorrect || startDateCorrect;
        }).collect(Collectors.toList());


        if (conflicts.size() > 0) {
            for (PurchaseHistory item : conflicts) {
                if (endDate.getTime() > item.getStartDate().getTime()) {
                    endDate = item.getStartDate();
                }
            }
            addPurchase(productTree, endDateCopy);
        }else{
            purchaseHistoryList.add(purchaseHistory);

            for(ProductTreeDTO ptDto : productTree.getProducts()){
                addPurchase(ptDto, startDate);
            }
        }

    }

    private Date getNewDate(Date date, PurchaseHistory purchaseHistory, ProductTreeDTO productTree) {
        int rootLT = productTree.getParent().getProductLT();
        if (purchaseHistory.getNetto() / productTree.getParent().getProductFOQ() > 1) {
            rootLT *= Math.ceil((float) purchaseHistory.getNetto() / (float) productTree.getParent().getProductFOQ());
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < rootLT; i++) {
            if (dayOfWeek == 6 || dayOfWeek == 7) {
                i--;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            date.setTime(calendar.getTime().getTime());
        }

        return date;
    }
}

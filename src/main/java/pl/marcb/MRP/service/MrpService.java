package pl.marcb.MRP.service;

import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.PurchaseHistory;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.LogicException;

import java.util.Date;
import java.util.List;

public interface MrpService {
    List<PurchaseHistory> generateMRP(Date finishDate, Long productId, Integer number) throws BusinessEntityNotFoundException, LogicException;
}

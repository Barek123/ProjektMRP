package pl.marcb.MRP.service;

import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.LogicException;

import java.util.Date;

public interface MrpService {
    void generateMRP(Date finishDate, Product product, Integer number) throws BusinessEntityNotFoundException, LogicException;
}

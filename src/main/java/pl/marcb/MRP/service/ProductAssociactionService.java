package pl.marcb.MRP.service;

import pl.marcb.MRP.domain.ProductAssociation;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.OperationNotAllowedException;

public interface ProductAssociactionService {
    ProductAssociation insertNewProductAssociation(ProductAssociation productAssociation) throws BusinessEntityNotFoundException, OperationNotAllowedException;
    void deleteProductAssociation(Long id, Long childId) throws BusinessEntityNotFoundException;
}

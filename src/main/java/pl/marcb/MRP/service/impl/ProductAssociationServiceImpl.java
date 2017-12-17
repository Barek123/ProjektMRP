package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.ProductAssociation;
import pl.marcb.MRP.domain.repository.ProductAssociacionRepository;
import pl.marcb.MRP.domain.repository.ProductRepository;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.OperationNotAllowedException;
import pl.marcb.MRP.service.ProductAssociactionService;

import java.util.List;

@Service
public class ProductAssociationServiceImpl implements ProductAssociactionService {
    private ProductAssociacionRepository productAssociacionRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductAssociationServiceImpl(ProductAssociacionRepository productAssociacionRepository, ProductRepository productRepository) {
        this.productAssociacionRepository = productAssociacionRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductAssociation insertNewProductAssociation(ProductAssociation productAssociation) throws BusinessEntityNotFoundException, OperationNotAllowedException {
        Product product = productRepository.findOne(productAssociation.getProduct().getId());
        Product parentProduct = productRepository.findOne(productAssociation.getProductParent().getId());
        if (product == null || parentProduct == null) {
            throw new BusinessEntityNotFoundException("product not exist");
        }

        checkAssociationConflict(parentProduct, product);
        List<ProductAssociation> exsistInDatabase = productAssociacionRepository.findAllByProductAndProductParent(product, parentProduct);

        if(exsistInDatabase.size()!=0){
            ProductAssociation productAssociationToSave = exsistInDatabase.get(0);
            productAssociationToSave.setNumber(productAssociationToSave.getNumber()+productAssociation.getNumber());
            return productAssociacionRepository.save(productAssociationToSave);
        }

        return productAssociacionRepository.save(ProductAssociation.builder()
                .number(productAssociation.getNumber())
                .product(product)
                .productParent(parentProduct).build());
    }

    @Override
    public void deleteProductAssociation(Long id, Long childId) throws BusinessEntityNotFoundException {
        Product productParent = productRepository.findOne(id);
        Product productChildren = productRepository.findOne(childId);
        if(productParent == null || productChildren == null){
            throw new BusinessEntityNotFoundException("product not exist");
        }

        List<ProductAssociation> allByProductAndProductParent = productAssociacionRepository.findAllByProductAndProductParent(productChildren, productParent);
        if(allByProductAndProductParent == null || allByProductAndProductParent.size()==0){
            throw new BusinessEntityNotFoundException("connection not found");
        }

        allByProductAndProductParent.forEach(c -> {
            productAssociacionRepository.delete(c.getId());
        });
    }

    private void checkAssociationConflict(Product parentProduct, Product product) throws OperationNotAllowedException {
        List<ProductAssociation> allByProductParent = productAssociacionRepository.findAllByProductParent(product);
        for(ProductAssociation productAssociation : allByProductParent){
            if(productAssociation.getProduct().getId().equals(parentProduct.getId())){
                throw new OperationNotAllowedException("product association not possible");
            }
            checkAssociationConflict(productAssociation.getProduct(), parentProduct);
        }
    }
}

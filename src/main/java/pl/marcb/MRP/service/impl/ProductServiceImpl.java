package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.ProductAssociation;
import pl.marcb.MRP.domain.Storage;
import pl.marcb.MRP.domain.StorageUpdate;
import pl.marcb.MRP.domain.dto.ProductDto;
import pl.marcb.MRP.domain.dto.ProductTreeDTO;
import pl.marcb.MRP.domain.dto.ProductTreeToJsonDto;
import pl.marcb.MRP.domain.repository.*;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.service.ProductService;
import pl.marcb.MRP.service.mapper.ProductMapper;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductAssociacionRepository productAssociacionRepository;
    private StorageRepository storageRepository;
    private PurchaseHistoryRepository purchaseHistoryRepository;
    private StorageUpdateRepository storageUpdateRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductAssociacionRepository productAssociacionRepository, StorageRepository storageRepository, PurchaseHistoryRepository purchaseHistoryRepository, StorageUpdateRepository storageUpdateRepository) {
        this.productRepository = productRepository;
        this.productAssociacionRepository = productAssociacionRepository;
        this.storageRepository = storageRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.storageUpdateRepository = storageUpdateRepository;
    }

    @Override
    public Product findProductById(Long id) {
        return this.productRepository.findOne(id);
    }

    @Override
    public Product insertProduct(ProductDto productDto) {
        Product wrap = new ProductMapper(productDto).wrap();
        Product save = this.productRepository.save(wrap);
        storageRepository.save(Storage.builder()
                .product(save)
                .value(productDto.getStorage())
                .build());
        return save;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDto product) {
        Product productToSave = new ProductMapper(product).wrap();
        productToSave.setId(id);
        Product save = this.productRepository.save(productToSave);
        List<Storage> allByProduct = storageRepository.findAllByProduct(save);
        if(allByProduct.size()==1){
            Storage storage = allByProduct.get(0);
            storage.setValue(product.getStorage());
            storageRepository.save(storage);
        }else{
            storageRepository.deleteByProduct(save);
            storageRepository.save(Storage.builder()
                    .product(save)
                    .value(product.getStorage())
                    .build());
        }
        return save;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsByQuery(String query) {
        return productRepository.findAllProductsByQuery(query);
    }

    @Override
    public List<Product> findAllProductsExceptOne(Long exceptProductId) {
        return productRepository.findAllProductsExceptOne(exceptProductId);
    }

    @Override
    public List<Product> findAllProductsByQueryExceptOne(Long exceptProductId, String query) {
        return productRepository.findAllProductsByQueryExceptOne(exceptProductId, query);
    }

    @Override
    @Transactional
    public void removeProduct(Long id) {
        Product product = productRepository.findOne(id);
        storageRepository.deleteByProduct(product);
        storageUpdateRepository.deleteByProduct(product);
        purchaseHistoryRepository.deleteByProduct(product);
        productAssociacionRepository.deleteByProductOrProductParent(product, product);
        productRepository.delete(id);
    }

    @Override
    @Transactional
    public ProductTreeToJsonDto getProductsTreeById(Long productId, int number) throws BusinessEntityNotFoundException {
        Product product = productRepository.findOne(productId);
        if(product == null){
            throw new BusinessEntityNotFoundException("product not found");
        }

        return getProductTreeToJsonItem(product, number, 1);
    }

    private ProductTreeToJsonDto getProductTreeToJsonItem(Product product, int number, int level) {
        List<ProductAssociation> byProductParent = productAssociacionRepository.findAllByProductParent(product);
        List<ProductTreeToJsonDto> children = new ArrayList<ProductTreeToJsonDto>();
        children.addAll(byProductParent
                .stream()
                .map(c -> getProductTreeToJsonItem(c.getProduct(), c.getNumber() * number, level + 1))
                .collect(Collectors.toList()));
        ProductTreeToJsonDto root = new ProductTreeToJsonDto(level, number, product.getId(), product.getProductName(), children);

        return root;
    }

//    @Override
//    public ProductTreeDTO getItemTreeByProduct(Product product) {
//        return mapProductsTree(product, 1);
//    }
//
//    private ProductTreeDTO mapProductsTree(Product product, int number) {
////        TreeItem<String> root = new TreeItem<String>(
////                product.getProductName() + " (" + number + " " + product.getProductUnit() + ")"
////        );
//
//
//        List<ProductAssociation> children = productAssociacionRepository.findAllByProductParent(product);
//        root.getChildren().addAll(children
//                .stream()
//                .map(c -> mapProductsTree(c.getProduct(), c.getNumber() * number))
//                .collect(Collectors.toList()));
//
//        return root;
//    }

    @Override
    public ProductTreeDTO getProductsTree(Product product, int number){
        return getProductTreeItem(product, number, 1);
    }

    @Transactional
    private ProductTreeDTO getProductTreeItem(Product product, int number, int level) {
        List<ProductAssociation> byProductParent = productAssociacionRepository.findAllByProductParent(product);
        List<ProductTreeDTO> children = new ArrayList<ProductTreeDTO>();
        children.addAll(byProductParent
                .stream()
                .map(c -> getProductTreeItem(c.getProduct(), c.getNumber() * number, level + 1))
                .collect(Collectors.toList()));
        ProductTreeDTO root = new ProductTreeDTO(level, number, product, children);

        return root;
    }
}

package pl.marcb.MRP.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.ProductAssociation;
import pl.marcb.MRP.domain.dto.ProductTreeDTO;
import pl.marcb.MRP.domain.dto.ProductTreeToJsonDto;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.OperationNotAllowedException;
import pl.marcb.MRP.service.ProductAssociactionService;
import pl.marcb.MRP.service.ProductService;
import pl.marcb.MRP.view.Views;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/productsassociation")
public class ProductAssociationController {
    @Autowired
    private ProductAssociactionService productAssociactionService;
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(Views.ProductAssociation.class)
    public @ResponseBody ProductAssociation insertNewProductAssociation(@RequestBody ProductAssociation productAssociation) throws BusinessEntityNotFoundException, OperationNotAllowedException {
        return productAssociactionService.insertNewProductAssociation(productAssociation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/tree")
    @JsonView(Views.ProductAssociation.class)
    public @ResponseBody
    ProductTreeToJsonDto getProductTree(@PathVariable("productId") Long productId) throws BusinessEntityNotFoundException {
        return productService.getProductsTreeById(productId, 1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/tree/{number}")
    @JsonView(Views.ProductAssociation.class)
    public @ResponseBody
    ProductTreeToJsonDto getProductTree(@PathVariable("productId") Long productId, @PathVariable("number") Integer number) throws BusinessEntityNotFoundException {
        return productService.getProductsTreeById(productId, number);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/parent/{id}/child/{childId}")
    public void removeProductAssociation(@PathVariable("id") Long id, @PathVariable("childId") Long childId) throws BusinessEntityNotFoundException {
        productAssociactionService.deleteProductAssociation(id, childId);
    }
}

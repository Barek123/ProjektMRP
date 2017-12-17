package pl.marcb.MRP.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.Product;
import pl.marcb.MRP.domain.dto.ProductDto;
import pl.marcb.MRP.domain.enums.UnitEnum;
import pl.marcb.MRP.service.ProductService;
import pl.marcb.MRP.view.Views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @JsonView(Views.Product.class)
    public @ResponseBody Product  getProductById(@PathVariable("id") Long id){
        return productService.findProductById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/measures")
    @JsonView(Views.Product.class)
    public @ResponseBody List<String>  getProductMeasures(){
        return Arrays.stream(UnitEnum.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Product.class)
    public @ResponseBody Product insertProduct(@RequestBody ProductDto productDto) {
        return productService.insertProduct(productDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Product.class)
    public @ResponseBody Product put(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.Product.class)
    public @ResponseBody List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query/{query}")
    @JsonView(Views.Product.class)
    public @ResponseBody List<Product>  findAllProductsByQuery(@PathVariable("query") String query ) {
        return productService.findAllProductsByQuery(query);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/except/{id}")
    @JsonView(Views.Product.class)
    public @ResponseBody List<Product>  findAllProductsExceptOne(@PathVariable("id") Long exceptProductId ) {
        return productService.findAllProductsExceptOne(exceptProductId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/except/{id}/query/{query}")
    @JsonView(Views.Product.class)
    public @ResponseBody List<Product>  findAllProductsByQueryExceptOne(@PathVariable("id") Long exceptProductId, @PathVariable("query") String query ) {
        return productService.findAllProductsByQueryExceptOne(exceptProductId, query);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void removeProduct(@PathVariable("id") Long id){
        productService.removeProduct(id);
    }
}

package ricardo.porfirio.ApiProducts.Controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import ricardo.porfirio.ApiProducts.Models.ProductsModel;
import ricardo.porfirio.ApiProducts.Services.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/api/v1/products/{id}")
    public ProductsModel getProductById(@PathVariable String id) throws InterruptedException, ExecutionException{
        return productsService.getProduct(id);
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductsModel>> getAllProducts() throws InterruptedException, ExecutionException{
        return productsService.getAllProducts();
    }

    @GetMapping("/api/v1/products/firstPagination")
    public ResponseEntity<List<ProductsModel>> getFirstPageProducts() throws InterruptedException, ExecutionException, TimeoutException {
        return productsService.getFirstPageProducts();
    }
    
    @GetMapping("/api/v1/products/nextPagination")
    public ResponseEntity<List<ProductsModel>> getNextPageProducts() throws InterruptedException, ExecutionException, TimeoutException {
        return productsService.getNextPageProducts();
    }
    
    
    @PostMapping("/api/v1/products") 
    public String saveProducts(@RequestBody ProductsModel products) throws InterruptedException, ExecutionException{
        return productsService.saveProduct(products);
    }

    @PutMapping("api/v1/products/{id}")
    public String updateProducts(@PathVariable String id, @RequestBody ProductsModel productsModel) throws InterruptedException, ExecutionException{
        return productsService.updateProduct(id, productsModel);
    }

}

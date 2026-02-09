package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Product deleteById(String id){
        Product product = findById(id);
        if (product != null){
            productData.remove(product);
        }
        return product;
    }

    public Product findById(String searchId){
        for(Product product : productData){
            String productId = product.getProductID();
            if(productId.equals(searchId)){
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}

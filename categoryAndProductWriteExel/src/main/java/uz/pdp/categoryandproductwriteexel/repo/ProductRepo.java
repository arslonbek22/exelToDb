package uz.pdp.categoryandproductwriteexel.repo;

import org.apache.poi.util.LocaleID;
import uz.pdp.categoryandproductwriteexel.entity.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepo extends MongoRepository<Product, String>{

    List<Product> findAllByCategoryId(String id);

}

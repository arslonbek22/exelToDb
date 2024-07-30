package uz.pdp.categoryandproductwriteexel.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.categoryandproductwriteexel.entity.Category;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {

}

package uz.pdp.categoryandproductwriteexel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.categoryandproductwriteexel.entity.Product;
import uz.pdp.categoryandproductwriteexel.repo.ProductRepo;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productRepo;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }
}

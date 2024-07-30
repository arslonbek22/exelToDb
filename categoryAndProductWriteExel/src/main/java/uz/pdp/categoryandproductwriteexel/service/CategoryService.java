package uz.pdp.categoryandproductwriteexel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.categoryandproductwriteexel.entity.Category;
import uz.pdp.categoryandproductwriteexel.repo.CategoryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> getAllCategory() {
        return  categoryRepo.findAll();
    }
}

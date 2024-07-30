package uz.pdp.categoryandproductwriteexel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.categoryandproductwriteexel.entity.Category;
import uz.pdp.categoryandproductwriteexel.entity.Product;
import uz.pdp.categoryandproductwriteexel.service.CategoryService;
import uz.pdp.categoryandproductwriteexel.service.ProductService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping
    public String home(Model model) {
        List<Category> allCategory = categoryService.getAllCategory();
        List<Product> products = productService.getAllProduct();
        if (allCategory!=null){
            model.addAttribute("categories", allCategory);
        }
        if (products!=null){
            model.addAttribute("products", products);
        }
        return "home";
    }




}

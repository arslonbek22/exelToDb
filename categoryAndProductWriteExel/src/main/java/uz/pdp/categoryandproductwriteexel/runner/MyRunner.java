package uz.pdp.categoryandproductwriteexel.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import uz.pdp.categoryandproductwriteexel.entity.Category;
import uz.pdp.categoryandproductwriteexel.entity.Product;
import uz.pdp.categoryandproductwriteexel.repo.CategoryRepo;
import uz.pdp.categoryandproductwriteexel.repo.ProductRepo;


@Controller
@RequiredArgsConstructor
public class MyRunner {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    /*@Override*/
    public void run(String... args) throws Exception {
        Category ichimliklar = categoryRepo.save(Category.builder()
                .name("Ichimliklar")
                .build());
        Category yeguliklar = categoryRepo.save(Category.builder()
                .name("Yeguliklar")
                .build());
        Category kiyguliklar = categoryRepo.save(Category.builder()
                .name("Kiyguliklar")
                .build());

        Product cola = productRepo.save(Product.builder()
                .name("Milliy Cola")
                .description("Uzbekistonda ishlab chiqarilgan")
                .price(7000)
                .category(ichimliklar)
                .build());
        Product olma = productRepo.save(Product.builder()
                .name("olma")
                .description("Dalada yetshtrilgan")
                .price(10000)
                .category(yeguliklar)
                .build());

        Product anor = productRepo.save(Product.builder()
                .name("anor")
                .description("Varganzaniki donasi katta yegan mazza qiladi")
                .price(25000)
                .category(yeguliklar)
                .build());

        Product flesh = productRepo.save(Product.builder()
                .name("Flesh")
                .description("Halol sertifikati mavjud")
                .price(11000)
                .category(ichimliklar)
                .build());

        Product finka = productRepo.save(Product.builder()
                .name("Finka")
                .description("Zara brendidan turkiya maxsuloti")
                .price(250000)
                .category(kiyguliklar)
                .build());

        Product shim = productRepo.save(Product.builder()
                .name("Shim")
                .description("Pol klassika turkiya maxsuloti")
                .price(240000)
                .category(kiyguliklar)
                .build());


    }
}

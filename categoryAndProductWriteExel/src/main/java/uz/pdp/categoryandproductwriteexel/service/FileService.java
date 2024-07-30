package uz.pdp.categoryandproductwriteexel.service;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.categoryandproductwriteexel.entity.Category;
import uz.pdp.categoryandproductwriteexel.entity.Product;
import uz.pdp.categoryandproductwriteexel.repo.CategoryRepo;
import uz.pdp.categoryandproductwriteexel.repo.ProductRepo;

import java.io.*;
import java.util.Iterator;
import java.util.List;

@EnableAsync
@RequiredArgsConstructor
@MultipartConfig
@Service
public class FileService {


    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;


    public File dataWriteToExel() {
        List<Category> allCategories = categoryRepo.findAll();


        File file = new File("files/file.xlsx");
        try (
                Workbook workbook = new XSSFWorkbook();
        ) {
            Sheet sheet = workbook.createSheet("data");
            int rowNum = 1;
            for (Category allCategory : allCategories) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(allCategory.getName());
                List<Product> allProducts = productRepo.findAllByCategoryId(allCategory.getId());
                for (Product allProduct : allProducts) {
                    Row row1 = sheet.createRow(rowNum++);
                    row1.createCell(1).setCellValue(allProduct.getName());
                    row1.createCell(2).setCellValue(allProduct.getDescription());
                    row1.createCell(3).setCellValue(allProduct.getPrice());

                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            } catch (Exception e) {
                System.out.println("yozmadi");
                e.printStackTrace();
            }
            System.out.println("Muommo yoq");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }


    public void dataSendWeb(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            Category category = null;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell firstCell = row.getCell(0);
                if (firstCell != null && firstCell.getCellType() == CellType.STRING) {
                    // Create a new category
                    category = new Category();
                    category.setName(firstCell.getStringCellValue());
                    categoryRepo.save(category);
                    System.out.println(category);
                }

                if (category != null) {
                    // Iterate through product rows
                    for (int i = 1; i < row.getLastCellNum(); i += 3) {
                        Cell nameCell = row.getCell(i);
                        Cell descriptionCell = row.getCell(i + 1);
                        Cell priceCell = row.getCell(i + 2);

                        if (nameCell != null && nameCell.getCellType() == CellType.STRING &&
                                descriptionCell != null && descriptionCell.getCellType() == CellType.STRING &&
                                priceCell != null && priceCell.getCellType() == CellType.NUMERIC) {

                            Product product = new Product();
                            product.setName(nameCell.getStringCellValue());
                            product.setDescription(descriptionCell.getStringCellValue());
                            product.setPrice(priceCell.getNumericCellValue());
                            product.setCategory(category);

                            productRepo.save(product);
                            System.out.println(product);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

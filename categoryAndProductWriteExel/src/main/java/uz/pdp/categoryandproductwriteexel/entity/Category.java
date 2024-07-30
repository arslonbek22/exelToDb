package uz.pdp.categoryandproductwriteexel.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "category")
public class Category {

    @Id
    private String id;
    private String name;

}

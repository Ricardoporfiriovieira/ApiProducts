package ricardo.porfirio.ApiProducts.Models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductsModel {

    private long id;
    
    private String name;

    private String description;

    private Float price;

}

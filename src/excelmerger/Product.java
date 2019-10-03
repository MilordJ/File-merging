/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelmerger;

import lombok.Data;

/**
 *
 * @author Lito
 */
@Data
public class Product {
    private String id = "";
    private String description = "";
    private String price = "";

    public Product() {
    }

    public Product(String id, String description, String price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
    
}

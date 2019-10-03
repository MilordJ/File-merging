/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelmerger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Lito
 */
public class ExcelMerger {
    /**
     */
    public static void main(String[] args) throws IOException {
        Reader();
    }
    
    static void Reader() throws FileNotFoundException, IOException {
        BufferedReader productBr = new BufferedReader(new FileReader("C:/Users/Lito/Desktop/products.csv"));
        BufferedReader productByDateBr = new BufferedReader(new FileReader("C:/Users/Lito/Desktop/prices_by_date.csv"));
        String productLine;
        String productByDateLine;
        Map<String, Product> productMap = new HashMap<>();
        int count = 0;
        while ((productLine = productBr.readLine()) != null) {
            if(count > 0) {
                Product product = new Product();
                String[] array = productLine.split(",");
                if (array.length > 0) {
                    product.setId(array[0]);
                    product.setDescription(array[1]);
                    productMap.put(array[0], product);
                }
            }
            ++count;
        }

        count = 0;
        while ((productByDateLine = productByDateBr.readLine()) != null) {
            if(count > 0) {
                String[] ll = productByDateLine.split(",");
                String productId = ll[0];
                String price = ll[2];
                Product product = productMap.get(productId);
                String oldPrice = product.getPrice();
                if(oldPrice.trim().isEmpty()) 
                    product.setPrice(price);
                else
                    product.setPrice(oldPrice + ", " + price);
                productMap.put(productId, product);
            }
            ++count;
        }
        write(productMap);
    }

    static void write(Map<String, Product> productMap) throws IOException {
        FileWriter csvWriter = new FileWriter("C:/Users/Lito/Desktop/all_prices.csv");
        String header = "PRODUCT_ID,PRODUCT_DESCRIPTION,PRICE";
        csvWriter.append(String.join(",", header));
        csvWriter.append("\n");
        for (String s: productMap.keySet()) {
            String id = productMap.get(s).getId();
            String name = productMap.get(s).getDescription();
            String price = "\"" + productMap.get(s).getPrice() + "\""; 
            String data = id + "," + name + "," + price;
            csvWriter.append(data);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

    }
}

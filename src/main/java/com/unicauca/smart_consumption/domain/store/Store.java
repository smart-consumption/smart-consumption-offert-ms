package com.unicauca.smart_consumption.domain.store;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.offer.Offer;
import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Store {
    private String id;
    private String name;
    private List<Product> products;
    private List<Offer> offers;
    private City city;



    public void updateStore(String name, City city) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (city != null) {
            this.city = city;
        }
    }

    public void addProducts(List<Product> products) {
        if (Objects.nonNull(products)) {
           products.forEach(product -> this.products.add(product));
        }
    }

    public void addOffer(Offer offer) {
        if (Objects.nonNull(offer) && !offers.contains(offer)) {
            offers.add(offer);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


    public boolean hasProduct(Product product) {
        return products.contains(product);
    }

    public List<Product> getSustainableProducts() {
        List<Product> sustainableProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isSustainable()) {
                sustainableProducts.add(product);
            }
        }
        return sustainableProducts;
    }

    public List<Product> getProductsByCategory(Category category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.isInCategory(category)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }


}

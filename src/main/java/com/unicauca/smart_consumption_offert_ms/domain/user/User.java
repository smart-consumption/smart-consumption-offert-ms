package com.unicauca.smart_consumption_offert_ms.domain.user;

import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String username;
    private String name;
    private ArrayList<Product> watchList;

    public boolean addProductToWatchList(Product product)
    {
        if (Objects.nonNull(product) && !watchList.contains(product))
        {
            watchList.add(product);
            return true;
        }
        return false;
    }

}

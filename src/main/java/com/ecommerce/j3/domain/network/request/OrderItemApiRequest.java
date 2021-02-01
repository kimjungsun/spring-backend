package com.ecommerce.j3.domain.network.request;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemApiRequest {
    private Long orderItemId;
    private Product product;
    private Order order;
    private String sku;
    private Integer price;
    private Integer discountRate;
    private Integer quantity;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

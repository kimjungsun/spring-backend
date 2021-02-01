package com.ecommerce.j3.domain.network.response;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionApiResponse {
    private Long suggestionId;
    private Account account;
    private Product product;
    private String content;
}

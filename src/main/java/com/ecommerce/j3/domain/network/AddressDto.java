package com.ecommerce.j3.domain.network;

import com.ecommerce.j3.domain.entity.Account;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AddressDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressApiRequest {
        @NotNull
        private Long addressId;
        private Account account;
        private String address;
        private String roadAddress;
        private String city;
        private String country;
        private Integer zipCode;
    }


}

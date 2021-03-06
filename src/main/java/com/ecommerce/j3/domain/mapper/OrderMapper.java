package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.controller.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class OrderMapper implements DefaultMapper<Order, OrderDto.OrderApiRequest, OrderDto.OrderApiResponse> {

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "orderItemIds", target = "orderItems")
    @Override
    public abstract Order toEntity(OrderDto.OrderApiRequest dto);


    @Override
    public abstract OrderDto.OrderApiResponse toApiResponse(Order order);

    @Override
    public Order updateFromDto(@MappingTarget Order entity, OrderDto.OrderApiRequest dto) {
        if (dto == null) return null;
        Order db = entity;
        Order req = toEntity(dto);
        entity = Order.builder()
                .ordersId(db.getOrdersId())
                .account(db.getAccount())
                .orderItems(req.getOrderItems())
                .sessionId(req.getSessionId() != "" ? req.getSessionId() : req.getSessionId())
                .token(req.getToken() != "" ? req.getToken() : req.getToken())
                .status(req.getStatus() != null ? req.getStatus() : req.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : req.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : req.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : req.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : req.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : req.getUserDiscount())
                .grandTotal(req.getGrandTotal())
                .firstName(req.getFirstName() != "" ? req.getFirstName() : req.getFirstName())
                .lastName(req.getLastName() != "" ? req.getLastName() : req.getLastName())
                .phoneNumber(req.getPhoneNumber() != "" ? req.getPhoneNumber() : req.getPhoneNumber())
                .email(req.getEmail() != "" ? req.getEmail() : req.getEmail())
                .roadAddress(req.getRoadAddress() != "" ? req.getRoadAddress() : req.getRoadAddress())
                .address(req.getAddress() != "" ? req.getAddress() : req.getAddress())
                .city(req.getCity() != "" ? req.getCity() : req.getCity())
                .province(req.getProvince() != "" ? req.getProvince() : req.getProvince())
                .country(req.getCountry() != "" ? req.getCountry() : req.getCountry())
                .zipCode(req.getZipCode() != null ? req.getZipCode() : req.getZipCode())
                .content(req.getContent() != "" ? req.getContent() : req.getContent())
                .build();
        return entity;
    }


    public Order testUpdateFromDto(@MappingTarget Order entity, OrderDto.OrderApiRequest dto) {
        if (dto == null) return null;
        Order db = entity;
        Order req = toEntity(dto);
        entity = Order.builder()
                .ordersId(db.getOrdersId())
                .account(db.getAccount())
                .orderItems(req.getOrderItems())
                .sessionId(req.getSessionId() != "" ? req.getSessionId() : req.getSessionId())
                .token(req.getToken() != "" ? req.getToken() : req.getToken())
                .status(req.getStatus() != null ? req.getStatus() : req.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : req.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : req.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : req.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : req.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : req.getUserDiscount())
                .grandTotal(req.getGrandTotal())
                .firstName(req.getFirstName() != "" ? req.getFirstName() : req.getFirstName())
                .lastName(req.getLastName() != "" ? req.getLastName() : req.getLastName())
                .phoneNumber(req.getPhoneNumber() != "" ? req.getPhoneNumber() : req.getPhoneNumber())
                .email(req.getEmail() != "" ? req.getEmail() : req.getEmail())
                .roadAddress(req.getRoadAddress() != "" ? req.getRoadAddress() : req.getRoadAddress())
                .address(req.getAddress() != "" ? req.getAddress() : req.getAddress())
                .city(req.getCity() != "" ? req.getCity() : req.getCity())
                .province(req.getProvince() != "" ? req.getProvince() : req.getProvince())
                .country(req.getCountry() != "" ? req.getCountry() : req.getCountry())
                .zipCode(req.getZipCode() != null ? req.getZipCode() : req.getZipCode())
                .content(req.getContent() != "" ? req.getContent() : req.getContent())
                .build();
        return entity;
    }

}

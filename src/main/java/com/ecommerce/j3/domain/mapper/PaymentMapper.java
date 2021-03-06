package com.ecommerce.j3.domain.mapper;


import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiRequest;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiResponse;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class PaymentMapper implements DefaultMapper<Payment, PaymentApiRequest, PaymentApiResponse> {

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "orderId", target = "order")
    @Override
    public abstract Payment toEntity(PaymentApiRequest dto);

    @Override
    public abstract PaymentApiResponse toApiResponse(Payment entity);

    @Override
    public Payment updateFromDto(@MappingTarget Payment entity, PaymentApiRequest dto) {
        if (dto == null) return null;
        // TODO: 구현 해야함, account mapper 참조

        return null;
    }
}

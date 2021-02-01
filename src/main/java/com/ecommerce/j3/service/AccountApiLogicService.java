package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.api.CrudInterface;
import com.ecommerce.j3.domain.entity.Account;

import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;

import com.ecommerce.j3.repository.AccountRepository;
import org.springframework.stereotype.Service;

import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountApiLogicService implements CrudInterface<AccountApiRequest, AccountApiResponse> {

    // 1. request data
    // 2. account 생성
    // 3. 생성된 데이터 -> return AccountApiResponse

    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AccountMapper accountMapper;


    @Override
//    public BodyData<AccountApiResponse> create(BodyData<AccountApiRequest> request) {
    public BodyData<AccountApiResponse> create(AccountApiRequest request) {

        // 1. request data
//        AccountApiRequest accountApiRequest = request.getData();
        AccountApiRequest accountApiRequest = request;
        // 2. account 생성
//        Account account = Account.builder().
//                accountId(accountApiRequest.getAccountId()).
//                email(accountApiRequest.getEmail()).
//                passwordHash(accountApiRequest.getPasswordHash()).
//                firstName(accountApiRequest.getFirstName()).
//                lastName(accountApiRequest.getLastName()).
//                gender(accountApiRequest.getGender()).
//                birthday(accountApiRequest.getBirthday()).
//                phoneNumber(accountApiRequest.getPhoneNumber()).
//                registeredAt(accountApiRequest.getRegisteredAt()).
//                lastLogin(accountApiRequest.getLastLogin()).
//                accountType(accountApiRequest.getAccountType()).
//                build();

        Account account = accountMapper.toEntity(request);
//        Account newAccount = accountRepository.save(account);
        accountRepository.save(account);
        // 3. 생성된 데이터 -> return AccountApiResponse
//        return response(newAccount);
        return response(account);
    }

    @Override
    public BodyData<AccountApiResponse> read(Long id) {
        // 1. id -> repository getOne / getById
        Optional<Account> optional = accountRepository.findById(id);
        // 2. return account -> accountApiResponse
        return optional.map(account -> response(account))
                .orElseGet(() -> BodyData.ERROR("데이터 없음"));

    }

    public BodyData<AccountApiResponse> readByEmail(String email) {
        Account account = accountRepository.findByEmail(email).orElseThrow(()->new RuntimeException("cannot find"));
        AccountApiResponse accountApiResponse = AccountApiResponse.builder().
                accountId(account.getAccountId()).
                email(account.getEmail()).
                build();
        return response(account);
    }

    @Override
//    public BodyData<AccountApiResponse> update(BodyData<AccountApiRequest> request) {
    public BodyData<AccountApiResponse> update(AccountApiRequest request) {
        // 1. data 생성
//        AccountApiRequest accountApiRequest = request.getData();
        AccountApiRequest accountApiRequest = request;
        // 2. id -> account 찾고
        Account accountFromDB = accountRepository
                .findById(accountApiRequest.getAccountId())
                .orElseThrow(()->new RuntimeException("cannot find"));

        // 3. update

        accountMapper.updateFromDto(accountFromDB, accountApiRequest);
        accountRepository.save(accountFromDB);
        return response(accountFromDB);
    }

    @Override
    public BodyData delete(Long id) {
        Optional<Account> optional = accountRepository.findById(id);

        // 2. repository -> delete
        return optional.map(account -> {
            accountRepository.delete(account);
            return BodyData.OK();

        }).orElseGet(() -> BodyData.ERROR("데이터 없음"));
    }

    private BodyData<AccountApiResponse> response(Account account) {
        AccountApiResponse accountApiResponse = AccountApiResponse.builder().
                accountId(account.getAccountId()).
                email(account.getEmail()).
                passwordHash(account.getPasswordHash()).
                firstName(account.getFirstName()).
                lastName(account.getLastName()).
                gender(account.getGender()).
                birthday(account.getBirthday()).
                phoneNumber(account.getPhoneNumber()).
                registeredAt(account.getRegisteredAt()).
                lastLogin(account.getLastLogin()).
                accountType(account.getAccountType()).
                build();

        return BodyData.OK(accountApiResponse);
    }

}

package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.api.CrudInterface;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;

import com.ecommerce.j3.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class AccountApiLogicService implements CrudInterface<AccountApiRequest, AccountApiResponse> {
    // AccountRepository bean 등록
    private AccountRepository accountRepository;
    public AccountApiLogicService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    // Response 전달
    private BodyData<AccountApiResponse> response(Account account){
        AccountApiResponse accountApiResponse = AccountApiResponse.builder()
                .email(account.getEmail())
                .passwordHash(account.getPasswordHash())
                .lastLogin(account.getLastLogin())
                .accountType(account.getAccountType())
                .build();

        return BodyData.OK(accountApiResponse);
    }

    // 1. request data
    // 2. account 생성
    // 3. 생성된 데이터 -> return AccountApiResponse

    // Create
    @Override
    public BodyData<AccountApiResponse> create(BodyData<AccountApiRequest> request) {

        // 1. request data
        AccountApiRequest accountApiRequest = request.getData();

        // 2. account 생성
        Account account = Account.builder().
                accountId(accountApiRequest.getAccountId()).
                email(accountApiRequest.getEmail()).
                passwordHash(accountApiRequest.getPasswordHash()).
                firstName(accountApiRequest.getFirstName()).
                lastName(accountApiRequest.getLastName()).
                gender(accountApiRequest.getGender()).
                birthday(accountApiRequest.getBirthday()).
                phoneNumber(accountApiRequest.getPhoneNumber()).
                registeredAt(accountApiRequest.getRegisteredAt()).
                lastLogin(accountApiRequest.getLastLogin()).
                accountType(accountApiRequest.getAccountType()).
                build();

        Account newAccount = accountRepository.save(account);

        // 3. 생성된 데이터 -> return AccountApiResponse
        return response(newAccount);
    }

    @Override
    @Valid
    public BodyData<AccountApiResponse> read(Long id) {
        // 1. id -> repository getOne / getById
        Optional<Account> optional = accountRepository.findById(id);

        // 2. return account -> accountApiResponse
        return optional.map(account -> response(account))
                .orElseGet(()-> BodyData.ERROR("데이터 없음"));

    }

    @Override
    public BodyData<AccountApiResponse> update(BodyData<AccountApiRequest> request) {

        // 1. data 생성
        AccountApiRequest accountApiRequest = request.getData();
        // 2. id -> account 찾고
        Optional<Account> optional = accountRepository.findById(accountApiRequest.getAccountId());

        return optional.map(account -> {
            // 3. update
            account
                    .setEmail(accountApiRequest.getEmail())
                    .setPasswordHash(accountApiRequest.getPasswordHash())
                    .setFirstName(accountApiRequest.getFirstName())
                    .setLastName(accountApiRequest.getLastName())
                    .setGender(accountApiRequest.getGender())
                    .setBirthday(accountApiRequest.getBirthday())
                    .setPhoneNumber(accountApiRequest.getPhoneNumber())
//                    .setRegisteredAt(accountApiRequest.getRegisteredAt())
//                    .setLastLogin(accountApiRequest.getLastLogin())
                    .setAccountType(accountApiRequest.getAccountType())
            ;
            return account;
        })
                .map(account -> accountRepository.save(account))
                .map(updateAccount -> response(updateAccount))
                .orElseGet(() -> BodyData.ERROR("데이터 없음"));
    }
    @Override
    public BodyData delete(Long id) {
        Optional<Account> optional = accountRepository.findById(id);

        // 2. repository -> delete
        return optional.map(account -> {
            accountRepository.delete(account);
            return BodyData.OK();

        })
                .orElseGet(()-> BodyData.ERROR("데이터 없음"));
    }

}

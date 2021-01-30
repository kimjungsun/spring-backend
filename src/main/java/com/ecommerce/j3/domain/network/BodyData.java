package com.ecommerce.j3.domain.network;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 생성자 생성을 위한 어노테이션

public class BodyData<T> {
    // api 결과
    private String result;

    // api 응답코드
    private String statusCode;

    // api 부가설명
    private String description;

    private T data;

    // OK
    public static <T> BodyData<T> OK() {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .result("OK")
                .statusCode("200")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> BodyData<T> OK(T data) {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .result("OK")
                .statusCode("200")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> BodyData<T> ERROR(String description) {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .result("ERROR")
                .statusCode("403")
                .description(description)
                .build();
    }
}

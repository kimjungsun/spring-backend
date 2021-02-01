package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)    // 01-18 Megan
    public List<OrderItem> orderItems ;


    private String sessionId;

    private String token;

    @Column(columnDefinition = "enum('READY', 'ORDER', 'CANCEL')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer itemPriceTotal;

    private Integer itemDiscount;

    private Integer tax;

    private Integer shipping;

    private Integer userDiscount;

    private Integer grandTotal;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String roadAddress;;

    private String address;

    private String city;

    private String province;

    private String country;

    private Integer zipCode;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

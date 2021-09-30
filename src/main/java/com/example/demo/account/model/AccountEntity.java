package com.example.demo.account.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Account")
@Table(name = "account")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private BigDecimal plnAccount;

    @Column
    private BigDecimal usdAccount;


}

package com.cinema.service.domain.entity;

import com.cinema.service.domain.enums.DiscountTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    Long id;
    String uuid;
    BigDecimal pricePaid;
    @Enumerated(EnumType.STRING)
    DiscountTypeEnum discountType;
}

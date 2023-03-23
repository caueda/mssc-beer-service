package com.devueda.msscbeerservice.web.model;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    @Null
    private UUID id;
    @Null
    private Integer version;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;

    @NotNull
    @Size(min = 5, max = 100)
    private String beerName;
    private BeerStyleEnum beerStyleEnum;
    @Positive
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;
}

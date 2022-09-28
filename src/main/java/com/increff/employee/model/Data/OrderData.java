package com.increff.employee.model.Data;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
public class OrderData{
    @NotNull
    private Long id;

    private ZonedDateTime time;
    private Boolean isPlaced;
}

package com.increff.employee.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class InfoData {

    private String message="No Message";
    private String email="No Email";

}

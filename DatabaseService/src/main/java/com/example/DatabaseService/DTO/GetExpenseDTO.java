package com.example.DatabaseService.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExpenseDTO {


    private Long id;
    private Double amount;
    private String categoryName;
    private String notes;
    private Date date;


    public GetExpenseDTO(Double amount, String categoryName, String notes, Date date) {
        this.amount = amount;
        this.categoryName = categoryName;
        this.notes = notes;
        this.date = date;
    }


}

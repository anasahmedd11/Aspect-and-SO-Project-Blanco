package com.example.DatabaseService.DTO;

import com.example.DatabaseService.entity.Expenses;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriesDTO {

    private Long id;
    private String name;
    private List<Expenses> expenses;

}

package com.example.APIManagerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    private Long id;

    private String name;

    public Categories(String name) {
        this.name = name;
    }
}

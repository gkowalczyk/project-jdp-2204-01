package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupDto {
    private Long id;
    private String groupName;
    private ProductDto productDto;
}

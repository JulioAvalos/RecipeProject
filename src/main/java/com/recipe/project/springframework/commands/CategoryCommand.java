package com.recipe.project.springframework.commands;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}

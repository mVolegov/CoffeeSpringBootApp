package com.mvoleg.coffeespringbootapp.api.dto.menucategory;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о категориях меню")
public class MenuCategoryDTO {

    @Schema(description = "Идентификатор категории меню")
    private Long id;

    @Schema(description = "Название категории меню")
    private String name;

    public MenuCategoryDTO() {}

    public MenuCategoryDTO(String name) {
        this.name = name;
    }

    public MenuCategoryDTO(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

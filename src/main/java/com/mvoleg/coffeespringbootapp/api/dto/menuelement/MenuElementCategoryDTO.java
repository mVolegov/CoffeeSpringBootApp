package com.mvoleg.coffeespringbootapp.api.dto.menuelement;

public class MenuElementCategoryDTO {

    private Long menuElementId;
    private Long menuCategoryId;

    public MenuElementCategoryDTO() {
    }

    public MenuElementCategoryDTO(Long menuElementId) {
        this.menuElementId = menuElementId;
    }

    public Long getMenuElementId() {
        return menuElementId;
    }

    public void setMenuElementId(Long menuElementId) {
        this.menuElementId = menuElementId;
    }

    public Long getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Long menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }
}

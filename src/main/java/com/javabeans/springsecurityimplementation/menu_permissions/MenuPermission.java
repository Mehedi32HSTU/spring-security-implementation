package com.javabeans.springsecurityimplementation.menu_permissions;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_permission")
public class MenuPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long menuId;

    public MenuPermission() {
    }
    public MenuPermission(long id, long userId, long menuId) {
        this.id = id;
        this.userId = userId;
        this.menuId = menuId;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }
}

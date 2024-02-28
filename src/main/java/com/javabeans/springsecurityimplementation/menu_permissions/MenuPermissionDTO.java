package com.javabeans.springsecurityimplementation.menu_permissions;

import java.util.ArrayList;
import java.util.List;

public class MenuPermissionDTO {
    private long userId;
    private List<Long> menuIds = new ArrayList<>();
    public MenuPermissionDTO() {
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public List<Long> getMenuIds() {
        return menuIds;
    }
    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
    @Override
    public String toString() {
        return "MenuPermissionDTO{" +
                "userId=" + userId +
                ", menuIds=" + menuIds +
                '}';
    }
}

package com.javabeans.springsecurityimplementation.menu_permissions;

import java.util.ArrayList;
import java.util.List;

public class MenuPermissionUpdateDeleteDTO {
    private long userId;
    private List<Long> addNewMenuIds = new ArrayList<>();
    private List<Long> deleteMenuIds = new ArrayList<>();

    public MenuPermissionUpdateDeleteDTO() {
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public List<Long> getAddNewMenuIds() {
        return addNewMenuIds;
    }
    public void setAddNewMenuIds(List<Long> addNewMenuIds) {
        this.addNewMenuIds = addNewMenuIds;
    }
    public List<Long> getDeleteMenuIds() {
        return deleteMenuIds;
    }
    public void setDeleteMenuIds(List<Long> deleteMenuIds) {
        this.deleteMenuIds = deleteMenuIds;
    }
    @Override
    public String toString() {
        return "MenuPermissionUpdateDeleteDTO{" +
                "userId=" + userId +
                ", addNewMenuIds=" + addNewMenuIds +
                ", deleteMenuIds=" + deleteMenuIds +
                '}';
    }
}

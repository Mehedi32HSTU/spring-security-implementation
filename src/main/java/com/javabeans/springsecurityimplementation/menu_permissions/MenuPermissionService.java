package com.javabeans.springsecurityimplementation.menu_permissions;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuPermissionService {
    public ResponseEntity<?> addMenuPermission(MenuPermissionDTO menuPermissionDTO);
    public ResponseEntity<?> getMenuPermission(long userId);
    public ResponseEntity<?> getAllMenuPermission();
    public ResponseEntity<?> updateMenuPermission(long userId, MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO);
    public ResponseEntity<?> deleteMenuPermission(long userId, MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO);

}

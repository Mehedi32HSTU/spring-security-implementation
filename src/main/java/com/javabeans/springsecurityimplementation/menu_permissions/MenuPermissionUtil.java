package com.javabeans.springsecurityimplementation.menu_permissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuPermissionUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Map<Long, List<Long>> menuPermissionMap = new HashMap<>();
    private MenuPermissionRepository menuPermissionRepository;

    public MenuPermissionUtil(MenuPermissionRepository menuPermissionRepository) {
        this.menuPermissionRepository = menuPermissionRepository;
    }

    /**
     * This method should get called only at service start time.
     */
    public void loadMenuPermission() {
        try {
            logger.info("loadMenuPermission method called.");
            List<MenuPermission> allMenuPermissions = menuPermissionRepository.findAll();
            allMenuPermissions.forEach(menuPermission -> {
                List<Long> menuIds = menuPermissionMap.getOrDefault(menuPermission.getUserId(), new ArrayList<>());
                menuIds.add(menuPermission.getMenuId());
                menuPermissionMap.put(menuPermission.getUserId(), menuIds);
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception {} has occurred in loadMenuPermission method.", e.getMessage());
        }
    }
    /**
     * This method should get called whenever a users menu permission is get updated.
     * Operations like: add, update, delete menu permissions
     */
    public void loadMenuPermission(long userId) {
        try {
            logger.info("loadMenuPermission method called.");
            List<MenuPermission> allMenuPermissions = menuPermissionRepository.findByUserId(userId);
            menuPermissionMap.put(userId, new ArrayList<>());
            allMenuPermissions.forEach(menuPermission -> {
                List<Long> menuIds = menuPermissionMap.getOrDefault(menuPermission.getUserId(), new ArrayList<>());
                if(!menuIds.contains(menuPermission.getMenuId()))
                    menuIds.add(menuPermission.getMenuId());
                menuPermissionMap.put(menuPermission.getUserId(), menuIds);
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception {} has occurred in loadMenuPermission method.", e.getMessage());
        }
    }

    public void insertMenuPermission(long userId, List<Long> menuIds) {
        try {
            logger.info("insertMenuPermission method called.");
            List<MenuPermission> allMenuPermissions = new ArrayList<>();
            menuIds.forEach(menuId -> {
                MenuPermission menuPermission = new MenuPermission();
                menuPermission.setMenuId(menuId);
                menuPermission.setUserId(userId);
                allMenuPermissions.add(menuPermission);
            });
            menuPermissionRepository.saveAll(allMenuPermissions);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception {} has occurred in insertMenuPermission method.", e.getMessage());
        }
    }
}

package com.javabeans.springsecurityimplementation.menu_permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu-permission")
public class MenuPermissionController {
    @Autowired
    private MenuPermissionService menuPermissionService;

    @PreAuthorize("@securityService.hasMenuPermission(@MenuIds.ADD_MENU_PERMISSION)")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMenuPermission(@RequestBody MenuPermissionDTO menuPermissionDTO) {
        return menuPermissionService.addMenuPermission(menuPermissionDTO);
    }
    @PreAuthorize("@securityService.isSelfById(#userId) or @securityService.hasMenuPermission(@MenuIds.GET_MENU_PERMISSION)")
    @RequestMapping(value = "/{userId}/get-menu", method = RequestMethod.GET)
    public ResponseEntity<?> getMenuPermission(@PathVariable(name = "userId") long userId) {
        return menuPermissionService.getMenuPermission(userId);
    }
    @PreAuthorize("@securityService.hasMenuPermission(@MenuIds.GET_ALL_MENU_PERMISSION)")
    @RequestMapping(value = "/get-all-menu", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMenuPermission() {
        return menuPermissionService.getAllMenuPermission();
    }
    @PreAuthorize("@securityService.hasMenuPermission(@MenuIds.UPDATE_MENU_PERMISSION)")
    @RequestMapping(value = "/{userId}/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMenuPermission(@PathVariable(name = "userId") long userId,
            @RequestBody MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO) {
        return menuPermissionService.updateMenuPermission(userId, menuPermissionUpdateDeleteDTO);
    }
    @PreAuthorize("@securityService.hasMenuPermission(@MenuIds.DELETE_MENU_PERMISSION)")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMenuPermission(@PathVariable(name = "userId") long userId,
           @RequestBody MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO) {
        return menuPermissionService.deleteMenuPermission(userId, menuPermissionUpdateDeleteDTO);
    }
}

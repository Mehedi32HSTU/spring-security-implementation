package com.javabeans.springsecurityimplementation.menu_permissions;

import com.javabeans.springsecurityimplementation.constants_and_enums.MenuIdContainer;
import com.javabeans.springsecurityimplementation.request_response.MessageResponse;
import com.javabeans.springsecurityimplementation.users.UserInfo;
import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuPermissionServiceImpl implements MenuPermissionService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuPermissionRepository menuPermissionRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ResponseEntity<?> addMenuPermission(MenuPermissionDTO menuPermissionDTO) {
        try {
            logger.info("addMenuPermission method called.");
            if(Objects.isNull(menuPermissionDTO)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Empty request body."));
            } else if (menuPermissionDTO.getMenuIds().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Empty menu id list."));
            }
            UserInfo userInfo = userInfoRepository.findById(menuPermissionDTO.getUserId()).orElse(null);
            if(Objects.isNull(userInfo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found with id"));
            List<MenuPermission> allNewMenuPermission = new ArrayList<>();
            List<Long> allMenuIds = MenuIdContainer.allMenuIds;
            for (Long menuId : menuPermissionDTO.getMenuIds()) {
                if(allMenuIds.contains(menuId)) {
                    MenuPermission menuPermission = new MenuPermission();
                    menuPermission.setUserId(menuPermissionDTO.getUserId());
                    menuPermission.setMenuId(menuId);
                    allNewMenuPermission.add(menuPermission);
                }
            }
            if(!allNewMenuPermission.isEmpty())
                menuPermissionRepository.saveAll(allNewMenuPermission);
            new MenuPermissionUtil(menuPermissionRepository).loadMenuPermission(userInfo.getId());
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Menu permissions added successfully."));
        } catch (Exception e) {
            return exceptionResponse(e, "addMenuPermission");
        }
    }

    @Override
    public ResponseEntity<?> getMenuPermission(long userId) {
        try {
            logger.info("getMenuPermission method called.");
            UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
            if(Objects.isNull(userInfo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found with id"));
            List<Long> menuIds = MenuPermissionUtil.menuPermissionMap
                    .getOrDefault(userId, new ArrayList<>());
            MenuPermissionDTO menuPermissionDTO = new MenuPermissionDTO();
            menuPermissionDTO.setUserId(userId);
            menuPermissionDTO.setMenuIds(menuIds);
            return ResponseEntity.status(HttpStatus.OK).body(menuPermissionDTO);
        } catch (Exception e) {
            return exceptionResponse(e, "getMenuPermission");
        }
    }
    public ResponseEntity<?> getAllMenuPermission() {
        try {
            logger.info("getAllMenuPermission method called.");
            return ResponseEntity.status(HttpStatus.OK).body(MenuPermissionUtil.menuPermissionMap);
        } catch (Exception e) {
            return exceptionResponse(e, "getAllMenuPermission");
        }
    }

    @Override
    public ResponseEntity<?> updateMenuPermission(long userId, MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO) {
        try {
            logger.info("updateMenuPermission method called.");
            if(Objects.isNull(menuPermissionUpdateDeleteDTO)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Empty request body."));
            }
            UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
            if(Objects.isNull(userInfo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found with id"));

            if(!menuPermissionUpdateDeleteDTO.getDeleteMenuIds().isEmpty()) {

                menuPermissionRepository.deleteMenuPermission(userId, menuPermissionUpdateDeleteDTO.getDeleteMenuIds());
            }

            if(!menuPermissionUpdateDeleteDTO.getAddNewMenuIds().isEmpty()) {
                List<MenuPermission> allNewMenuPermission = new ArrayList<>();
                List<Long> allMenuIds = MenuIdContainer.allMenuIds;
                for (Long menuId : menuPermissionUpdateDeleteDTO.getAddNewMenuIds()) {
                    if(allMenuIds.contains(menuId)) {
                        MenuPermission menuPermission = new MenuPermission();
                        menuPermission.setUserId(userId);
                        menuPermission.setMenuId(menuId);
                        allNewMenuPermission.add(menuPermission);
                    }
                }
                if(!allNewMenuPermission.isEmpty())
                    menuPermissionRepository.saveAll(allNewMenuPermission);
            }
            new MenuPermissionUtil(menuPermissionRepository).loadMenuPermission(userId);

            List<Long> menuIds = MenuPermissionUtil.menuPermissionMap
                    .getOrDefault(userId, new ArrayList<>());
            MenuPermissionDTO menuPermissionDTO = new MenuPermissionDTO();
            menuPermissionDTO.setUserId(userId);
            menuPermissionDTO.setMenuIds(menuIds);
            return ResponseEntity.status(HttpStatus.OK).body(menuPermissionDTO);
        } catch (Exception e) {
            return exceptionResponse(e, "updateMenuPermission");
        }
    }

    @Override
    public ResponseEntity<?> deleteMenuPermission(long userId, MenuPermissionUpdateDeleteDTO menuPermissionUpdateDeleteDTO) {
        try {
            logger.info("deleteMenuPermission method called.");
            if(Objects.isNull(menuPermissionUpdateDeleteDTO)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Empty request body."));
            }
            UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
            if(Objects.isNull(userInfo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found with id"));

            if(!menuPermissionUpdateDeleteDTO.getDeleteMenuIds().isEmpty()) {
                menuPermissionRepository.deleteMenuPermission(userId, menuPermissionUpdateDeleteDTO.getDeleteMenuIds());
            }
            new MenuPermissionUtil(menuPermissionRepository).loadMenuPermission(userId);

            List<Long> menuIds = MenuPermissionUtil.menuPermissionMap
                    .getOrDefault(userId, new ArrayList<>());
            MenuPermissionDTO menuPermissionDTO = new MenuPermissionDTO();
            menuPermissionDTO.setUserId(userId);
            menuPermissionDTO.setMenuIds(menuIds);
            return ResponseEntity.status(HttpStatus.OK).body(menuPermissionDTO);
        } catch (Exception e) {
            return exceptionResponse(e, "deleteMenuPermission");
        }
    }

    private ResponseEntity<?> exceptionResponse(Exception e, String methodName) {
        e.printStackTrace();
        logger.error("Exception {} has occurred in {} method.", e.getMessage(), methodName);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Exception "+e.getMessage()+ "has occurred."));
    }
}

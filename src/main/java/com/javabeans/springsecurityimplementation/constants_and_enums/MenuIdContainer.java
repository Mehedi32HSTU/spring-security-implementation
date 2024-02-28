package com.javabeans.springsecurityimplementation.constants_and_enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component("MenuIds")
public class MenuIdContainer {
    private static Logger logger = LoggerFactory.getLogger(MenuIdContainer.class);
    public static final List<Long> allMenuIds = getAllMenuIds();
    //NOTE: Private constructor to prevent instantiation
    private MenuIdContainer() {}
    public static List<Long> getAllMenuIds() {
        List<Long> menuIds = new ArrayList<>();
        Field[] fields = MenuIdContainer.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(long.class) && java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    menuIds.add(field.getLong(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    logger.error("Exception {} has occurred in getAllMenuIds method.", e.getMessage());
                }
            }
        }
        return menuIds;
    }

    public static final long GET_ADMIN_INFO = 100000001L;
    public static final long GET_USER_INFO = 100000002L;

    public static final long ADD_USER = 100010001L;
    public static final long GET_USER = 100010002L;
    public static final long GET_ALL_USER = 100010003L;
    public static final long UPDATE_USER = 100010004L;
    public static final long DELETE_USER = 100010005L;

    public static final long ADD_MENU_PERMISSION = 100020001L;
    public static final long GET_MENU_PERMISSION = 100020002L;
    public static final long UPDATE_MENU_PERMISSION = 100020003L;
    public static final long DELETE_MENU_PERMISSION = 100020004L;
    public static final long GET_ALL_MENU_PERMISSION = 100020005L;


}

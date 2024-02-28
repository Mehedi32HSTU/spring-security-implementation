package com.javabeans.springsecurityimplementation.menu_permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Long> {
    @Modifying
    @Query(value = "delete from menu_permission where user_id=?1 and menu_id in (?2)", nativeQuery = true)
    @Transactional
    public void deleteMenuPermission(long userId, List<Long> menuIds);
    public List<MenuPermission> findByUserId(long userId);
}

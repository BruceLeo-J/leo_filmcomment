package com.graduation.filmcomment.shiro.realms;

import com.graduation.filmcomment.entity.LeoAdmin;
import com.graduation.filmcomment.entity.LeoPermission;
import com.graduation.filmcomment.entity.LeoRole;
import com.graduation.filmcomment.service.AdminService;
import com.graduation.filmcomment.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取身份信息
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();
        //根据主身份信息获取用户对象
        LeoAdmin leoAdmin = adminService.findUserByUserName(primaryPrincipal);
        //根据用户id获取所有角色
        LeoAdmin leoAdminAndRoles = adminService.findRolesById(leoAdmin.getLeoAdminId());

        if(!CollectionUtils.isEmpty(leoAdminAndRoles.getLeoRoles())){

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (LeoRole leoRole : leoAdminAndRoles.getLeoRoles()) {
                System.out.println(leoRole.getLeoRoleName());
                //添加角色
                simpleAuthorizationInfo.addRole(leoRole.getLeoRoleName());

                if(!CollectionUtils.isEmpty(leoRole.getLeoPermissions())){
                    for (LeoPermission leoPermission : leoRole.getLeoPermissions()) {
                        System.out.println(leoPermission);
                        //添加权限
                        simpleAuthorizationInfo.addStringPermission(leoPermission.getLeoPermissionName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据身份信息，查询用户
        String principal = (String) token.getPrincipal();
        LeoAdmin leoAdmin =  adminService.findUserByUserName(principal);
        if (!ObjectUtils.isEmpty(leoAdmin)){
            return new SimpleAuthenticationInfo(leoAdmin.getLeoAdminUsername(),leoAdmin.getLeoAdminPassword(), ByteSource.Util.bytes(leoAdmin.getLeoAdminSalt()),this.getName());
        }
        return null;
    }
}

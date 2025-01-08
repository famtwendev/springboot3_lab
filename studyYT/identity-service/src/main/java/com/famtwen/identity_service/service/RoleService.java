package com.famtwen.identity_service.service;

import com.famtwen.identity_service.dto.request.ApiResponse;
import com.famtwen.identity_service.dto.request.PermissionRequest;
import com.famtwen.identity_service.dto.request.RoleRequest;
import com.famtwen.identity_service.dto.response.PermissionResponse;
import com.famtwen.identity_service.dto.response.RoleResponse;
import com.famtwen.identity_service.entity.Permission;
import com.famtwen.identity_service.mapper.PermissionMapper;
import com.famtwen.identity_service.mapper.RoleMapper;
import com.famtwen.identity_service.repository.PermissionRepository;
import com.famtwen.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;


    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
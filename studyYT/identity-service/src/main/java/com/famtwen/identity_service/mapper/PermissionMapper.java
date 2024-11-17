package com.famtwen.identity_service.mapper;

import com.famtwen.identity_service.dto.request.PermissionRequest;
import com.famtwen.identity_service.dto.response.PermissionResponse;
import com.famtwen.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
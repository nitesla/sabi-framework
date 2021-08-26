package com.sabi.framework.dto.requestDto;

import lombok.Data;

@Data
public class RoleDto {

    private Long id;
    private String name;
    private String status;
    private String scope;
    private Boolean isActive;

    public RoleDto(Long id, String name, String status, String scope, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.scope = scope;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", scope='" + scope + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

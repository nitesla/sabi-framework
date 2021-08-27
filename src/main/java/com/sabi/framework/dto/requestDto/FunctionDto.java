package com.sabi.framework.dto.requestDto;

public class FunctionDto {

    private Long id;
    private String name;
    private String status;
    private String description;
    private boolean isActive;
    private String code;

    public FunctionDto(Long id, String name, String status, String description, boolean isActive, String code) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.isActive = isActive;
        this.code = code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FunctionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", code='" + code + '\'' +
                '}';
    }
}

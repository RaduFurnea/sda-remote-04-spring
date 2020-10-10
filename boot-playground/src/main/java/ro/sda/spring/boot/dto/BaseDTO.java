package ro.sda.spring.boot.dto;

import java.time.LocalDateTime;

public abstract class BaseDTO {

    protected Long id;

    protected LocalDateTime createdTime;

    protected LocalDateTime lastModifiedTime;

    public BaseDTO(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public BaseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}

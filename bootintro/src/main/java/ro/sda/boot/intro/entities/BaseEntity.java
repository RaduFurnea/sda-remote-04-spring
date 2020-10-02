package ro.sda.boot.intro.entities;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private LocalDateTime lastUpdateTime;

    @PrePersist
    private void prePersist() {
        this.createdTime = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastUpdateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }


}

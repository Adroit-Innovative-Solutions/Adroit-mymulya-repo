package com.mymulya.globaltables.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "global_users")
public class GlobalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String domain;    // Domain (e.g., dataqinc.com)
    private String organization;  // Organization (e.g., dataquad, adroit)
    private String schemaName;   // Name of the schema for the organization
    private String tableName;    // Name of the table for user data

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Automatically set the creation date/time

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

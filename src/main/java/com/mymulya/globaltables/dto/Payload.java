package com.mymulya.globaltables.dto;

public class Payload {
    private String userId;
    private String userName;
    private String email;
    private String roleType;
    private String organization;
    private String loginTimestamp;

    public Payload(String userId, String userName, String email, String roleType, String organization, String loginTimestamp) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.roleType = roleType;
        this.organization = organization;
        this.loginTimestamp = loginTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(String loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }
// Getters and Setters
    // (Or use Lombok @Data if preferred)
}

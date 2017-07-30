package com.bing.ddup.model.dto;

/**
 * Shiro登录认证后的保存的user信息
 */
public class CurUser {
    private Integer uid;
    private String uname;
    private String username;
    private Integer userType;
    private Integer status;

    public CurUser() {}

    public CurUser(Integer uid, String uname, String username, Integer userType, Integer status) {
        this.uid = uid;
        this.uname = uname;
        this.username = username;
        this.userType = userType;
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

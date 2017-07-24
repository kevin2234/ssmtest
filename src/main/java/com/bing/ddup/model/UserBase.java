package com.bing.ddup.model;

public class UserBase {
    private Integer uid;

    private Byte registerSource;

    private String userName;

    private String nickName;

    private Boolean gender;

    private Long birthday;

    private String signature;

    private String mobile;

    private Integer mobileBindTime;

    private String email;

    private Integer emailBindTime;

    private String face;

    private String face200;

    private String srcface;

    private Integer createTime;

    private Integer updateTime;

    private String pushToken;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Byte registerSource) {
        this.registerSource = registerSource;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getMobileBindTime() {
        return mobileBindTime;
    }

    public void setMobileBindTime(Integer mobileBindTime) {
        this.mobileBindTime = mobileBindTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getEmailBindTime() {
        return emailBindTime;
    }

    public void setEmailBindTime(Integer emailBindTime) {
        this.emailBindTime = emailBindTime;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    public String getFace200() {
        return face200;
    }

    public void setFace200(String face200) {
        this.face200 = face200 == null ? null : face200.trim();
    }

    public String getSrcface() {
        return srcface;
    }

    public void setSrcface(String srcface) {
        this.srcface = srcface == null ? null : srcface.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken == null ? null : pushToken.trim();
    }
}
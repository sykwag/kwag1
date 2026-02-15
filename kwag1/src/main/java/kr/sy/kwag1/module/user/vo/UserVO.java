package kr.sy.kwag1.module.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 사용자 VO (Value Object)
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "사용자 ID")
    private Long userId;

    @ApiModelProperty(value = "테넌트 ID")
    private String tenantId;

    @ApiModelProperty(value = "로그인 ID")
    private String userLoginId;

    @ApiModelProperty(value = "사용자명")
    private String userName;

    @ApiModelProperty(value = "이메일")
    private String userEmail;

    @ApiModelProperty(value = "비밀번호 (입력 시에만 사용)")
    private String userPassword;

    @ApiModelProperty(value = "역할 (ADMIN/USER)")
    private String userRole;

    @ApiModelProperty(value = "상태 (ACTIVE/INACTIVE/LOCKED)")
    private String userStatus;

    @ApiModelProperty(value = "사용여부 (Y/N)")
    private String useYn;

    @ApiModelProperty(value = "마지막 로그인 일시")
    private Date lastLoginDate;

    @ApiModelProperty(value = "로그인 실패 횟수")
    private Integer loginFailCount;

    @ApiModelProperty(value = "등록일시")
    private Date regDate;

    @ApiModelProperty(value = "등록자")
    private String regUser;

    @ApiModelProperty(value = "수정일시")
    private Date updDate;

    @ApiModelProperty(value = "수정자")
    private String updUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(Integer loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", tenantId='" + tenantId + '\'' +
                ", userLoginId='" + userLoginId + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}

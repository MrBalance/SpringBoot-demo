package com.mrbalance.model;

import java.io.Serializable;

/**
 * usr_table
 * @author 
 */
public class UsrTable implements Serializable {
    /**
     * 用户id
     */
    private Integer usrId;

    /**
     * 用户账号
     */
    private String usrAccount;

    /**
     * 用户名称
     */
    private String usrName;

    /**
     * 用户密码
     */
    private String usrPassword;

    /**
     * 是否有效（0：无效；1：有效）
     */
    private Integer isActive;

    private static final long serialVersionUID = 1L;

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrAccount() {
        return usrAccount;
    }

    public void setUsrAccount(String usrAccount) {
        this.usrAccount = usrAccount;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UsrTable other = (UsrTable) that;
        return (this.getUsrId() == null ? other.getUsrId() == null : this.getUsrId().equals(other.getUsrId()))
            && (this.getUsrAccount() == null ? other.getUsrAccount() == null : this.getUsrAccount().equals(other.getUsrAccount()))
            && (this.getUsrName() == null ? other.getUsrName() == null : this.getUsrName().equals(other.getUsrName()))
            && (this.getUsrPassword() == null ? other.getUsrPassword() == null : this.getUsrPassword().equals(other.getUsrPassword()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUsrId() == null) ? 0 : getUsrId().hashCode());
        result = prime * result + ((getUsrAccount() == null) ? 0 : getUsrAccount().hashCode());
        result = prime * result + ((getUsrName() == null) ? 0 : getUsrName().hashCode());
        result = prime * result + ((getUsrPassword() == null) ? 0 : getUsrPassword().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", usrId=").append(usrId);
        sb.append(", usrAccount=").append(usrAccount);
        sb.append(", usrName=").append(usrName);
        sb.append(", usrPassword=").append(usrPassword);
        sb.append(", isActive=").append(isActive);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package org.wso2.claimMgt.utills;

public class ConfigurationBean {


    private String hostName;
    private String httpsPort;
    private String adminUserName;
    private String adminPassword;
    private String secondaryUserStoreDomain;
    private String tenantDomain;
    private boolean runOnTenant;
    private String claimUri;
    private String claimValue;
    private String profileName;
    private String userName;

    public boolean isRunOnTenant() {
        return runOnTenant;
    }

    public void setRunOnTenant(boolean runOnTenant) {
        this.runOnTenant = runOnTenant;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHttpsPort() {
        return httpsPort;
    }

    public void setHttpsPort(String httpsPort) {
        this.httpsPort = httpsPort;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getSecondaryUserStoreDomain() {
        return secondaryUserStoreDomain;
    }

    public void setSecondaryUserStoreDomain(String secondaryUserStoreDomain) {
        this.secondaryUserStoreDomain = secondaryUserStoreDomain;
    }

    public String getTenantDomain() {
        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {
        this.tenantDomain = tenantDomain;
    }


    public String getClaimUri() {
        return claimUri;
    }

    public void setClaimUri(String claimUri) {
        this.claimUri = claimUri;
    }

    public String getClaimValue() {
        return claimValue;
    }

    public void setClaimValue(String claimValue) {
        this.claimValue = claimValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}

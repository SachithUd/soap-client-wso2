package org.wso2.claimMgt;

public class ConfigurationBean {


    private String hostName;
    private String httpsPort;
    private String adminUserName;
    private String adminPassword;
    private String secondaryUserStoreDomain;
    private String tenantDomain;
    private boolean runOnTenant;

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


}

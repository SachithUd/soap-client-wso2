package org.wso2.claimMgt;

import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.claimMgt.utills.LoginUtill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {


    protected String role1;
    protected String role2;
    protected String role2NewName;

    protected String user1;
    protected String user1Pwd;
    protected String user2;// for role1
    protected String user2Pwd;
    protected String user2NewPwd;
    protected String user3;// for role2
    protected String user3Pwd;
    protected String user4;// for role2
    protected String user4Pwd;
    protected String user4NewPwd;
    protected String user5;// for role1 and role2
    protected String user5Pwd;

    protected String claimUri;
    protected String claimValue;

    //claim values of user2
    protected String claimURI1;
    protected String claimValue1;
    protected String claimURI2;
    protected String claimValue2;
    protected String claimURI3;
    protected String claimValue3;
    ConfigurationBean configuration;


    public String init() throws IOException, LoginAuthenticationExceptionException {
        configuration = new ConfigurationBean();
        this.setSSLParam();
        this.readConfigProperties();
        this.setTestData();
        LoginUtill loginUtill = new LoginUtill(this.getBackendUrl());
        return loginUtill.login(configuration.getAdminUserName(), configuration.getAdminPassword(), configuration.getHostName());
    }

    private void setSSLParam() {
        System.setProperty("javax.net.ssl.trustStore", System.getProperty("resource.location") + "products/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
    }

    protected String getBackendUrl() {
        if (configuration.isRunOnTenant()) {
            return "https://" + configuration.getHostName() + ":" + configuration.getHttpsPort() + "/t/test.org/services/";
        } else {
            return "https://" + configuration.getHostName() + ":" + configuration.getHttpsPort() + "/services/";
        }
    }

    private void readConfigProperties() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream(System.getProperty("resource.location") + "configuration.properties");
        prop.load(input);
        configuration.setAdminUserName(prop.getProperty("admin.user.name"));
        configuration.setAdminPassword(prop.getProperty("admin.user.password"));
        configuration.setHostName(prop.getProperty("remote.host.name"));
        configuration.setHttpsPort(prop.getProperty("remote.port.https"));
        configuration.setSecondaryUserStoreDomain(prop.getProperty("secondery.userstore.domain"));
        configuration.setTenantDomain(prop.getProperty("tenant.domain"));
        configuration.setRunOnTenant(Boolean.valueOf(prop.getProperty("test.run.on.tenant")));
    }

    public ConfigurationBean getConfiguration() {
        return configuration;
    }
    private void setTestData() {
        role1 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestRoley1";
        role2 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestRoley2";
        role2NewName = configuration.getSecondaryUserStoreDomain() + "/testNewNameRole2";

        user1 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestUsery1";// for role1
        user1Pwd = "testPassword1";
        user2 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestUsery2";// for role1
        user2Pwd = "testPassword2";
        user2NewPwd = "testNewPassword2";
        user3 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestUsery3";// for role2
        user3Pwd = "testPassword3";
        user4 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestUsery4";// for role2
        user4Pwd = "testPassword4";
        user4NewPwd = "testNewPassword4";
        user5 = configuration.getSecondaryUserStoreDomain() + "/UserStoreTestUsery5";// for role1 and role2
        user5Pwd = "testPassword5";

        claimUri = "http://wso2.org/claims/mail";
        claimValue = "WSO2";

        //claim values of user2
        claimURI1 = "http://wso2.org/claims/nickname";
        claimValue1 = "testCaseUser1";
        claimURI2 = "http://wso2.org/claims/country";
        claimValue2 = "testCaseUserCountry";
        claimURI3 = "http://wso2.org/claims/stateorprovince";
        claimValue3 = "western";
    }
}

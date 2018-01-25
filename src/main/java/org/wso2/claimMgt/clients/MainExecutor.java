package org.wso2.claimMgt.clients;


import org.apache.axis2.AxisFault;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.claimMgt.utills.ConfigurationBean;
import org.wso2.claimMgt.utills.LoginUtill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainExecutor {
    protected static String role1;
    protected static String role2;
    protected static String role2NewName;

    protected static String user1;
    protected static String user1Pwd;
    protected static String user2;// for role1
    protected static String user2Pwd;
    protected static String user2NewPwd;
    protected static String user3;// for role2
    protected static String user3Pwd;
    protected static String user4;// for role2
    protected static String user4Pwd;
    protected static String user4NewPwd;
    protected static String user5;// for role1 and role2
    protected static String user5Pwd;

    protected static String claimUri;
    protected static String claimValue;

    //claim values of user2
    protected static String claimURI1;
    protected static String claimValue1;
    protected static String claimURI2;
    protected static String claimValue2;
    protected static String claimURI3;
    protected static String claimValue3;
    public static ConfigurationBean configuration;
    public static String cookie;

    public static void main(String[] args) throws IOException, LoginAuthenticationExceptionException {
//        ClaimManagementServiceClient claimManagementServiceClient = new ClaimManagementServiceClient(backendUrl, sessionCookie);
        configuration = new ConfigurationBean();
        setSSLParam();
        readConfigProperties();
        setTestData();
        LoginUtill loginUtill = new LoginUtill(getBackendUrl());
        cookie = loginUtill.login(configuration.getAdminUserName(), configuration.getAdminPassword(), configuration.getHostName());
    }

    private static void setSSLParam() {
        System.setProperty("javax.net.ssl.trustStore", System.getProperty("resource.location") + "products/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
    }

    private static String getBackendUrl() {
        if (configuration.isRunOnTenant()) {
            return "https://" + configuration.getHostName() + ":" + configuration.getHttpsPort() + "/t/test.org/services/";
        } else {
            return "https://" + configuration.getHostName() + ":" + configuration.getHttpsPort() + "/services/";
        }
    }

    private static void readConfigProperties() throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(System.getProperty("resource.location") + "configuration.properties");
        prop.load(input);
        configuration.setAdminUserName(prop.getProperty("admin.user.name"));
        configuration.setAdminPassword(prop.getProperty("admin.user.password"));
        configuration.setHostName(prop.getProperty("remote.host.name"));
        configuration.setHttpsPort(prop.getProperty("remote.port.https"));
        configuration.setSecondaryUserStoreDomain(prop.getProperty("secondery.userstore.domain"));
        configuration.setTenantDomain(prop.getProperty("tenant.domain"));
        configuration.setRunOnTenant(Boolean.valueOf(prop.getProperty("test.run.on.tenant")));
    }

    private static void setTestData() {
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

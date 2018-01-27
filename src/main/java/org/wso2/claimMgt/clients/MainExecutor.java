package org.wso2.claimMgt.clients;

import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.carbon.um.ws.api.stub.RemoteUserStoreManagerServiceUserStoreExceptionException;
import org.wso2.claimMgt.utills.ConfigurationBean;
import org.wso2.claimMgt.utills.LoginUtill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainExecutor {
    public static ConfigurationBean configuration;
    public static String cookie;

    public static void main(String[] args) throws IOException, LoginAuthenticationExceptionException,
            RemoteUserStoreManagerServiceUserStoreExceptionException {
        configuration = new ConfigurationBean();
        setSSLParam();
        readConfigProperties();
        LoginUtill loginUtill = new LoginUtill(getBackendUrl());
        cookie = loginUtill.login(configuration.getAdminUserName(), configuration.getAdminPassword(),
                configuration.getHostName());
        RemoteUserStoreManagerServiceClient remoteUserStoreManagerServiceClient =
                new RemoteUserStoreManagerServiceClient(getBackendUrl(), cookie);
        remoteUserStoreManagerServiceClient.setUserClaimValue(configuration.getUserName(), configuration.getClaimUri(),
                configuration.getClaimValue(), configuration.getProfileName());

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
        configuration.setClaimUri(prop.getProperty("claim.uri"));
        configuration.setClaimValue(prop.getProperty("claim.value"));
        configuration.setUserName(prop.getProperty("user.name"));
        configuration.setProfileName(prop.getProperty("profile.name"));
    }
}

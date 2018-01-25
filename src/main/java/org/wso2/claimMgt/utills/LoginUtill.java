package org.wso2.claimMgt.utills;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ServiceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.carbon.authenticator.stub.LogoutAuthenticationExceptionException;

import java.rmi.RemoteException;

public class LoginUtill {

    private static final Log log = LogFactory.getLog(LoginUtill.class);
    private AuthenticationAdminStub authenticationAdminStub;

    public LoginUtill(String backendUrl) throws AxisFault {
        String serviceName = "AuthenticationAdmin";
        String endPoint = backendUrl + serviceName;
        this.authenticationAdminStub = new AuthenticationAdminStub(endPoint);
    }


    public String login(String userName, String password, String host) throws LoginAuthenticationExceptionException, RemoteException {
        Boolean loginStatus = authenticationAdminStub.login(userName, password, host);
        if (!loginStatus.booleanValue()) {
            throw new LoginAuthenticationExceptionException("Login Unsuccessful. Return false as a login status by Server");
        } else {
            log.info("Login Successful");
            ServiceContext serviceContext = authenticationAdminStub._getServiceClient().getLastOperationContext().getServiceContext();
            String sessionCookie = (String) serviceContext.getProperty("Cookie");
            return sessionCookie;
        }
    }

    public void logout() throws LogoutAuthenticationExceptionException, RemoteException {
        authenticationAdminStub.logout();
    }

}

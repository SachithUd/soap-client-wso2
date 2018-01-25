package org.wso2.claimMgt.clients;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.claim.mgt.stub.ClaimManagementServiceClaimManagementException;
import org.wso2.carbon.claim.mgt.stub.ClaimManagementServiceStub;
import org.wso2.carbon.claim.mgt.stub.dto.ClaimDTO;
import org.wso2.carbon.claim.mgt.stub.dto.ClaimDialectDTO;
import org.wso2.carbon.claim.mgt.stub.dto.ClaimMappingDTO;
import java.rmi.RemoteException;

public class ClaimManagementServiceClient {
    private static Log log = LogFactory.getLog(ClaimManagementServiceClient.class);

    private final String serviceName = "ClaimManagementService";
    private ClaimManagementServiceStub claimManagementServiceStub;
    private String endPoint;

    public ClaimManagementServiceClient(String backEndUrl, String sessionCookie) throws AxisFault {
        this.endPoint = backEndUrl + serviceName;
        claimManagementServiceStub = new ClaimManagementServiceStub(endPoint);
        AuthenticateStub.authenticateStub(sessionCookie, claimManagementServiceStub);
    }

    public ClaimManagementServiceClient(String backEndUrl, String userName, String password)
            throws AxisFault {
        this.endPoint = backEndUrl + serviceName;
        claimManagementServiceStub = new ClaimManagementServiceStub(endPoint);
        AuthenticateStub.authenticateStub(userName, password, claimManagementServiceStub);
    }

    public void addNewClaimMapping(String dialectURI, String claimUri, String description, String mappedAttribute)
            throws ClaimManagementServiceClaimManagementException, RemoteException {
        ClaimMappingDTO claimMappingDTO = new ClaimMappingDTO();

        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setDialectURI(dialectURI);
        claimDTO.setClaimUri(claimUri);
        claimDTO.setDescription(description);

        claimMappingDTO.setClaim(claimDTO);
        claimMappingDTO.setMappedAttribute(mappedAttribute);
        claimManagementServiceStub.addNewClaimMapping(claimMappingDTO);

    }

    public void removeClaimMapping(String dialectURI, String claimURI)
            throws RemoteException, Exception {
        try {
            claimManagementServiceStub.removeClaimMapping(dialectURI, claimURI);
        } catch (RemoteException e) {
            throw new RemoteException("Unable to remove claim Mapping ", e);
        }
    }

    public ClaimDialectDTO[] getClaimMappings() throws RemoteException, Exception {
        try {
            return claimManagementServiceStub.getClaimMappings();
        } catch (RemoteException e) {
            throw new RemoteException("Error while getting claim mappings ", e);
        }
    }

    public ClaimDialectDTO getClaimMappingByDialect(String dialect)
            throws RemoteException, Exception {
        try {
            return claimManagementServiceStub.getClaimMappingByDialect(dialect);
        } catch (RemoteException e) {
            throw new RemoteException("Unable while getting claim Mapping by dialect", e);
        }
    }

    public void addNewClaimDialect(ClaimDialectDTO claimDialectDTO)
            throws RemoteException, Exception {
        try {
            claimManagementServiceStub.addNewClaimDialect(claimDialectDTO);
        } catch (RemoteException e) {
            throw new RemoteException("Unable to add new claim dialect", e);
        }
    }

    public void addNewClaimMapping(ClaimMappingDTO claimMappingDTO)
            throws RemoteException, Exception {
        try {
            claimManagementServiceStub.addNewClaimMapping(claimMappingDTO);
        } catch (RemoteException e) {
            throw new RemoteException("Unable to add new claim Mapping", e);
        }
    }

    public void updateClaimMapping(ClaimMappingDTO claimMappingDTO)
            throws RemoteException, Exception {
        try {
            claimManagementServiceStub.upateClaimMapping(claimMappingDTO);
        } catch (RemoteException e) {
            throw new RemoteException("Unable to update claim Mapping", e);
        }
    }

    public void removeClaimDialect(String dialectURI)
            throws RemoteException, Exception {
        try {
            claimManagementServiceStub.removeClaimDialect(dialectURI);
        } catch (RemoteException e) {
            throw new RemoteException("Unable to remove claim dialect", e);
        }
    }
}

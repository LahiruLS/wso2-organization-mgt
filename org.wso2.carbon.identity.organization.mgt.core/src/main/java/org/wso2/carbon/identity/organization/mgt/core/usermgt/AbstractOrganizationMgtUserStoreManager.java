package org.wso2.carbon.identity.organization.mgt.core.usermgt;

import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.ldap.UniqueIDReadWriteLDAPUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;

import java.util.Map;

/**
 * Abstract Organization Mgt UserStore Manager.
 */
public abstract class AbstractOrganizationMgtUserStoreManager extends UniqueIDReadWriteLDAPUserStoreManager {

    public AbstractOrganizationMgtUserStoreManager() {
    }

    public AbstractOrganizationMgtUserStoreManager(RealmConfiguration realmConfig, Map<String, Object> properties,
                                                   ClaimManager claimManager,
                                                   ProfileConfigurationManager profileManager, UserRealm realm,
                                                   Integer tenantId)
            throws UserStoreException {

        super(realmConfig, properties, claimManager, profileManager, realm, tenantId);
    }

    public AbstractOrganizationMgtUserStoreManager(RealmConfiguration realmConfig, ClaimManager claimManager,
                                                   ProfileConfigurationManager profileManager)
            throws UserStoreException {

        super(realmConfig, claimManager, profileManager);
    }

    /**
     * This method creates a subDirectory in the LDAP.
     *
     * @param dn
     * @param rdn
     * @throws UserStoreException
     */
    public abstract void createOu(String dn, String rdn) throws UserStoreException;

    /**
     * This method removes the provided subdirectory from the LDAP.
     * Subdirectory should be empty. (No users nor leaf OUs)
     *
     * @param dn
     * @throws UserStoreException
     */
    public abstract void deleteOu(String dn) throws UserStoreException;
}

/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.organization.mgt.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.organization.mgt.core.constant.OrganizationMgtConstants;
import org.wso2.carbon.identity.organization.mgt.core.exception.OrganizationManagementClientException;
import org.wso2.carbon.identity.organization.mgt.core.exception.OrganizationManagementException;
import org.wso2.carbon.identity.organization.mgt.core.exception.OrganizationManagementServerException;
import org.wso2.carbon.identity.organization.mgt.core.internal.OrganizationMgtDataHolder;
import org.wso2.carbon.identity.organization.mgt.core.model.Organization;
import org.wso2.carbon.identity.organization.mgt.core.model.OrganizationAdd;
import org.wso2.carbon.user.api.UserRealm;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.api.UserStoreManager;

import java.util.StringJoiner;
import java.util.UUID;

import static org.wso2.carbon.identity.organization.mgt.core.constant.OrganizationMgtConstants.ErrorMessages.ERROR_CODE_USER_STORE_ACCESS_ERROR;
import static org.wso2.carbon.identity.organization.mgt.core.constant.SQLConstants.MAX_QUERY_LENGTH_IN_BYTES_SQL;

/**
 * This class provides utility functions for the Organization Management.
 */
public class Utils {

    private static final Log log = LogFactory.getLog(Utils.class);

    public static OrganizationManagementClientException handleClientException(
            OrganizationMgtConstants.ErrorMessages error, String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new OrganizationManagementClientException(message, error.getCode());
    }

    public static OrganizationManagementServerException handleServerException(
            OrganizationMgtConstants.ErrorMessages error, String data, Throwable e) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return new OrganizationManagementServerException(message, error.getCode(), e);
    }

    public static String generateUniqueID() {

        return UUID.randomUUID().toString();
    }

    public static String getLdapRootDn() {

        //TODO implement logic.
        return null;
    }

    public static void logOrganizationAddObject(OrganizationAdd organizationAdd) {

        if (!log.isDebugEnabled()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        StringJoiner attributesJoiner = new StringJoiner(",");
        StringJoiner configJoiner = new StringJoiner(",");
        sb.append("Logging OrganizationAdd object");
        sb.append("\nName : " + organizationAdd.getName());
        sb.append("\nDescription : " + organizationAdd.getDescription());
        sb.append("\nParentId : " + organizationAdd.getParentId());
        // user store configs and attributes cannot be null
        organizationAdd.getAttributes().forEach(entry ->
                attributesJoiner.add(entry.toString())
        );
        sb.append("\nAttributes : " + attributesJoiner.toString());
        organizationAdd.getUserStoreConfigs().forEach(entry ->
                configJoiner.add(entry.toString())
        );
        sb.append("\nUser Store Configs : ");
        log.debug(sb.toString());
    }

    public static void logOrganizationObject(Organization organization) {

        if (!log.isDebugEnabled()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Logging Organization object");
        sb.append("\nId : " + organization.getId());
        sb.append("\nName : " + organization.getName());
        sb.append("\nTenantId : " + organization.getTenantId());
        sb.append("\nParentId : " + organization.getParentId());
        sb.append("\nActive : " + organization.isActive());
        sb.append("\nCreated Time : " + organization.getCreated());
        sb.append("\nLast Modified Time : " + organization.getLastModified());
        sb.append("\nUser Store Configs : ");
        StringJoiner configJoiner = new StringJoiner(",");
        organization.getUserStoreConfigs().entrySet().stream().forEach(
                entry -> configJoiner.add(entry.getValue().toString())
        );
        sb.append(configJoiner.toString());
        sb.append("\nAttributes : ");
        StringJoiner attributeJoiner = new StringJoiner(",");
        organization.getAttributes().entrySet().stream().forEach(
                entry -> attributeJoiner.add(entry.getValue().toString())
        );
        sb.append(attributeJoiner.toString());
        log.debug(sb.toString());
    }

    public static int getMaximumQueryLengthInBytes() {

        return StringUtils.isBlank(MAX_QUERY_LENGTH_IN_BYTES_SQL)
                ? 4194304 : Integer.parseInt(MAX_QUERY_LENGTH_IN_BYTES_SQL);
    }

    public static String getUserStoreType(String userStoreDomain) throws OrganizationManagementException {

        int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        try {
            UserRealm userRealm = OrganizationMgtDataHolder.getInstance().getRealmService()
                    .getTenantUserRealm(tenantId);
            UserStoreManager userStoreManager = (UserStoreManager) userRealm.getUserStoreManager();
        } catch (UserStoreException e) {
            throw handleServerException(ERROR_CODE_USER_STORE_ACCESS_ERROR,
                    "Error obtaining user realm for the tenant " + tenantId, e);
        }
        return null;
    }
}

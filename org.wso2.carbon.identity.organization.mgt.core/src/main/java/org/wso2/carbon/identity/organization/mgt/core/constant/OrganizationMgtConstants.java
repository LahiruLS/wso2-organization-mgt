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

package org.wso2.carbon.identity.organization.mgt.core.constant;

/**
 * Organization Mgt Constants.
 */
public class OrganizationMgtConstants {

    public static final String ROOT = "ROOT";
    public static final String USER_STORE_DOMAIN = "USER_STORE_DOMAIN";
    public static final String RDN = "RDN";
    public static final String DN = "DN";
    public static final String PRIMARY = "PRIMARY";
    public static final String DN_PLACE_HOLDER = "ou=%s,";
    public static final String SCIM2_USER_RESOURCE_BASE_PATH = "/t/%s/scim2/Users/%s";
    public static final String ORGANIZATION_RESOURCE_BASE_PATH =
            "/t/%s/api/identity/organization-mgt/v1.0/organizations/%s";
    public static final String ACCOUNT_DISABLED_CLAIM_URI = "http://wso2.org/claims/identity/accountDisabled";

    public static final String PATCH_OP_ADD = "add";
    public static final String PATCH_OP_REMOVE = "remove";
    public static final String PATCH_OP_REPLACE = "replace";
    public static final String PATCH_PATH_ORG_NAME = "/name";
    public static final String PATCH_PATH_ORG_DISPLAY_NAME = "/displayName";
    public static final String PATCH_PATH_ORG_DESCRIPTION = "/description";
    public static final String PATCH_PATH_ORG_STATUS = "/status";
    public static final String PATCH_PATH_ORG_PARENT_ID = "/parent/id";
    public static final String PATCH_PATH_ORG_ATTRIBUTES = "/attributes/";

    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_NAME = "name";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_DISPLAY_NAME = "displayName";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_DESCRIPTION = "description";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_STATUS = "status";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_PARENT_ID = "parentId";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_PARENT_NAME = "parentName";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_PARENT_DISPLAY_NAME = "parentDisplayName";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_CREATED = "created";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_LAST_MODIFIED = "lastModified";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_CREATED_BY_ID = "createdBy";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_LAST_MODIFIED_BY_ID = "lastModifiedBy";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_ATTRIBUTE_KEY = "attributeKey";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_ATTRIBUTE_VALUE = "attributeValue";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_USER_STORE_CONFIG_KEY = "userStoreConfigKey";
    public static final String ORGANIZATION_SEARCH_BEAN_FIELD_USER_STORE_CONFIG_VALUE = "userStoreConfigValue";

    // Permission strings
    public static final String PERMISSION = "/permission";
    public static final String ADMIN_PERMISSION = "/permission/admin";
    public static final String ADMIN_MANAGE_PERMISSION = "/permission/admin/manage";
    public static final String ADMIN_MANAGE_IDENTITY_PERMISSION = "/permission/admin/manage/identity";
    // organization permissions
    public static final String ORGANIZATION_BASE_PERMISSION = "/permission/admin/manage/identity/organizationmgt";
    public static final String ORGANIZATION_ADMIN_PERMISSION =
            "/permission/admin/manage/identity/organizationmgt/admin";
    public static final String ORGANIZATION_CREATE_PERMISSION =
            "/permission/admin/manage/identity/organizationmgt/create";
    public static final String ORGANIZATION_VIEW_PERMISSION = "/permission/admin/manage/identity/organizationmgt/view";
    public static final String ORGANIZATION_UPDATE_PERMISSION =
            "/permission/admin/manage/identity/organizationmgt/update";
    public static final String ORGANIZATION_DELETE_PERMISSION =
            "/permission/admin/manage/identity/organizationmgt/delete";
    // User permissions
    public static final String USER_MGT_BASE_PERMISSION = "/permission/admin/manage/identity/usermgt";
    public static final String USER_MGT_CREATE_PERMISSION = "/permission/admin/manage/identity/usermgt/create";
    public static final String USER_MGT_VIEW_PERMISSION = "/permission/admin/manage/identity/usermgt/view";
    public static final String USER_MGT_LIST_PERMISSION = "/permission/admin/manage/identity/usermgt/list";
    public static final String USER_MGT_UPDATE_PERMISSION = "/permission/admin/manage/identity/usermgt/update";
    public static final String USER_MGT_DELETE_PERMISSION = "/permission/admin/manage/identity/usermgt/delete";
    // Role permissions
    public static final String ROLE_MGT_BASE_PERMISSION = "/permission/admin/manage/identity/rolemgt";
    public static final String ROLE_MGT_CREATE_PERMISSION = "/permission/admin/manage/identity/rolemgt/create";
    public static final String ROLE_MGT_VIEW_PERMISSION = "/permission/admin/manage/identity/rolemgt/view";
    public static final String ROLE_MGT_UPDATE_PERMISSION = "/permission/admin/manage/identity/rolemgt/update";
    public static final String ROLE_MGT_DELETE_PERMISSION = "/permission/admin/manage/identity/rolemgt/delete";
    // User role management permissions
    public static final String USER_ROLE_MGT_BASE_PERMISSION = "/permission/admin/manage/identity/userrolemgt";
    public static final String USER_ROLE_MGT_VIEW_PERMISSION = "/permission/admin/manage/identity/userrolemgt/view";
    public static final String USER_ROLE_MGT_CREATE_PERMISSION = "/permission/admin/manage/identity/userrolemgt/create";
    public static final String USER_ROLE_MGT_DELETE_PERMISSION = "/permission/admin/manage/identity/userrolemgt/delete";
    public static final String USER_ROLE_MGT_UPDATE_PERMISSION = "/permission/admin/manage/identity/userrolemgt/update";

    public static final String UI_EXECUTE = "ui.execute";

    /**
     *  <IS_HOME>/repository/resources/conf/templates/repository/conf/identity/identity.xml.j2
     *
     *     <!--Organization management properties-->
     *     <OrganizationMgt>
     *         <OrgNameClaimUri>{{organization.mgt.org_name_claim_uri}}</OrgNameClaimUri>
     *         <OrgIdClaimUri>{{organization.mgt.org_id_claim_uri}}</OrgIdClaimUri>
     *         <filterUsersByOrganizationName>{{organization.mgt.filter_by_org_name}}</filterUsersByOrganizationName>
     *     </OrganizationMgt>
     *
     *  <IS_HOME>>/repository/conf/deployment.toml
     *
     *      [organization.mgt]
     *      org_name_claim_uri = "http://wso2.org/claims/organizationName"
     *      org_id_claim_uri = "http://wso2.org/claims/organizationId"
     *      filter_by_org_name = "false"
     */
    // Organization mgt claim details
    public static final String ORGANIZATION_ID_CLAIM_URI = "OrganizationMgt.OrgIdClaimUri";
    public static final String ORGANIZATION_ID_DEFAULT_CLAIM_URI = "http://wso2.org/claims/organizationId";
    public static final String ORGANIZATION_NAME_CLAIM_URI = "OrganizationMgt.OrgNameClaimUri";
    public static final String ORGANIZATION_NAME_DEFAULT_CLAIM_URI = "http://wso2.org/claims/organization";
    public static final String FILTER_USERS_BY_ORG_NAME = "OrganizationMgt.filterUsersByOrganizationName";

    /**
     * Error Messages.
     */
    public enum ErrorMessages {

        // Client errors (ORGMGT_00001-ORGMGT_00049)
        ERROR_CODE_INVALID_ORGANIZATION_ADD_REQUEST("ORGMGT_00001", "Invalid organization add request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_IMPORT_REQUEST("ORGMGT_00002", "Invalid organization import request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_GET_REQUEST("ORGMGT_00003", "Invalid organization search/get request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_GET_BY_ID_REQUEST("ORGMGT_00004",
                "Invalid get organization by ID request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_PATCH_REQUEST("ORGMGT_00005", "Invalid organization patch request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_DELETE_REQUEST("ORGMGT_00006", "Invalid organization delete request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_CHILDREN_GET_REQUEST("ORGMGT_00007",
                "Invalid get child organizations request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_CONFIG_GET_REQUEST("ORGMGT_00008",
                "Invalid get organization configs request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_CONFIG_PATCH_REQUEST("ORGMGT_00009",
                "Invalid patch organization config request : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_USER_STORE_CONFIGURATIONS("ORGMGT_00010",
                "Invalid user store configurations : %s"),
        ERROR_CODE_SQL_QUERY_LIMIT_EXCEEDED("ORGMGT_00011", "Request caused an SQL query limit exceed : %s"),
        ERROR_CODE_INVALID_ORGANIZATION_GET_ID_BY_NAME_REQUEST("ORGMGT_00012",
                "Invalid get organization Id by name request : %s"),
        ERROR_CODE_INVALID_EVENT("ORGMGT_00013", "Invalid event : %s"),
        ERROR_CODE_UNAUTHORIZED_ACTION("ORGMGT_00014", "Unauthorized action : %s"),
        ERROR_CODE_INVALID_DATE_FORMAT("ORGMGT_00015", "Invalid search criteria : %s"),
        ERROR_CODE_CONFLICTING_REQUEST("ORGMGT_00016", "%s"),
        ERROR_CODE_RESOURCE_NOT_FOUND("ORGMGT_00017", "Resource not found : %s"),
        ERROR_CODE_UNCLASSIFIED_ERROR("ORGMGT_00019", "Error while processing : %s"),

        // Server errors (ORGMGT_00050-ORGMGT_00100)
        ERROR_CODE_ORGANIZATION_ADD_ERROR("ORGMGT_00050", "Error while creating the organization : %s"),
        ERROR_CODE_ORGANIZATION_IMPORT_ERROR("ORGMGT_00051", "Error while importing the organization : %s"),
        ERROR_CODE_ORGANIZATION_GET_ERROR("ORGMGT_00052", "Error while retrieving/searching the organizations : %s"),
        ERROR_CODE_ORGANIZATION_GET_BY_ID_ERROR("ORGMGT_00053", "Error while retrieving the organization : %s"),
        ERROR_CODE_ORGANIZATION_DELETE_ERROR("ORGMGT_00054", "Error while deleting the organization : %s"),
        ERROR_CODE_ORGANIZATION_PATCH_ERROR("ORGMGT_00055", "Error while patching the organization : %s"),
        ERROR_CODE_ORGANIZATION_GET_CHILDREN_ERROR("ORGMGT_00056",
                "Error while retrieving the child organizations : %s"),
        ERROR_CODE_ORGANIZATION_GET_CONFIGS_ERROR("ORGMGT_00057",
                "Error while retrieving the organization configs : %s"),
        ERROR_CODE_ORGANIZATION_PATCH_CONFIGS_ERROR("ORGMGT_00058",
                "Error while patching the organization configs : %s"),
        ERROR_CODE_CHECK_ORGANIZATION_EXIST_BY_ID_ERROR("ORGMGT_00059",
                "Error while checking if the organization id exist : %s"),
        ERROR_CODE_CHECK_ORGANIZATION_EXIST_BY_NAME_ERROR("ORGMGT_00060",
                "Error while checking if the organization name exist : %s"),
        ERROR_CODE_USER_STORE_CONFIGURATIONS_ERROR("ORGMGT_00061", "User store configurations error : %s"),
        ERROR_CODE_CHECK_ATTRIBUTE_EXIST_ERROR("ORGMGT_00062", "Error while checking if the attribute exist : %s"),
        ERROR_CODE_ORGANIZATION_GET_ID_BY_NAME_ERROR("ORGMGT_00063",
                "Error while retrieving organization Id by name : %s"),
        ERROR_CODE_EVENTING_ERROR("ORGMGT_00064", "Error while handling the event : %s"),
        ERROR_CODE_USER_STORE_OPERATIONS_ERROR("ORGMGT_00065", "Error accessing user store : %s"),
        ERROR_CODE_CHECK_RDN_AVAILABILITY_ERROR("ORGMGT_00066", "Error checking RDN availability : %s"),
        ERROR_CODE_USER_ROLE_ORG_AUTHORIZATION_ERROR("ORGMGT_00067", "Error while authorizing : %s"),
        ERROR_CODE_ORG_MGT_SERVER_CONFIG_ERROR("ORGMGT_00068",
                "Organization Management Server configuration error : %s"),
        ERROR_CODE_RETRIEVING_AUTHORIZED_ORGANIZATION_LIST_ERROR("ORGMGT_00069",
                "Error while retrieving authorized organizations list : %s"),
        ERROR_CODE_INITIALIZATION_ERROR("ORGMGT_00070", "Error while initializing the organization mgt component : %s"),

        ERROR_CODE_UNEXPECTED("ORGMGT_00101", "Unexpected Error");

        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return code;
        }

        public String getMessage() {

            return message;
        }
    }

    /**
     * Forbidden Error Messages
     */
    public enum ForbiddenErrorMessages {

        ORGMGT_00014;
    }

    /**
     * Not Found Error Messages
     */
    public enum NotFoundErrorMessages {

        ORGMGT_00004, ORGMGT_00017;
    }

    /**
     * Conflict Error Messages
     */
    public enum ConflictErrorMessages {

        ORGMGT_00016;
    }
}

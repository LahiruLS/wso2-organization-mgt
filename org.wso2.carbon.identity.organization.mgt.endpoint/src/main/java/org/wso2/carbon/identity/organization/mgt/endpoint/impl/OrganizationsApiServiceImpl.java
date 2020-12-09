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

package org.wso2.carbon.identity.organization.mgt.endpoint.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.wso2.carbon.identity.organization.mgt.core.exception.OrganizationManagementClientException;
import org.wso2.carbon.identity.organization.mgt.core.exception.OrganizationManagementException;
import org.wso2.carbon.identity.organization.mgt.core.model.Operation;
import org.wso2.carbon.identity.organization.mgt.core.model.Organization;
import org.wso2.carbon.identity.organization.mgt.core.model.OrganizationSearchBean;
import org.wso2.carbon.identity.organization.mgt.core.model.UserStoreConfig;
import org.wso2.carbon.identity.organization.mgt.endpoint.OrganizationsApiService;
import org.wso2.carbon.identity.organization.mgt.endpoint.dto.OperationDTO;
import org.wso2.carbon.identity.organization.mgt.endpoint.dto.OrganizationAddDTO;
import org.wso2.carbon.identity.organization.mgt.endpoint.dto.UserRoleMappingDTO;
import org.wso2.carbon.identity.organization.mgt.endpoint.dto.UserRoleOperationDTO;
import org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationUserRoleMgtEndpointUtil;
import org.wso2.carbon.identity.organization.user.role.mgt.core.exception.OrganizationUserRoleMgtClientException;
import org.wso2.carbon.identity.organization.user.role.mgt.core.exception.OrganizationUserRoleMgtException;
import org.wso2.carbon.identity.organization.user.role.mgt.core.model.Role;
import org.wso2.carbon.identity.organization.user.role.mgt.core.model.RoleMember;
import org.wso2.carbon.identity.organization.user.role.mgt.core.model.UserRoleMapping;
import org.wso2.carbon.identity.organization.user.role.mgt.core.model.UserRoleMappingUser;
import org.wso2.carbon.identity.organization.user.role.mgt.core.model.UserRoleOperation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.organization.mgt.core.constant.OrganizationMgtConstants.ErrorMessages.ERROR_CODE_INVALID_ORGANIZATION_GET_REQUEST;
import static org.wso2.carbon.identity.organization.mgt.core.util.Utils.handleClientException;
import static org.wso2.carbon.identity.organization.mgt.endpoint.constants.OrganizationMgtEndpointConstants.ORGANIZATION_PATH;
import static org.wso2.carbon.identity.organization.mgt.endpoint.constants.OrganizationMgtEndpointConstants.ORGANIZATION_ROLES_PATH;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getBasicOrganizationDTOFromOrganization;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getOrganizationAddFromDTO;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getOrganizationDTOFromOrganization;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getOrganizationDTOsFromOrganizations;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getOrganizationManager;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getOrganizationUserRoleManager;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getSearchCondition;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.getUserStoreConfigDTOsFromUserStoreConfigs;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.handleBadRequestResponse;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.handleServerErrorResponse;
import static org.wso2.carbon.identity.organization.mgt.endpoint.util.OrganizationMgtEndpointUtil.handleUnexpectedServerError;
import static org.wso2.carbon.identity.organization.user.role.mgt.core.constant.OrganizationUserRoleMgtConstants.ErrorMessages.ERROR_CODE_INVALID_USER_GET_REQUEST_FOR_ORG_ROLE;

/**
 * Organizations Api Service Impl.
 */
public class OrganizationsApiServiceImpl extends OrganizationsApiService {

    private static final Log log = LogFactory.getLog(OrganizationsApiServiceImpl.class);

    @Override
    public Response organizationsPost(OrganizationAddDTO organizationAddDTO) {

        try {
            Organization organization = getOrganizationManager()
                    .addOrganization(getOrganizationAddFromDTO(organizationAddDTO), false);
            return Response.created(getOrganizationResourceURI(organization))
                    .entity(getBasicOrganizationDTOFromOrganization(organization)).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsImportPost(OrganizationAddDTO organizationAddDTO) {

        try {
            Organization organization = getOrganizationManager()
                    .addOrganization(getOrganizationAddFromDTO(organizationAddDTO), true);
            return Response.created(getOrganizationResourceURI(organization))
                    .entity(getBasicOrganizationDTOFromOrganization(organization)).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsGet(SearchContext searchContext, Integer offset, Integer limit, String sortBy,
            String sortOrder, String attributes, Boolean includePermissions) {

        try {
            if ((limit != null && limit < 1) || (offset != null && offset < 0)) {
                throw handleClientException(ERROR_CODE_INVALID_ORGANIZATION_GET_REQUEST,
                        "Invalid pagination arguments. 'limit' should be greater than 0 and 'offset' should be "
                                + "greater than -1");
            }
            // If pagination parameters not defined in the request, set them to -1
            limit = (limit == null) ? Integer.valueOf(-1) : limit;
            offset = (offset == null) ? Integer.valueOf(-1) : offset;
            boolean permissionsReq = includePermissions != null ? includePermissions.booleanValue() : false;
            List<String> requestedAttributes = attributes == null ?
                    new ArrayList<>() :
                    Arrays.stream(attributes.split(",")).map(String::trim).collect(Collectors.toList());
            List<Organization> organizations = getOrganizationManager()
                    .getOrganizations(getSearchCondition(searchContext, OrganizationSearchBean.class), offset, limit,
                            sortBy, sortOrder, requestedAttributes, permissionsReq);
            return Response.ok().entity(getOrganizationDTOsFromOrganizations(organizations)).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdGet(String organizationId, Boolean includePermissions) {

        try {
            boolean permissionsReq = includePermissions != null ? includePermissions.booleanValue() : false;
            Organization organization = getOrganizationManager().getOrganization(organizationId, permissionsReq);
            return Response.ok().entity(getOrganizationDTOFromOrganization(organization)).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdChildrenGet(String organizationId) {

        try {
            return Response.ok().entity(getOrganizationManager().getChildOrganizationIds(organizationId)).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdDelete(String organizationId) {

        try {
            getOrganizationManager().deleteOrganization(organizationId);
            return Response.noContent().build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdPatch(String organizationId, List<OperationDTO> operations) {

        try {
            getOrganizationManager().patchOrganization(organizationId,
                    operations.stream().map(op -> new Operation(op.getOp(), op.getPath(), op.getValue()))
                            .collect(Collectors.toList()));
            return Response.noContent().build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdRolesPost(String organizationId, UserRoleMappingDTO userRoleMapping) {

        try {
            UserRoleMapping userRoleMapping1 = new UserRoleMapping(userRoleMapping.getRoleId(),
                    userRoleMapping.getUsers().stream().map(mapping ->
                            new UserRoleMappingUser(mapping.getUserId(), mapping.getIncludeSubOrgs()))
                            .collect(Collectors.toList()));
            getOrganizationUserRoleManager().addOrganizationUserRoleMappings(organizationId, userRoleMapping1);
            return Response.created(getOrganizationRoleResourceURI(organizationId)).build();
        } catch (OrganizationUserRoleMgtClientException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleBadRequestResponse(e, log);
        } catch (OrganizationUserRoleMgtException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return OrganizationUserRoleMgtEndpointUtil.handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdRolesRoleIdUsersGet(String organizationId, String roleId, Integer offset,
                                                                   Integer limit, String attributes, String filter) {

        try {
            if ((limit != null && limit < 1) || (offset != null && offset < 0)) {
                throw org.wso2.carbon.identity.organization.user.role.mgt.core.util.Utils
                        .handleClientException(ERROR_CODE_INVALID_USER_GET_REQUEST_FOR_ORG_ROLE,
                                "Invalid pagination arguments. 'limit' should be greater than 0 and 'offset' should be "
                                        + "greater than -1");
            }
            // If pagination parameters not defined in the request, set them to -1
            limit = (limit == null) ? Integer.valueOf(-1) : limit;
            offset = (offset == null) ? Integer.valueOf(-1) : offset;
            List<String> requestedAttributes = attributes == null ? new ArrayList<>() :
                    Arrays.stream(attributes.split(",")).map(String::trim).collect(Collectors.toList());
            if (!requestedAttributes.contains("userName")) {
                requestedAttributes.add("userName");
            }
            List<RoleMember> roleMembers = getOrganizationUserRoleManager()
                    .getUsersByOrganizationAndRole(organizationId, roleId, offset, limit, requestedAttributes, filter);
            return Response.ok()
                    .entity(roleMembers.stream().map(RoleMember::getUserAttributes).collect(Collectors.toList()))
                    .build();
        } catch (OrganizationUserRoleMgtClientException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleBadRequestResponse(e, log);
        } catch (OrganizationUserRoleMgtException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return OrganizationUserRoleMgtEndpointUtil.handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdRolesRoleIdUsersUserIdDelete(String organizationId, String roleId,
            String userId) {

        try {
            getOrganizationUserRoleManager()
                    .deleteOrganizationsUserRoleMapping(organizationId, userId, roleId, null, false, false);
            return Response.noContent().build();
        } catch (OrganizationUserRoleMgtClientException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleBadRequestResponse(e, log);
        } catch (OrganizationUserRoleMgtException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return OrganizationUserRoleMgtEndpointUtil.handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdRolesRoleIdUsersUserIdPatch(String organizationId, String roleId,
                                                                           String userId,
                                                                           List<UserRoleOperationDTO> operations) {

        try {
            getOrganizationUserRoleManager().patchOrganizationsUserRoleMapping(organizationId, roleId, userId,
                    operations.stream().map(op -> new UserRoleOperation(op.getOp(), op.getPath(), op.getValue()))
                            .collect(Collectors.toList()));
            return Response.noContent().build();
        } catch (OrganizationUserRoleMgtClientException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleBadRequestResponse(e, log);
        } catch (OrganizationUserRoleMgtException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return OrganizationUserRoleMgtEndpointUtil.handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdUsersUserIdRolesGet(String organizationId, String userId) {

        try {
            List<Role> roles = getOrganizationUserRoleManager().getRolesByOrganizationAndUser(organizationId, userId);
            return Response.ok().entity(roles).build();
        } catch (OrganizationUserRoleMgtClientException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleBadRequestResponse(e, log);
        } catch (OrganizationUserRoleMgtException e) {
            return OrganizationUserRoleMgtEndpointUtil.handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return OrganizationUserRoleMgtEndpointUtil.handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdUserstoreConfigsGet(String organizationId) {

        try {
            Map<String, UserStoreConfig> userStoreConfigs = getOrganizationManager()
                    .getUserStoreConfigs(organizationId);
            return Response.ok(getUserStoreConfigDTOsFromUserStoreConfigs(userStoreConfigs.values())).build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    @Override
    public Response organizationsOrganizationIdUserstoreConfigsPatch(String organizationId,
            List<OperationDTO> operations) {

        try {
            getOrganizationManager().patchUserStoreConfigs(organizationId,
                    operations.stream().map(op -> new Operation(op.getOp(), op.getPath(), op.getValue()))
                            .collect(Collectors.toList()));
            return Response.noContent().build();
        } catch (OrganizationManagementClientException e) {
            return handleBadRequestResponse(e, log);
        } catch (OrganizationManagementException e) {
            return handleServerErrorResponse(e, log);
        } catch (Throwable throwable) {
            return handleUnexpectedServerError(throwable, log);
        }
    }

    private URI getOrganizationResourceURI(Organization organization) throws URISyntaxException {

        return new URI(ORGANIZATION_PATH + '/' + organization.getId());
    }

    private URI getOrganizationRoleResourceURI(String organizationId) throws URISyntaxException {

        return new URI(String.format(ORGANIZATION_ROLES_PATH, organizationId));
    }
}

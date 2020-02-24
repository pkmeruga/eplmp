/*******************************************************************************
  * Copyright (c) 2017-2019 DocDoku.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributors:
  *    DocDoku - initial API and implementation
  *******************************************************************************/

package org.polarsys.eplmp.server.auth.modules;

import org.polarsys.eplmp.core.common.JWTokenUserGroupMapping;
import org.polarsys.eplmp.core.security.UserGroupMapping;
import org.polarsys.eplmp.core.services.ITokenManagerLocal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.callback.CallerPrincipalCallback;
import javax.security.auth.message.callback.GroupPrincipalCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of jwt authentication in requests headers
 *
 * @author Morgan Guimard
 */
public class JWTSAM extends CustomSAM {

    private static final Logger LOGGER = Logger.getLogger(JWTSAM.class.getName());
    private Key key;

    public JWTSAM(Key key) {
        this.key = key;
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject) throws AuthException {

        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();
        HttpServletResponse response = (HttpServletResponse) messageInfo.getResponseMessage();

        LOGGER.log(Level.FINE, "Validating request @" + request.getMethod() + " " + request.getRequestURI());

        String authorization = request.getHeader("Authorization");
        String[] splitAuthorization = authorization.split(" ");
        String jwt = splitAuthorization[1];
        ITokenManagerLocal tokenManager;

        try {
            final String beanName = "java:app/eplmp-server-ejb/JWTokenManager!org.polarsys.eplmp.core.services.ITokenManagerLocal";;
            InitialContext context = new InitialContext();
            tokenManager = (ITokenManagerLocal) context.lookup(beanName);
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE,null,e);
            return AuthStatus.FAILURE;
        }

        JWTokenUserGroupMapping jwTokenUserGroupMapping = tokenManager.validateAuthToken(key, jwt);

        if (jwTokenUserGroupMapping != null) {

            UserGroupMapping userGroupMapping = jwTokenUserGroupMapping.getUserGroupMapping();
            CallerPrincipalCallback callerPrincipalCallback = new CallerPrincipalCallback(clientSubject, userGroupMapping.getLogin());
            GroupPrincipalCallback groupPrincipalCallback = new GroupPrincipalCallback(clientSubject, new String[]{userGroupMapping.getGroupName()});
            Callback[] callbacks = new Callback[]{callerPrincipalCallback, groupPrincipalCallback};

            try {
                callbackHandler.handle(callbacks);
            } catch (IOException | UnsupportedCallbackException e) {
                throw new AuthException(e.getMessage());
            }

            tokenManager.refreshTokenIfNeeded(key, response, jwTokenUserGroupMapping);

            return AuthStatus.SUCCESS;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return AuthStatus.FAILURE;

    }

    @Override
    public boolean canHandle(MessageInfo messageInfo) {
        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();

        // Check in headers
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.split(" ").length == 2;
        }

        return false;
    }


}

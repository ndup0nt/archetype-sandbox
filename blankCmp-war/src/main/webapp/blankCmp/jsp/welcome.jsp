<%@ page import="org.silverpeas.components.oosphere.blankCmp.web.servlets.BlankCmpAction" %>
<%--

    Copyright (C) 2000 - 2011 Silverpeas

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    As a special exception to the terms and conditions of version 3.0 of
    the GPL, you may redistribute this Program in connection with Free/Libre
    Open Source Software ("FLOSS") applications as described in Silverpeas's
    FLOSS exception.  You should have received a copy of the text describing
    the FLOSS exception, and it is also available here:
    "http://www.silverpeas.com/legal/licensing"

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.silverpeas.com/tld/viewGenerator" prefix="view"%>
<html>
<head>
<view:looknfeel />
</head>
<view:setBundle bundle="${requestScope.resources.multilangBundle}" />
<view:setBundle bundle="${requestScope.resources.iconsBundle}"
	var="icons" />
<c:set var="browseContext" value="${requestScope.browseContext}" />
<body bgcolor="#ffffff" style="margin-left: 5px; margin-top: 5px;">
	<c:set var="browseBarTitle">Welcome to BlankCmp</c:set>
	<view:browseBar path="${browseBarTitle}" />
    <view:operationPane>
        <c:set var="createIcon">
            <fmt:message key="blankCmp.createBlankStuff" bundle="${icons}" />
        </c:set>
        <c:set var="createText">
            <fmt:message key="blankCmp.createBlankStuff.altText" />
        </c:set>
        <view:operation action="<%= BlankCmpAction.CREATE.getURI() %>"
                        icon="${createIcon}" altText="${createText}" />
        <view:operationSeparator />
    </view:operationPane>


    <view:window>
		<view:frame>
			<view:board>
            Cette instance s'appele <strong><c:out
						value="${browseContext[1]}" /></strong>.<br />
            Elle se situe dans l'espace <strong><c:out
						value="${browseContext[0]}" /></strong>.
        	</view:board>

            <view:arrayPane routingAddress="${action.URI}"
                            title="Liste des objets existants" var="objectsTable">
                <view:arrayColumn title="Identifiant" />
                <view:arrayColumn title="Libellé" />
                <c:forEach items="${objects}" var="o">
                    <view:arrayLine>
                        <view:arrayCellText text="${o.id}" />
                        <view:arrayCellText text="${o.label}" />
                    </view:arrayLine>
                </c:forEach>
            </view:arrayPane>

        </view:frame>
	</view:window>
</body>
</html>
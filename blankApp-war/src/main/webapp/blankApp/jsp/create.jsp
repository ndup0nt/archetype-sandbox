<%--

    Copyright (C) 2000 - 2012 Silverpeas

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    As a special exception to the terms and conditions of version 3.0 of
    the GPL, you may redistribute this Program in connection with Free/Libre
    Open Source Software ("FLOSS") applications as described in Silverpeas's
    FLOSS exception.  You should have received a copy of the text describing
    the FLOSS exception, and it is also available here:
    "http://www.silverpeas.org/docs/core/legal/floss_exception.html"

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.silverpeas.components.blankApp.web.servlets.BlankAppAction" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.silverpeas.com/tld/viewGenerator" prefix="view" %>
<html>
<head>
    <view:looknfeel/>
</head>
<body bgcolor="#ffffff">
<view:browseBar path="Nouvel objet"/>
<view:window>
    <view:frame>
        <view:board>
            <form id="blankApp-newBlankStuff-form" action="">
                <table>
                    <tr>
                        <td class="txtlibform"><label for="blankApp-newBlankStuff-label">Libellé :</label></td>
                        <td><input type="text" id="blankApp-newBlankStuff-label" name="label" size="20" maxlength="50"/></td>
                    </tr>
                </table>
                <view:buttonPane>
                    <c:url var="postURL" value="/services/blankApp/blankStuff"/>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $("#blankApp-newBlankStuff-form").submit(function(){
                                var blankStuff = {};
                                var simpleValues = ["label"];
                                $.each(simpleValues, function(){
                                    blankStuff[this] = $("#blankApp-newBlankStuff-"+this).val();
                                });

                                var myData = $.toJSON(blankStuff);
                                $.ajax({
                                    url: "${postURL}",
                                    type: "POST",
                                    data: myData,
                                    contentType: "application/json",
                                    dataType: "json",
                                    cache: false,
                                    success: function(data){
                                        if(console){
                                            console.log(data);
                                            console.log("Redirect to ${onSuccessURL}");
                                        }
                                        location.href = "${onSuccessURL}";
                                    },
                                    error: function(jqXHR, textStatus, errorThrown){
                                        if(console){
                                            console.log("date sent : "+myData);
                                            console.log("textStatus : "+textStatus);
                                            console.log("error thrown : "+errorThrown);
                                        }
                                    }
                                });
                                return false;
                            });
                        });
                        function submitForm() {
                            $("#blankApp-newBlankStuff-form").submit();
                        }
                    </script>
                    <view:button label="Sauvegarder" action="javascript:submitForm();"/>
                </view:buttonPane>
            </form>
        </view:board>
    </view:frame>
</view:window>
</body>
</html>

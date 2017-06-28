<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="manageenergissimo" scope="session" class="fr.paris.lutece.plugins.energissimo.web.ManageEnergissimoJspBean" />

<% manageenergissimo.init( request, manageenergissimo.RIGHT_MANAGEENERGISSIMO ); %>
<%= manageenergissimo.getManageEnergissimoHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>

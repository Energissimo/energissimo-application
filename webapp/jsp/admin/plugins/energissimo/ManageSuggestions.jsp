<jsp:useBean id="manageenergissimoSuggestion" scope="session" class="fr.paris.lutece.plugins.energissimo.web.SuggestionJspBean" />
<% String strContent = manageenergissimoSuggestion.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>

/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.energissimo.web;

import fr.paris.lutece.plugins.energissimo.business.Suggestion;
import fr.paris.lutece.plugins.energissimo.business.SuggestionHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Suggestion features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageSuggestions.jsp", controllerPath = "jsp/admin/plugins/energissimo/", right = "ENERGISSIMO_MANAGEMENT" )
public class SuggestionJspBean extends ManageEnergissimoJspBean
{
    // Templates
    private static final String TEMPLATE_MANAGE_SUGGESTIONS = "/admin/plugins/energissimo/manage_suggestions.html";
    private static final String TEMPLATE_CREATE_SUGGESTION = "/admin/plugins/energissimo/create_suggestion.html";
    private static final String TEMPLATE_MODIFY_SUGGESTION = "/admin/plugins/energissimo/modify_suggestion.html";

    // Parameters
    private static final String PARAMETER_ID_SUGGESTION = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_SUGGESTIONS = "energissimo.manage_suggestions.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_SUGGESTION = "energissimo.modify_suggestion.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_SUGGESTION = "energissimo.create_suggestion.pageTitle";

    // Markers
    private static final String MARK_SUGGESTION_LIST = "suggestion_list";
    private static final String MARK_SUGGESTION = "suggestion";

    private static final String JSP_MANAGE_SUGGESTIONS = "jsp/admin/plugins/energissimo/ManageSuggestions.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_SUGGESTION = "energissimo.message.confirmRemoveSuggestion";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "energissimo.model.entity.suggestion.attribute.";

    // Views
    private static final String VIEW_MANAGE_SUGGESTIONS = "manageSuggestions";
    private static final String VIEW_CREATE_SUGGESTION = "createSuggestion";
    private static final String VIEW_MODIFY_SUGGESTION = "modifySuggestion";

    // Actions
    private static final String ACTION_CREATE_SUGGESTION = "createSuggestion";
    private static final String ACTION_MODIFY_SUGGESTION = "modifySuggestion";
    private static final String ACTION_REMOVE_SUGGESTION = "removeSuggestion";
    private static final String ACTION_CONFIRM_REMOVE_SUGGESTION = "confirmRemoveSuggestion";

    // Infos
    private static final String INFO_SUGGESTION_CREATED = "energissimo.info.suggestion.created";
    private static final String INFO_SUGGESTION_UPDATED = "energissimo.info.suggestion.updated";
    private static final String INFO_SUGGESTION_REMOVED = "energissimo.info.suggestion.removed";
    
    // Session variable to store working values
    private Suggestion _suggestion;
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_SUGGESTIONS, defaultView = true )
    public String getManageSuggestions( HttpServletRequest request )
    {
        _suggestion = null;
        List<Suggestion> listSuggestions = SuggestionHome.getSuggestionsList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_SUGGESTION_LIST, listSuggestions, JSP_MANAGE_SUGGESTIONS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_SUGGESTIONS, TEMPLATE_MANAGE_SUGGESTIONS, model );
    }

    /**
     * Returns the form to create a suggestion
     *
     * @param request The Http request
     * @return the html code of the suggestion form
     */
    @View( VIEW_CREATE_SUGGESTION )
    public String getCreateSuggestion( HttpServletRequest request )
    {
        _suggestion = ( _suggestion != null ) ? _suggestion : new Suggestion(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_SUGGESTION, _suggestion );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_SUGGESTION, TEMPLATE_CREATE_SUGGESTION, model );
    }

    /**
     * Process the data capture form of a new suggestion
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_SUGGESTION )
    public String doCreateSuggestion( HttpServletRequest request )
    {
        populate( _suggestion, request );

        // Check constraints
        if ( !validateBean( _suggestion, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_SUGGESTION );
        }

        SuggestionHome.create( _suggestion );
        addInfo( INFO_SUGGESTION_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SUGGESTIONS );
    }

    /**
     * Manages the removal form of a suggestion whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_SUGGESTION )
    public String getConfirmRemoveSuggestion( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SUGGESTION ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_SUGGESTION ) );
        url.addParameter( PARAMETER_ID_SUGGESTION, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_SUGGESTION, url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a suggestion
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage suggestions
     */
    @Action( ACTION_REMOVE_SUGGESTION )
    public String doRemoveSuggestion( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SUGGESTION ) );
        SuggestionHome.remove( nId );
        addInfo( INFO_SUGGESTION_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SUGGESTIONS );
    }

    /**
     * Returns the form to update info about a suggestion
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_SUGGESTION )
    public String getModifySuggestion( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_SUGGESTION ) );

        if ( _suggestion == null || ( _suggestion.getId(  ) != nId ))
        {
            _suggestion = SuggestionHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_SUGGESTION, _suggestion );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_SUGGESTION, TEMPLATE_MODIFY_SUGGESTION, model );
    }

    /**
     * Process the change form of a suggestion
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_SUGGESTION )
    public String doModifySuggestion( HttpServletRequest request )
    {
        populate( _suggestion, request );

        // Check constraints
        if ( !validateBean( _suggestion, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_SUGGESTION, PARAMETER_ID_SUGGESTION, _suggestion.getId( ) );
        }

        SuggestionHome.update( _suggestion );
        addInfo( INFO_SUGGESTION_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_SUGGESTIONS );
    }
}
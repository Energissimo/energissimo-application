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

import fr.paris.lutece.plugins.energissimo.business.Municipality;
import fr.paris.lutece.plugins.energissimo.business.MunicipalityHome;
import fr.paris.lutece.plugins.energissimo.service.MunicipalityService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "energissimo" , pageTitleI18nKey = "energissimo.xpage.energissimo.pageTitle" , pagePathI18nKey = "energissimo.xpage.energissimo.pagePathLabel" )
public class EnergissimoApp extends MVCApplication
{
    private static final String TEMPLATE_XPAGE = "/skin/plugins/energissimo/energissimo.html";
    private static final String TEMPLATE_MUNICIPALITY = "/skin/plugins/energissimo/municipality.html"; 
    private static final String TEMPLATE_LIST = "/skin/plugins/energissimo/list.html"; 

    private static final String MARK_MUNICIPALITIES = "municipalities_list";
    private static final String MARK_MUNICIPALITY = "municipality"; 
    private static final String VIEW_HOME = "home";
    private static final String VIEW_MUNICIPALITY = "municipality";
    private static final String VIEW_LIST = "list";
    
    private static final String ACTION_SEARCH = "search";
    private static final String PARAMETER_QUERY = "query";
    private static final String PARAMETER_ID = "id";

    private List<Municipality> _searchResults;
    
    /**
     * Returns the content of the page energissimo. 
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_HOME , defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        Map<String,Object> model = getModel();
        
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ) , model  );
    }
    
    @Action( ACTION_SEARCH )
    public XPage doSearch( HttpServletRequest request )
    {
        String strQuery = request.getParameter( PARAMETER_QUERY );
        _searchResults = MunicipalityService.getMunicipalities(strQuery);
        switch( _searchResults.size() )
        {
            case 0 :
                addError( "Désolé. Aucune commune trouvée !" );
                return redirectView(request, VIEW_HOME);
            case 1 :
                Municipality m = _searchResults.get(0);
                Map<String, String> parameters = new HashMap<>(); 
                parameters.put( PARAMETER_ID ,  m.getZipcode() );
                return redirect( request , VIEW_MUNICIPALITY , parameters );
                
            default :
                return redirectView( request , VIEW_LIST );
        }
    }
    
    @View( VIEW_LIST )
    public XPage viewHList( HttpServletRequest request )
    {
        Map<String,Object> model = getModel();
        model.put( MARK_MUNICIPALITIES, _searchResults );
        return getXPage( TEMPLATE_LIST, request.getLocale(  ) , model );
    }
    
    @View( VIEW_MUNICIPALITY )
    public XPage viewMunicipality( HttpServletRequest request )
    {
        String strId = request.getParameter( PARAMETER_ID );
        Municipality m = MunicipalityHome.findByZipCode( strId ).get(0);  //FIXME
        Map<String,Object> model = getModel();
        model.put( MARK_MUNICIPALITY , m );
        return getXPage( TEMPLATE_MUNICIPALITY, request.getLocale(  ) , model );
    }
    
    
}

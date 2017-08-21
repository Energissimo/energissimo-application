/*
 * Copyright (c) 2017 Energissimo authors
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
 *  3. Neither the name of 'Energissimo' nor the names of its
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

package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for Suggestion objects
 */
public final class SuggestionHome
{
    // Static variable pointed at the DAO instance
    private static ISuggestionDAO _dao = SpringContextService.getBean( "energissimo.suggestionDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "energissimo" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private SuggestionHome( )
    {
    }

    /**
     * Create an instance of the suggestion class
     * 
     * @param suggestion
     *            The instance of the Suggestion which contains the informations to store
     * @return The instance of suggestion which has been created with its primary key.
     */
    public static Suggestion create( Suggestion suggestion )
    {
        _dao.insert( suggestion, _plugin );

        return suggestion;
    }

    /**
     * Update of the suggestion which is specified in parameter
     * 
     * @param suggestion
     *            The instance of the Suggestion which contains the data to store
     * @return The instance of the suggestion which has been updated
     */
    public static Suggestion update( Suggestion suggestion )
    {
        _dao.store( suggestion, _plugin );

        return suggestion;
    }

    /**
     * Remove the suggestion whose identifier is specified in parameter
     * 
     * @param nKey
     *            The suggestion Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a suggestion whose identifier is specified in parameter
     * 
     * @param nKey
     *            The suggestion primary key
     * @return an instance of Suggestion
     */
    public static Suggestion findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Load the data of all the suggestion objects and returns them as a list
     * 
     * @return the list which contains the data of all the suggestion objects
     */
    public static List<Suggestion> getSuggestionsList( )
    {
        return _dao.selectSuggestionsList( _plugin );
    }

    /**
     * Load the id of all the suggestion objects and returns them as a list
     * 
     * @return the list which contains the id of all the suggestion objects
     */
    public static List<Integer> getIdSuggestionsList( )
    {
        return _dao.selectIdSuggestionsList( _plugin );
    }

    /**
     * Load the data of all the suggestion objects and returns them as a referenceList
     * 
     * @return the referenceList which contains the data of all the suggestion objects
     */
    public static ReferenceList getSuggestionsReferenceList( )
    {
        return _dao.selectSuggestionsReferenceList( _plugin );
    }
}

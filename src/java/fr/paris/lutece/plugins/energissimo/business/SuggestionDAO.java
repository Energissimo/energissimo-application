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

package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Suggestion objects
 */
public final class SuggestionDAO implements ISuggestionDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_suggestion ) FROM energissimo_suggestion";
    private static final String SQL_QUERY_SELECT = "SELECT id_suggestion,  FROM energissimo_suggestion WHERE id_suggestion = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO energissimo_suggestion ( id_suggestion,  ) VALUES ( ?,  ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM energissimo_suggestion WHERE id_suggestion = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE energissimo_suggestion SET id_suggestion = ?,  WHERE id_suggestion = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_suggestion,  FROM energissimo_suggestion";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_suggestion FROM energissimo_suggestion";

    /**
     * Generates a new primary key
     * 
     * @param plugin
     *            The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery( );
        int nKey = 1;

        if ( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free( );
        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Suggestion suggestion, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        suggestion.setId( newPrimaryKey( plugin ) );
        int nIndex = 1;

        daoUtil.setInt( nIndex++, suggestion.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Suggestion load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );
        Suggestion suggestion = null;

        if ( daoUtil.next( ) )
        {
            suggestion = new Suggestion( );
            int nIndex = 1;

            suggestion.setId( daoUtil.getInt( nIndex++ ) );
        }

        daoUtil.free( );
        return suggestion;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Suggestion suggestion, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;

        daoUtil.setInt( nIndex++, suggestion.getId( ) );
        daoUtil.setInt( nIndex, suggestion.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Suggestion> selectSuggestionsList( Plugin plugin )
    {
        List<Suggestion> suggestionList = new ArrayList<Suggestion>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            Suggestion suggestion = new Suggestion( );
            int nIndex = 1;

            suggestion.setId( daoUtil.getInt( nIndex++ ) );

            suggestionList.add( suggestion );
        }

        daoUtil.free( );
        return suggestionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdSuggestionsList( Plugin plugin )
    {
        List<Integer> suggestionList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            suggestionList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return suggestionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectSuggestionsReferenceList( Plugin plugin )
    {
        ReferenceList suggestionList = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            suggestionList.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return suggestionList;
    }
}

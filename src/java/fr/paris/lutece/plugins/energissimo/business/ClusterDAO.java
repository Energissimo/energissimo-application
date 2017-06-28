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
 * This class provides Data Access methods for Cluster objects
 */
public final class ClusterDAO implements IClusterDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_cluster ) FROM energissimo_cluster";
    private static final String SQL_QUERY_SELECT = "SELECT id_cluster, name FROM energissimo_cluster WHERE id_cluster = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO energissimo_cluster ( id_cluster, name ) VALUES ( ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM energissimo_cluster WHERE id_cluster = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE energissimo_cluster SET id_cluster = ?, name = ? WHERE id_cluster = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_cluster, name FROM energissimo_cluster";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_cluster FROM energissimo_cluster";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin)
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
        daoUtil.executeQuery( );
        int nKey = 1;

        if( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free();
        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Cluster cluster, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        cluster.setId( newPrimaryKey( plugin ) );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , cluster.getId( ) );
        daoUtil.setString( nIndex++ , cluster.getName( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Cluster load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );
        Cluster cluster = null;

        if ( daoUtil.next( ) )
        {
            cluster = new Cluster();
            int nIndex = 1;
            
            cluster.setId( daoUtil.getInt( nIndex++ ) );
            cluster.setName( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return cluster;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Cluster cluster, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , cluster.getId( ) );
        daoUtil.setString( nIndex++ , cluster.getName( ) );
        daoUtil.setInt( nIndex , cluster.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Cluster> selectClustersList( Plugin plugin )
    {
        List<Cluster> clusterList = new ArrayList<Cluster>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Cluster cluster = new Cluster(  );
            int nIndex = 1;
            
            cluster.setId( daoUtil.getInt( nIndex++ ) );
            cluster.setName( daoUtil.getString( nIndex++ ) );

            clusterList.add( cluster );
        }

        daoUtil.free( );
        return clusterList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdClustersList( Plugin plugin )
    {
        List<Integer> clusterList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            clusterList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return clusterList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectClustersReferenceList( Plugin plugin )
    {
        ReferenceList clusterList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            clusterList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return clusterList;
    }
}
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
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for Cluster objects
 */
public final class ClusterHome
{
    // Static variable pointed at the DAO instance
    private static IClusterDAO _dao = SpringContextService.getBean( "energissimo.clusterDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "energissimo" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private ClusterHome(  )
    {
    }

    /**
     * Create an instance of the cluster class
     * @param cluster The instance of the Cluster which contains the informations to store
     * @return The  instance of cluster which has been created with its primary key.
     */
    public static Cluster create( Cluster cluster )
    {
        _dao.insert( cluster, _plugin );

        return cluster;
    }

    /**
     * Update of the cluster which is specified in parameter
     * @param cluster The instance of the Cluster which contains the data to store
     * @return The instance of the  cluster which has been updated
     */
    public static Cluster update( Cluster cluster )
    {
        _dao.store( cluster, _plugin );

        return cluster;
    }

    /**
     * Remove the cluster whose identifier is specified in parameter
     * @param nKey The cluster Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a cluster whose identifier is specified in parameter
     * @param nKey The cluster primary key
     * @return an instance of Cluster
     */
    public static Cluster findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin);
    }

    /**
     * Load the data of all the cluster objects and returns them as a list
     * @return the list which contains the data of all the cluster objects
     */
    public static List<Cluster> getClustersList( )
    {
        return _dao.selectClustersList( _plugin );
    }
    
    /**
     * Load the id of all the cluster objects and returns them as a list
     * @return the list which contains the id of all the cluster objects
     */
    public static List<Integer> getIdClustersList( )
    {
        return _dao.selectIdClustersList( _plugin );
    }
    
    /**
     * Load the data of all the cluster objects and returns them as a referenceList
     * @return the referenceList which contains the data of all the cluster objects
     */
    public static ReferenceList getClustersReferenceList( )
    {
        return _dao.selectClustersReferenceList(_plugin );
    }
}


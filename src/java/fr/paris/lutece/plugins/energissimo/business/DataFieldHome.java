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

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for Field objects
 */

public final class DataFieldHome
{

    // Static variable pointed at the DAO instance
    private static DataFieldDAO _dao = new DataFieldDAO( );

    /**
     * Private constructor - this class need not be instantiated
     */

    private DataFieldHome( )
    {
    }

    /**
     * Create an instance of the field class
     * 
     * @param field
     *            The instance of the Field which contains the informations to store
     * @return The instance of field which has been created with its primary key.
     */

    public static DataField create( DataField field )
    {
        _dao.insert( field );

        return field;
    }

    /**
     * Update of the field data specified in parameter
     * 
     * @param field
     *            The instance of the Field which contains the data to store
     * @return The instance of the field which has been updated
     */

    public static DataField update( DataField field )
    {
        _dao.store( field );

        return field;
    }

    /**
     * Remove the field whose identifier is specified in parameter
     * 
     * @param nFieldId
     *            The field Id
     */

    public static void remove( int nFieldId )
    {
        _dao.delete( nFieldId );
    }

    // /////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a field whose identifier is specified in parameter
     * 
     * @param nKey
     *            The field primary key
     * @return an instance of Field
     */

    public static DataField findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey );
    }

    /**
     * Load the data of all the field objects and returns them in form of a collection
     * 
     * @return the list which contains the data of all the field objects
     */

    public static List<DataField> findAll( )
    {
        return _dao.selectFieldsList( );
    }

}

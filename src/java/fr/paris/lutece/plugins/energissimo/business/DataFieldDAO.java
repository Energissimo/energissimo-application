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

import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Field objects
 */

public final class DataFieldDAO
{

    // Constants

    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_field ) FROM energissimo_iris_field";
    private static final String SQL_QUERY_SELECT = "SELECT id_field, field_label, field_unit, id_group, field_order FROM energissimo_iris_field WHERE id_field = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO energissimo_iris_field ( id_field, field_label, field_unit, id_group, field_order ) VALUES ( ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM energissimo_iris_field WHERE id_field = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE energissimo_iris_field SET id_field = ?, field_label = ?, field_unit = ?, id_group = ?, field_order = ? WHERE id_field = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_field, field_label, field_unit, id_group, field_order FROM energissimo_iris_field";

    /**
     * Generates a new primary key
     * 
     * @return The new primary key
     */

    public int newPrimaryKey( )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK );
        daoUtil.executeQuery( );

        int nKey;

        daoUtil.next( );
        nKey = daoUtil.getInt( 1 ) + 1;
        daoUtil.free( );

        return nKey;
    }

    /**
     * Insert a new record in the table.
     * 
     * @param field
     *            instance of the Field object to insert
     */

    public void insert( DataField field )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT );

        field.setId( newPrimaryKey( ) );

        daoUtil.setInt( 1, field.getId( ) );
        daoUtil.setString( 2, field.getFieldLabel( ) );
        daoUtil.setString( 3, field.getFieldUnit( ) );
        daoUtil.setInt( 4, field.getIdGroup( ) );
        daoUtil.setInt( 5, field.getFieldOrder( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * Load the data of the field from the table
     * 
     * @param nId
     *            The identifier of the field
     * @return the instance of the Field
     */

    public DataField load( int nId )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT );
        daoUtil.setInt( 1, nId );
        daoUtil.executeQuery( );

        DataField field = null;

        if ( daoUtil.next( ) )
        {
            field = new DataField( );

            field.setId( daoUtil.getInt( 1 ) );
            field.setFieldLabel( daoUtil.getString( 2 ) );
            field.setFieldUnit( daoUtil.getString( 3 ) );
            field.setIdGroup( daoUtil.getInt( 4 ) );
            field.setFieldOrder( daoUtil.getInt( 5 ) );
        }

        daoUtil.free( );
        return field;
    }

    /**
     * Delete a record from the table
     * 
     * @param nFieldId
     *            The identifier of the field
     */

    public void delete( int nFieldId )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE );
        daoUtil.setInt( 1, nFieldId );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * Update the record in the table
     * 
     * @param field
     *            The reference of the field
     */

    public void store( DataField field )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE );

        daoUtil.setInt( 1, field.getId( ) );
        daoUtil.setString( 2, field.getFieldLabel( ) );
        daoUtil.setString( 3, field.getFieldUnit( ) );
        daoUtil.setInt( 4, field.getIdGroup( ) );
        daoUtil.setInt( 5, field.getFieldOrder( ) );
        daoUtil.setInt( 6, field.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * Load the data of all the fields and returns them as a list
     * 
     * @return The list which contains the data of all the fields
     */

    public List<DataField> selectFieldsList( )
    {
        List<DataField> fieldList = new ArrayList<>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            DataField field = new DataField( );

            field.setId( daoUtil.getInt( 1 ) );
            field.setFieldLabel( daoUtil.getString( 2 ) );
            field.setFieldUnit( daoUtil.getString( 3 ) );
            field.setIdGroup( daoUtil.getInt( 4 ) );
            field.setFieldOrder( daoUtil.getInt( 5 ) );

            fieldList.add( field );
        }

        daoUtil.free( );
        return fieldList;
    }

}

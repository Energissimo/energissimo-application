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
	 * @return The new primary key
	 */
    
	public int newPrimaryKey()
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK  );
		daoUtil.executeQuery();

		int nKey;

		if( !daoUtil.next() )
		{
			// if the table is empty
			nKey = 1;
		}

		nKey = daoUtil.getInt( 1 ) + 1;
		daoUtil.free();

		return nKey;
	}




	/**
	 * Insert a new record in the table.
	 * @param field instance of the Field object to insert
	 */

	public void insert( DataField field )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT );
                
		field.setId( newPrimaryKey() );
                
                daoUtil.setInt ( 1, field.getId ( ) );
                daoUtil.setString ( 2, field.getFieldLabel ( ) );
                daoUtil.setString ( 3, field.getFieldUnit ( ) );
                daoUtil.setInt ( 4, field.getIdGroup ( ) );
                daoUtil.setInt ( 5, field.getFieldOrder ( ) );

		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Load the data of the field from the table
	 * @param nId The identifier of the field
	 * @return the instance of the Field
	 */


        public DataField load( int nId )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT );
		daoUtil.setInt( 1 , nId );
		daoUtil.executeQuery();

		DataField field = null;

		if ( daoUtil.next() )
		{
			field = new DataField();

                    field.setId( daoUtil.getInt(  1 ) );
                    field.setFieldLabel( daoUtil.getString(  2 ) );
                    field.setFieldUnit( daoUtil.getString(  3 ) );
                    field.setIdGroup( daoUtil.getInt(  4 ) );
                    field.setFieldOrder( daoUtil.getInt(  5 ) );
		}

		daoUtil.free();
		return field;
	}


	/**
	 * Delete a record from the table
	 * @param nFieldId The identifier of the field
	 */

	public void delete( int nFieldId )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE );
		daoUtil.setInt( 1 , nFieldId );
		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Update the record in the table
	 * @param field The reference of the field
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
	 * @return The list which contains the data of all the fields
	 */

        public List<DataField> selectFieldsList()
	{
		List<DataField> fieldList = new ArrayList<>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
                DataField field = new DataField(  );

                    field.setId( daoUtil.getInt( 1 ) );
                    field.setFieldLabel( daoUtil.getString( 2 ) );
                    field.setFieldUnit( daoUtil.getString( 3 ) );
                    field.setIdGroup( daoUtil.getInt( 4 ) );
                    field.setFieldOrder( daoUtil.getInt( 5 ) );

                fieldList.add( field );
		}

		daoUtil.free();
		return fieldList;
	}

}
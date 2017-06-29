package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.List;



/**
 * This class provides instances management methods (create, find, ...) for Field objects
 */

public final class DataFieldHome
{

    // Static variable pointed at the DAO instance
    private static DataFieldDAO _dao = new DataFieldDAO();


    /**
     * Private constructor - this class need not be instantiated
     */

    private DataFieldHome(  )
    {
    }

    /**
     * Create an instance of the field class
     * @param field The instance of the Field which contains the informations to store
     * @return The  instance of field which has been created with its primary key.
     */

    public static DataField create( DataField field )
    {
        _dao.insert( field );

        return field;
    }


    /**
     * Update of the field data specified in parameter
     * @param field The instance of the Field which contains the data to store
     * @return The instance of the  field which has been updated
     */

    public static DataField update( DataField field )
    {
        _dao.store( field );

        return field;
    }


    /**
     * Remove the field whose identifier is specified in parameter
     * @param nFieldId The field Id
     */


    public static void remove( int nFieldId )
    {
        _dao.delete( nFieldId );
    }


    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a field whose identifier is specified in parameter   
     * @param nKey The field primary key
     * @return an instance of Field
     */

    public static DataField findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey );
    }


    /**
     * Load the data of all the field objects and returns them in form of a collection
     * @return the list which contains the data of all the field objects
     */

    public static List<DataField> findAll()
    {
        return _dao.selectFieldsList();
    }

}
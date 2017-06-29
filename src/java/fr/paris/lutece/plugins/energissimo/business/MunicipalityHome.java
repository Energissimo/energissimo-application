package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.List;



/**
 * This class provides instances management methods (create, find, ...) for Municipality objects
 */

public final class MunicipalityHome
{

    // Static variable pointed at the DAO instance
    private static MunicipalityDAO _dao = new MunicipalityDAO();


    /**
     * Private constructor - this class need not be instantiated
     */

    private MunicipalityHome(  )
    {
    }

    /**
     * Create an instance of the municipality class
     * @param municipality The instance of the Municipality which contains the informations to store
     * @return The  instance of municipality which has been created with its primary key.
     */

    public static Municipality create( Municipality municipality )
    {
        _dao.insert( municipality );

        return municipality;
    }


    /**
     * Update of the municipality data specified in parameter
     * @param municipality The instance of the Municipality which contains the data to store
     * @return The instance of the  municipality which has been updated
     */

    public static Municipality update( Municipality municipality )
    {
        _dao.store( municipality );

        return municipality;
    }


    /**
     * Remove the municipality whose identifier is specified in parameter
     * @param nMunicipalityId The municipality Id
     */


    public static void remove( int nMunicipalityId )
    {
        _dao.delete( nMunicipalityId );
    }


    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a municipality whose identifier is specified in parameter   
     * @param nKey The municipality primary key
     * @return an instance of Municipality
     */

    public static Municipality findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey );
    }


    /**
     * Load the data of all the municipality objects and returns them in form of a collection
     * @return the list which contains the data of all the municipality objects
     */

    public static List<Municipality> findAll()
    {
        return _dao.selectMunicipalitysList();
    }
    
    public static List<Municipality> findByName( String strName )
    {
        return _dao.selectByName( strName );
    }
    
    public static List<Municipality> findByZipCode( String strZipCode )
    {
        return _dao.selectByZipCode( strZipCode );
    }   

}

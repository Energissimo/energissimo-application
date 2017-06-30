package fr.paris.lutece.plugins.energissimo.business;

import java.util.ArrayList;
import java.util.List;



/**
 * This class provides instances management methods (create, find, ...) for Municipality objects
 */

public final class MunicipalityHome
{

    // Static variable pointed at the DAO instance
    private static MunicipalityDAO _dao = new MunicipalityDAO();


    public static List<Municipality> findByName( String strName )
    {
        return _dao.selectByName( strName );
    }
    
    public static List<Municipality> findByZipCode( String strZipCode )
    {
        List<Municipality> list = new ArrayList<>();
        Municipality m = _dao.selectByZipCode( strZipCode );
        if( m != null )
        {
            list.add( m );
        }
        return list;
    }   

    public static Municipality getSimilar(String strIrisCode ) 
    {
        return _dao.selectByIris( strIrisCode );
    }

}

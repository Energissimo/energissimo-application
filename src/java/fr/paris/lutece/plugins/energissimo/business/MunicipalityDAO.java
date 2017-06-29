package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Municipality objects
 */
public final class MunicipalityDAO {

    // Constants
    private static final String SQL_QUERY_SELECT_BY_NAME = "SELECT name, zipcode FROM energissimo_municipality WHERE name LIKE ? ";
    	private static final String SQL_QUERY_SELECT_BY_ZIPCODE = "SELECT zipcode, name, code_iris"
                + " data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, "
                + " data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, "
                + " data21, data22, data23, data24, data25, data26, data27, data28, data29, data30, "
                + " data31, data32, data33 "
                + " FROM energissimo_iris_data WHERE zipcode = ?";

        private static final int NUM_FIELD = 32;

    List<Municipality> selectByName(String strName) 
    {
        List<Municipality> municipalityList = new ArrayList<>();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_NAME );
        String strLike = "%" + strName + "%";
        daoUtil.setString( 1 , strLike );
        daoUtil.executeQuery();

        while (daoUtil.next()) {
            Municipality municipality = new Municipality();

            municipality.setName(daoUtil.getString(1));
            municipality.setZipcode(daoUtil.getString(2));

            municipalityList.add(municipality);
        }

        daoUtil.free();
        return municipalityList;
    }

    
        
    public Municipality selectByZipCode(String strZipCode ) 
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ZIPCODE );
        daoUtil.setString( 1 , strZipCode  );
        daoUtil.executeQuery();

        Municipality municipality = null;
        while (daoUtil.next()) {
            int nIndex = 1;
            if( municipality == null )
            {
                municipality = new Municipality();
                municipality.setZipcode( daoUtil.getString( nIndex++ ) );
                municipality.setName( daoUtil.getString( nIndex++ ));
            }
            
            IrisData irisData = new IrisData();

            irisData.setCodeIris( daoUtil.getString(nIndex++));
            String[] data = new String[NUM_FIELD];
            for( int i = 0 ; i < NUM_FIELD ; i++ )
            {
                data[i] = daoUtil.getString( nIndex++ );
            }
            irisData.setData( data );
            municipality.addIrisData(irisData);

        }

        daoUtil.free();
        return municipality;
    }


}

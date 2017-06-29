package fr.paris.lutece.plugins.energissimo.business;

import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Municipality objects
 */
public final class MunicipalityDAO {

    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_municipality ) FROM energissimo_municipality";
    private static final String SQL_QUERY_SELECT = "SELECT id_municipality, name, zipcode, data FROM energissimo_municipality WHERE id_municipality = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO energissimo_municipality ( id_municipality, name, zipcode, data ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM energissimo_municipality WHERE id_municipality = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE energissimo_municipality SET id_municipality = ?, name = ?, zipcode = ?, data = ? WHERE id_municipality = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_municipality, name, zipcode, data FROM energissimo_municipality";
    private static final String SQL_QUERY_SELECT_BY_NAME = "SELECT id_municipality, name, zipcode, data FROM energissimo_municipality WHERE name LIKE ? ";
    private static final String SQL_QUERY_SELECT_BY_ZIPCODE = "SELECT id_municipality, name, zipcode, data FROM energissimo_municipality WHERE zipcode = ?";

    
    /**
     * Generates a new primary key
     *
     * @return The new primary key
     */
    public int newPrimaryKey() {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_NEW_PK);
        daoUtil.executeQuery();

        int nKey;

        daoUtil.next();
        
        nKey = daoUtil.getInt(1) + 1;
        daoUtil.free();

        return nKey;
    }

    /**
     * Insert a new record in the table.
     *
     * @param municipality instance of the Municipality object to insert
     */
    public void insert(Municipality municipality) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_INSERT);

        municipality.setId(newPrimaryKey());

        daoUtil.setInt(1, municipality.getId());
        daoUtil.setString(2, municipality.getName());
        daoUtil.setString(3, municipality.getZipcode());
        daoUtil.setString(4, municipality.getData());

        daoUtil.executeUpdate();
        daoUtil.free();
    }

    /**
     * Load the data of the municipality from the table
     *
     * @param nId The identifier of the municipality
     * @return the instance of the Municipality
     */
    public Municipality load(int nId) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_SELECT);
        daoUtil.setInt(1, nId);
        daoUtil.executeQuery();

        Municipality municipality = null;

        if (daoUtil.next()) {
            municipality = new Municipality();

            municipality.setId(daoUtil.getInt(1));
            municipality.setName(daoUtil.getString(2));
            municipality.setZipcode(daoUtil.getString(3));
            municipality.setData(daoUtil.getString(4));
        }

        daoUtil.free();
        return municipality;
    }

    /**
     * Delete a record from the table
     *
     * @param nMunicipalityId The identifier of the municipality
     */
    public void delete(int nMunicipalityId) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_DELETE);
        daoUtil.setInt(1, nMunicipalityId);
        daoUtil.executeUpdate();
        daoUtil.free();
    }

    /**
     * Update the record in the table
     *
     * @param municipality The reference of the municipality
     */
    public void store(Municipality municipality) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_UPDATE);

        daoUtil.setInt(1, municipality.getId());
        daoUtil.setString(2, municipality.getName());
        daoUtil.setString(3, municipality.getZipcode());
        daoUtil.setString(4, municipality.getData());
        daoUtil.setInt(5, municipality.getId());

        daoUtil.executeUpdate();
        daoUtil.free();
    }

    /**
     * Load the data of all the municipalitys and returns them as a list
     *
     * @return The list which contains the data of all the municipalitys
     */
    public List<Municipality> selectMunicipalitysList() {
        List<Municipality> municipalityList = new ArrayList<>();
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_SELECTALL);
        daoUtil.executeQuery();

        while (daoUtil.next()) {
            Municipality municipality = new Municipality();

            municipality.setId(daoUtil.getInt(1));
            municipality.setName(daoUtil.getString(2));
            municipality.setZipcode(daoUtil.getString(3));
            municipality.setData(daoUtil.getString(4));

            municipalityList.add(municipality);
        }

        daoUtil.free();
        return municipalityList;
    }

    List<Municipality> selectByName(String strName) 
    {
        List<Municipality> municipalityList = new ArrayList<>();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_NAME );
        String strLike = "%" + strName + "%";
        daoUtil.setString( 1 , strLike );
        daoUtil.executeQuery();

        while (daoUtil.next()) {
            Municipality municipality = new Municipality();

            municipality.setId(daoUtil.getInt(1));
            municipality.setName(daoUtil.getString(2));
            municipality.setZipcode(daoUtil.getString(3));
            municipality.setData(daoUtil.getString(4));

            municipalityList.add(municipality);
        }

        daoUtil.free();
        return municipalityList;
    }

    List<Municipality> selectByZipCode(String strZipCode ) 
    {
        List<Municipality> municipalityList = new ArrayList<>();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ZIPCODE );
        daoUtil.setString( 1 , strZipCode  );
        daoUtil.executeQuery();

        while (daoUtil.next()) {
            Municipality municipality = new Municipality();

            municipality.setId(daoUtil.getInt(1));
            municipality.setName(daoUtil.getString(2));
            municipality.setZipcode(daoUtil.getString(3));
            municipality.setData(daoUtil.getString(4));

            municipalityList.add(municipality);
        }

        daoUtil.free();
        return municipalityList;
    }

}

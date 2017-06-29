package fr.paris.lutece.plugins.energissimo.business;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the business class for the object Municipality
 */ 
public class Municipality
{
    // Variables declarations 
    private String _strName;
    private String _strZipcode;
    private String _strData;
    private List<IrisData> _listIrisData = new ArrayList<>();
    
    
    
       /**
        * Returns the Name
        * @return The Name
        */ 
    public String getName()
    {
        return _strName;
    }
    
       /**
        * Sets the Name
        * @param strName The Name
        */ 
    public void setName( String strName )
    {
        _strName = strName;
    }
    
       /**
        * Returns the Zipcode
        * @return The Zipcode
        */ 
    public String getZipcode()
    {
        return _strZipcode;
    }
    
       /**
        * Sets the Zipcode
        * @param strZipcode The Zipcode
        */ 
    public void setZipcode( String strZipcode )
    {
        _strZipcode = strZipcode;
    }
    
       /**
        * Returns the Data
        * @return The Data
        */ 
    public String getData()
    {
        return _strData;
    }
    
       /**
        * Sets the Data
        * @param strData The Data
        */ 
    public void setData( String strData )
    {
        _strData = strData;
    }

    @Override
    public String toString() 
    {
        return _strZipcode + " " + _strName;
    }

    /**
     * @return the _listIrisData
     */
    public List<IrisData> getIris() 
    {
        return _listIrisData;
    }

    /**
     * @param _listIrisData the _listIrisData to set
     */
    public void addIrisData( IrisData irisData) 
    {
        _listIrisData.add(irisData);
    }
    
    
 }
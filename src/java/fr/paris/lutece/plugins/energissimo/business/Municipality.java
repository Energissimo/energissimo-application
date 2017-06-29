package fr.paris.lutece.plugins.energissimo.business;

/**
 * This is the business class for the object Municipality
 */ 
public class Municipality
{
    // Variables declarations 
    private int _nIdMunicipality;
    private String _strName;
    private String _strZipcode;
    private String _strData;
    
    
       /**
        * Returns the IdMunicipality
        * @return The IdMunicipality
        */ 
    public int getId()
    {
        return _nIdMunicipality;
    }
    
       /**
        * Sets the IdMunicipality
        * @param nIdMunicipality The IdMunicipality
        */ 
    public void setId( int nIdMunicipality )
    {
        _nIdMunicipality = nIdMunicipality;
    }
    
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
 }
package fr.paris.lutece.plugins.energissimo.business;

/**
 * This is the business class for the object Field
 */ 
public class DataField
{
    // Variables declarations 
    private int _nIdField;
    private String _strFieldLabel;
    private String _strFieldUnit;
    private int _nIdGroup;
    private int _nFieldOrder;
    
    
       /**
        * Returns the IdField
        * @return The IdField
        */ 
    public int getId()
    {
        return _nIdField;
    }
    
       /**
        * Sets the IdField
        * @param nIdField The IdField
        */ 
    public void setId( int nIdField )
    {
        _nIdField = nIdField;
    }
    
       /**
        * Returns the FieldLabel
        * @return The FieldLabel
        */ 
    public String getFieldLabel()
    {
        return _strFieldLabel;
    }
    
       /**
        * Sets the FieldLabel
        * @param strFieldLabel The FieldLabel
        */ 
    public void setFieldLabel( String strFieldLabel )
    {
        _strFieldLabel = strFieldLabel;
    }
    
       /**
        * Returns the FieldUnit
        * @return The FieldUnit
        */ 
    public String getFieldUnit()
    {
        return _strFieldUnit;
    }
    
       /**
        * Sets the FieldUnit
        * @param strFieldUnit The FieldUnit
        */ 
    public void setFieldUnit( String strFieldUnit )
    {
        _strFieldUnit = strFieldUnit;
    }
    
       /**
        * Returns the IdGroup
        * @return The IdGroup
        */ 
    public int getIdGroup()
    {
        return _nIdGroup;
    }
    
       /**
        * Sets the IdGroup
        * @param nIdGroup The IdGroup
        */ 
    public void setIdGroup( int nIdGroup )
    {
        _nIdGroup = nIdGroup;
    }
    
       /**
        * Returns the FieldOrder
        * @return The FieldOrder
        */ 
    public int getFieldOrder()
    {
        return _nFieldOrder;
    }
    
       /**
        * Sets the FieldOrder
        * @param nFieldOrder The FieldOrder
        */ 
    public void setFieldOrder( int nFieldOrder )
    {
        _nFieldOrder = nFieldOrder;
    }
 }
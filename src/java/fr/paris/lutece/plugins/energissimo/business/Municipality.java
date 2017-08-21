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
    private List<IrisData> _listIrisData = new ArrayList<>( );

    /**
     * Returns the Name
     * 
     * @return The Name
     */
    public String getName( )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * 
     * @param strName
     *            The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Zipcode
     * 
     * @return The Zipcode
     */
    public String getZipcode( )
    {
        return _strZipcode;
    }

    /**
     * Sets the Zipcode
     * 
     * @param strZipcode
     *            The Zipcode
     */
    public void setZipcode( String strZipcode )
    {
        _strZipcode = strZipcode;
    }

    /**
     * Returns the Data
     * 
     * @return The Data
     */
    public String getData( )
    {
        return _strData;
    }

    /**
     * Sets the Data
     * 
     * @param strData
     *            The Data
     */
    public void setData( String strData )
    {
        _strData = strData;
    }

    @Override
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( _strZipcode + " " + _strName );
        for ( IrisData iris : _listIrisData )
        {
            sb.append( "\n Iris : " + iris.getCodeIris( ) );

        }
        return sb.toString( );
    }

    /**
     * @return the _listIrisData
     */
    public List<IrisData> getIris( )
    {
        return _listIrisData;
    }

    /**
     * @param _listIrisData
     *            the _listIrisData to set
     */
    public void addIrisData( IrisData irisData )
    {
        _listIrisData.add( irisData );
    }

}

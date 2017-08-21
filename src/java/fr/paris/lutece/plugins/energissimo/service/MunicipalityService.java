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

package fr.paris.lutece.plugins.energissimo.service;

import fr.paris.lutece.plugins.energissimo.business.IrisData;
import fr.paris.lutece.plugins.energissimo.business.Municipality;
import fr.paris.lutece.plugins.energissimo.business.MunicipalityHome;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Municipality Service
 */
public class MunicipalityService
{
    private static Pattern zipPattern = Pattern.compile( "(\\d{5})" );

    /**
     * Get the list of municipalities matching a search query
     * @param strQuery The query
     * @return The list
     */
    public static List<Municipality> getMunicipalities( String strQuery )
    {
        List<Municipality> list;

        Matcher zipMatcher = zipPattern.matcher( strQuery );
        if ( zipMatcher.matches( ) )
        {
            list = MunicipalityHome.findByZipCode( strQuery );
        }
        else
        {
            list = MunicipalityHome.findByName( strQuery );
        }

        if ( list.size( ) == 1 )
        {
            Municipality m = list.get( 0 );
            fillData( m );
        }
        return list;
    }

    /**
     * Fill data of a given municipality
     * @param municipality The municipality
     */
    public static void fillData( Municipality municipality )
    {
        List<IrisData> list = municipality.getIris( );

        for ( int j = 0; j < list.size( ); j++ )
        {
            IrisData iris = list.get( j );
            for ( int i = 0; i < 3; i++ )
            {
                String codeIris = iris.getData( ) [47 + i];
                codeIris = codeIris.replace( "\r", "" );
                Municipality similar = MunicipalityHome.getSimilar( codeIris );
                if ( similar != null )
                {
                    iris.addSimilars( similar );
                }
            }
        }
    }

}

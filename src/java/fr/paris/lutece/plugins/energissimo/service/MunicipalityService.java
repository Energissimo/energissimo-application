/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.energissimo.service;

import fr.paris.lutece.plugins.energissimo.business.IrisData;
import fr.paris.lutece.plugins.energissimo.business.Municipality;
import fr.paris.lutece.plugins.energissimo.business.MunicipalityHome;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pierre
 */
public class MunicipalityService 
{
    private static Pattern zipPattern = Pattern.compile("(\\d{5})");

    public static List<Municipality> getMunicipalities( String strQuery )
    {
        List<Municipality> list;
        
        Matcher zipMatcher = zipPattern.matcher( strQuery );
        if (zipMatcher.matches()) 
        {
            list = MunicipalityHome.findByZipCode( strQuery );
        }
        else
        {
            list = MunicipalityHome.findByName(strQuery);
        }
        
        if (list.size() == 1 )
        {
            Municipality m = list.get(0);
            fillData( m );
            System.out.println( m );
        }
        return list;
    }

    public static void fillData(Municipality m) 
    {
        List<IrisData> list =  m.getIris();
        
        for( int j = 0 ; j < list.size() ; j++  )
        {
            IrisData iris = list.get(j);
            for( int i = 0 ; i < 3 ; i++ )
            {
                String codeIris = iris.getData()[47 + i];
                codeIris = codeIris.replace("\r", "" );
                System.out.println( "code iris : " + codeIris );
                Municipality similar = MunicipalityHome.getSimilar( codeIris );
                System.out.println( "Similar : " + similar );
                if( similar != null )
                {
                    iris.addSimilars( similar );
                }
            }
        }
    }
       
}

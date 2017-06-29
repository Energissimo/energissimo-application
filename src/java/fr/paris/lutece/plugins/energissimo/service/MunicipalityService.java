/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.energissimo.service;

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

    private static void fillData(Municipality m) 
    {
    }
       
}

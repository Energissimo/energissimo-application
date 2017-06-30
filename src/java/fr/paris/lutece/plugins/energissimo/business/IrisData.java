/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.energissimo.business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierre
 */
public class IrisData 
{
    private String codeIris;
    private String[] data;
    private List<Municipality> similars = new ArrayList<>();

    /**
     * @return the codeIris
     */
    public String getCodeIris() {
        return codeIris;
    }

    /**
     * @param codeIris the codeIris to set
     */
    public void setCodeIris(String codeIris) {
        this.codeIris = codeIris;
    }

    /**
     * @return the data
     */
    public String[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String[] data) {
        this.data = data;
    }

    /**
     * @return the similars
     */
    public List<Municipality> getSimilars() {
        
        return similars;
    }

    /**
     * @param similars the similars to set
     */
    public void addSimilars( Municipality m) {
        System.out.println( "before" + similars );
        similars.add(m);
                System.out.println( "after" + similars );

    }
}

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
 * IRIS Data
 */
public class IrisData
{
    private String _strCodeIris;
    private String [ ] _data;
    private List<Municipality> _similars = new ArrayList<>( );

    /**
     * @return the codeIris
     */
    public String getCodeIris( )
    {
        return _strCodeIris;
    }

    /**
     * @param codeIris
     *            the codeIris to set
     */
    public void setCodeIris( String codeIris )
    {
        _strCodeIris = codeIris;
    }

    /**
     * @return the data
     */
    public String [ ] getData( )
    {
        return _data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData( String [ ] data )
    {
        _data = data;
    }

    /**
     * @return the similars
     */
    public List<Municipality> getSimilars( )
    {

        return _similars;
    }

    /**
     * Add a similar municipality
     * 
     * @param municipality
     *            The municipality
     */
    public void addSimilars( Municipality municipality )
    {
        _similars.add( municipality );

    }
}

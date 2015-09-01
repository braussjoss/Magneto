package org.netbeans.rest.application.config;


import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brauss
 */
@Stateless
@Path("/MutantDetector")
public class MutantDetector {
    
    @GET
    public boolean isMutant(@QueryParam("dna") String dnas)
    {
        return $isMutant(dnas);
    }
    
    boolean $isMutant(String dnas)
    {
        String dna[] = dnas.split(",");
        //El tamaño del arreglo es de n*m
        int n = dna.length;
        int m = dna[0].length();
        char[][] dnaArray = new char[n][m];
        ArrayList<String> dnaMutante = new ArrayList<>();
        boolean esMutante = false;
        int contadorADN = 0;
        if(n<4 || m <4)
            //Si el tamaño del dna es menor a 4, entonces no es mutante.
        {
            return false;
        }

        //Conversion a Arreglo Bidimensional
        for(int i=0;i<n; i++)
        {
            for(int j=0;j<dna[i].length();j++)
            {
                dnaArray[i][j] = dna[i].charAt(j);
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                //Validacion de Verticales
                if(i<=n-4)
                {
                    if(dnaArray[i][j]==dnaArray[i+1][j] && 
                          dnaArray[i+1][j]==dnaArray[i+2][j] &&
                          dnaArray[i+2][j]==dnaArray[i+3][j])
                    {
                        // <editor-fold defaultstate="collapsed" desc="codigoExtraParaImpresionDeLineasMutantes">
                             dnaMutante.add(dnaArray[i][j]+""+dnaArray[i+1][j]+""+dnaArray[i+2][j]+""+dnaArray[i+3][j]+
                                " VV: "+
                                i+","+j+"; "+(i+1)+","+(j)+"; "+(i+2)+","+(j)+"; "+(i+3)+","+(j)+"; ");
                        // </editor-fold>
                        contadorADN++;
                    }
                }
                if(j<=m-4)
                {
                    //Validacion de Horizontales
                    if(dnaArray[i][j]==dnaArray[i][j+1] && 
                          dnaArray[i][j+1]==dnaArray[i][j+2] &&
                          dnaArray[i][j+2]==dnaArray[i][j+3])
                    {
                        // <editor-fold defaultstate="collapsed" desc="codigoExtraParaImpresionDeLineasMutantes">
                        dnaMutante.add(dnaArray[i][j]+""+dnaArray[i][j+1]+""+dnaArray[i][j+2]+""+dnaArray[i][j+3]+
                                " HH: "+
                                i+","+j+"; "+i+","+(j+1)+"; "+i+","+(j+2)+"; "+i+","+(j+3)+"; ");
                        //</editor-fold>
                        contadorADN++;
                    }
                }
                
                if(j<=m-4 && i<=n-4)
                {
                    //Validacion de Oblicuas 1
                    if(dnaArray[i][j]==dnaArray[i+1][j+1] && 
                                dnaArray[i+1][j+1]==dnaArray[i+2][j+2] &&
                                dnaArray[i+2][j+2]==dnaArray[i+3][j+3])
                    {
                        // <editor-fold defaultstate="collapsed" desc="codigoExtraParaImpresionDeLineasMutantes">
                        dnaMutante.add(dnaArray[i][j]+""+dnaArray[i+1][j+1]+""+dnaArray[i+2][j+2]+""+dnaArray[i+3][j+3]+
                                " O1: "+
                                i+","+j+"; "+(i+1)+","+(j+1)+"; "+(i+2)+","+(j+2)+"; "+(i+3)+","+(j+3)+"; ");
                        //</editor-fold>
                        contadorADN++;
                    }
                }
               
            }
            if(i<=n-4)
            {
                //Validacion de Oblicuas 2
                for(int j=m-1; j>=3; j--)
                {
                    if(dnaArray[i][j]==dnaArray[i+1][j-1] && 
                          dnaArray[i+1][j-1]==dnaArray[i+2][j-2] &&
                          dnaArray[i+2][j-2]==dnaArray[i+3][j-3])
                    {
                        // <editor-fold defaultstate="collapsed" desc="codigoExtraParaImpresionDeLineasMutantes">
                        dnaMutante.add(dnaArray[i][j]+""+dnaArray[i+1][j-1]+""+dnaArray[i+2][j-2]+""+dnaArray[i+3][j-3]+
                                " O2: "+
                                i+","+j+"; "+(i+1)+","+(j-1)+"; "+(i+2)+","+(j-2)+"; "+(i+3)+","+(j-3)+"; ");
                        //</editor-fold>
                        contadorADN++;
                    }
                }
            }
        }
        if(contadorADN>1)
        {
             esMutante = true;
        }
        // <editor-fold defaultstate="collapsed" desc="codigoExtraParaImpresionDeLineasMutantes">
        System.out.println("Lineas Mutantes: "+dnaMutante.size());
        for(String s : dnaMutante)
        {
            System.out.println(s);
        }
        //</editor-fold>
        return esMutante;
    }
}

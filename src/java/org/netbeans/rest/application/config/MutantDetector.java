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
        if(n<4 || m <4)
            //Si el tamaño del dna es menor a 4, entonces no es mutante.
        {
            return false;
        }
        
        //Conversion a Arreglo Bidimensional
        for(int i=0;i<n; i++)
        {
            for(int j=0;j<dna[0].length();j++)
            {
                dnaArray[i][j] = dna[i].charAt(j);
                //System.out.println("Arr: ["+i+"]["+j+"] = "+dnaArray[i][j]);
                System.out.print(dnaArray[i][j]);
            }
            System.out.println("");
        }
        
        //Validacion de Horizontales
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=m-4; j++)
            {
                if(dnaArray[i][j]==dnaArray[i][j+1] && 
                      dnaArray[i][j+1]==dnaArray[i][j+2] &&
                      dnaArray[i][j+2]==dnaArray[i][j+3])
                {
                    dnaMutante.add(dnaArray[i][j]+dnaArray[i][j+1]+dnaArray[i][j+2]+dnaArray[i][j+3]+
                            " Posiciones: "+
                            i+","+j+"; "+i+","+(j+1)+"; "+i+","+(j+2)+"; "+i+","+(j+3)+"; ");
                    esMutante = true;
                }
            }
        }
        
        //Validacion de Verticales
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<=n-4; j++)
            {
                if(dnaArray[i][j]==dnaArray[i][j+1] && 
                      dnaArray[i][j+1]==dnaArray[i][j+2] &&
                      dnaArray[i][j+2]==dnaArray[i][j+3])
                {
                    dnaMutante.add(dnaArray[i][j]+dnaArray[i][j+1]+dnaArray[i][j+2]+dnaArray[i][j+3]+
                            " Posiciones: "+
                            j+","+i+"; "+(j+1)+","+(i)+"; "+(j+2)+","+i+"; "+(j+3)+","+i+"; ");
                    esMutante = true;
                }
            }
        }
        
        //Validacion de Oblicuas 1.
        for(int i=0; i<=n-4; i++)
        {
            for(int j=0; j<=m-4; j++)
            {
                if(dnaArray[i][j]==dnaArray[i+1][j+1] && 
                      dnaArray[i+1][j+1]==dnaArray[i+2][j+2] &&
                      dnaArray[i+2][j+2]==dnaArray[i+3][j+3])
                {
                    dnaMutante.add(dnaArray[i][j]+dnaArray[i+1][j+1]+dnaArray[i+2][j+2]+dnaArray[i+3][j+3]+
                            " Posiciones: "+
                            i+","+j+"; "+(i+1)+","+(j+1)+"; "+(i+2)+","+(j+2)+"; "+(i+3)+","+(j+3)+"; ");
                    esMutante = true;
                }
            }
        }
        
        //Validacion de Oblicuas 2.
        for(int i=0; i<=n-4; i++)
        {
            for(int j=m-1; j>=3; j--)
            {
                if(dnaArray[i][j]==dnaArray[i+1][j-1] && 
                      dnaArray[i+1][j-1]==dnaArray[i+2][j-2] &&
                      dnaArray[i+2][j-2]==dnaArray[i+3][j-3])
                {
                    dnaMutante.add(dnaArray[i][j]+dnaArray[i+1][j-1]+dnaArray[i+2][j-2]+dnaArray[i+3][j-3]+
                            " Posiciones: "+
                            i+","+j+"; "+(i+1)+","+(j-1)+"; "+(i+2)+","+(j-2)+"; "+(i+3)+","+(j-3)+"; ");
                    esMutante = true;
                }
            }
        }
        
        System.out.println("Lineas Mutantes");
        for(String s : dnaMutante)
        {
            System.out.println(s);
        }
        
        return esMutante;
    }

}

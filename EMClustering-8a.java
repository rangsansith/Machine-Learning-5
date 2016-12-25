package assignment6infosec;

import java.util.Scanner;
import java.math.BigDecimal;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EMClusteringconverge {

	 public static void main (String args[])
	  {
		  double [] X     = { 8 , 5 , 9 , 4 , 7 };
		  double PAH      =  0.6  ;
		  double PBH      = 0.5  ;
		  double[] theta  = {0.6 , 0.5 };
		  double [] tow   = { 0.7 , 0.3 } ;
		 // double [] towrecomp = new double [2];
		  float[][] p     = new float [2][5] ; 
		  double [][] pj = new double [2][5] ; 
		  
   for (int z=0;z<45;z++)	
   {   
		  
		  for(int i=0;i<5;i++)
		 {
			 for(int j=0;j<2;j++)
			 {
			   p[j][i] =(float)(  tow[j]*(Math.pow(theta[j],X[i])) * (Math.pow(1-theta[j],(10-X[i])))   /
					   
					  ( tow[0]*(Math.pow(theta[0],X[i])) * (Math.pow(1-theta[0], (10-X[i]))) + 
					   
					   tow[1]*(Math.pow(theta[1],X[i]))*(Math.pow(1-theta[1],(10-X[i])))    )    ); 
			 }
		 } 
		//----------------------------------------------------------  
		//  pj[1][1] = (float) ( 0.7 * ((Math.pow(0.6, 8)) * (Math.pow(0.4, 2)))  / (0.7)*(   (Math.pow(0.6, 8)) * (Math.pow(0.4, 2)) ) + (0.3)*(  (Math.pow(0.5, 8))* (Math.pow(0.5, 2))   )  ); 
		// pj[1][1] =  ( 0.7 * (   (Math.pow(0.6, 8))  *   (Math.pow(0.4, 2))  ) )  / ( 0.7 * ((Math.pow(0.6, 8)) * (Math.pow(0.4, 2)) ) + 0.3 *  ((Math.pow(0.6, 8))* (Math.pow(0.4, 2))  )  );
		 //System.out.println("kdjfkdsjfksfjksdfkd"+ pj[1][1]);
		// System.out.println(    "---))))" +   0.7 * (   (Math.pow(0.6, 8))  *   (Math.pow(0.4, 2))   )  /  0.7 * ((Math.pow(0.6, 8)) * (Math.pow(0.4, 2)) ) + 0.3 *  ((Math.pow(0.6, 8))* (Math.pow(0.4, 2))   );
		 //--------------------------------------------------------- 
		
		  System.out.println("The M part is ....");  
		 // System.out.println("------------"+pj[1][1]);
		 for(int i=0;i<5;i++)
		 {
			 for(int j=0;j<2;j++)
			 {
				 System.out.println(p[j][i]);
			 }
			 System.out.println(" ");
		 }  
		 //------------------------------------
	    double sigmaodd = 0;
	    double sigmaeven = 0; 
	    //double [] towrecomp = new double [2];
	    double [] mu = new double [2];
	    //------------------------------------
	    for(int j=0;j<1;j++)
	    {
	    	for (int i=0;i<5; i++ )
	    	{
	    		sigmaodd = sigmaodd + p[j][i];
	    	}
	    }
	    tow[0]= sigmaodd/5; 
	    
	    for(int j=1;j<2;j++)
	    {
	    	for (int i=0;i<5; i++ )
	    	{
	    		sigmaeven = sigmaeven + p[j][i];
	    	}
	    }
	    tow[1]= sigmaeven/5 ;
	     System.out.println("Recomputed Values of tow are .....");
	    
	     for(int i =0 ;i<2;i++)
	     {
	    	 System.out.println(String.format("%.4f", tow[i]));
	     }
	  
	     //-----------Finding mu -----------------------------------//
	     double numeratorodd = 0 ;
	    double numeratoreven = 0;
	     
	     for(int j=0;j<1;j++)
	     {
	     	for (int i=0;i<5; i++ )
	     	{
	     		numeratorodd=numeratorodd+p[j][i]*X[i];
	     		
	     	}
	     }
	     for(int j=1;j<2;j++)
	     {
	     	for (int i=0;i<5; i++ )
	     	{
	     		numeratoreven=numeratoreven+p[j][i]*X[i];	
	     	}
	     }
	     mu[0]=numeratorodd/sigmaodd ; 
	     mu[1]= numeratoreven/sigmaeven; 
	    
	     System.out.println("  ");
	     System.out.println("Values for mu are conmputed as ......");
	     for(int i =0 ;i<2;i++)
	     {
	    	 System.out.println(String.format("%.4f", mu[i]));
	     }
	     
	     for(int i =0 ;i<2;i++)
	     {
	    	 theta[i] = mu[i]/10 ;
	     }
	     
	     System.out.println(" ");
	     System.out.println(" Theta is computed to the following  :D ");
	     for(int i =0 ;i<2;i++)
	     {
	    	 System.out.println(String.format("%.4f",theta[i]));
	     } 
	     System.out.println("++++++++++++++++++++++++++++++++++++++++++++++ ");
	     
	     
   }   
	  }

	
}

import java.util.ArrayList;
import java.util.Scanner;
import Jama.*;
import Jama.Matrix; 
import Jama.SingularValueDecomposition;
import java.text.*;
import java.math.*;
import java.text.DecimalFormat;
//-------------------------------------------------------------------
//Importing for line reading 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 





public class hmmwithmal {

	public static void main (String[] args ) throws FileNotFoundException
	{
		
		//N= number of states in the model = 2
		//M= number of observastion symbols=  27
		//matB = 27*2 
		// pie = 1*2 (1*N)
		
		double delta  = 0 ; 
		
		DecimalFormat df= new DecimalFormat("#.######");
		
		double[][] matA={{ (0.47468),  (0.52532)},{ (0.51656), (00.48344)}};
		
		double[][] matB={      {  (0.03735) ,   (0.03909)			},
				              {   (0.03408),    (0.03537)			},
				               {   (0.03455),(0.03537)			},
				               {   (0.03828) ,  (0.03909	)	},
				              {   (0.03782) ,  (0.03583) 		},
		                       {     (0.03922) ,  (0.03630)  	},
		                       {    (0.03688),     (0.04048)   	    } ,
		                       {     (0.03408) ,   (0.03537) 	},
		                          {   (0.03875) ,  (0.03816)  	},
		                          {   (0.04062) ,  (0.03909 )  	},
		                          {   (0.03735) ,  (0.03490)   	},
		                           {  (0.03968),  (0.03723 )   	},
		                           {  (0.03548) , (0.03537)   	},
		                           {  (0.03735) , (0.03909)    },
		                           {  (0.04062),  (0.03397)    },
		                          {   (0.03595),   (0.03397)  	},
		                           {  (0.03641) , (0.03816)  	},
		                           {  (0.03408) , (0.03676)   	},
		                           {  (0.04062),  (0.04048)     },
		                           {  (0.03548),   (0.03443)   	},
		                           {  (0.03922),   (0.03537 )  	},
		                           {  (0.04062) ,(0.03955 ) 	},
		                           {  (0.03455), (0.03816 )  	},
		                           {  (0.03595), (0.03723)   	},
		                           {  (0.03408), (0.03769)   	},
		                           {  (0.03408), (0.03955)   	}  ,
		                           
		                           {  (0.03688) , (0.03397 )    }   } ;
		
		Matrix         matB1 =         new Matrix(matB);
		double[][]     Btrans =        matB1.transpose().getArrayCopy();
		double[]       matpie=         {0.51316  ,0.48684 };
		int            minIters=       100;                                               //given
		int            iters=          0 ;                                               //given
		double         oldLogProb=     Double.NEGATIVE_INFINITY;                        //given
	    double         logProb =       0                  ; 
		
		//for 1 file of zeroaccess
	    //---------------------------------------
	    Scanner s = new Scanner(new File ("C:\\textbooks\\266 mark stamp\\project'\\ZA.txt") );              
	   ArrayList<String> list = new ArrayList<String>();
     	  while (s.hasNext()){
	      list.add(s.next());
	   }
	   //s.close();
	    
	    System.out.println(".,.," +list);
	    
	    String[] listarr = new String[list.size()];
	    listarr = list.toArray(listarr);
	    
/*	    System.out.println("The array of Input OP-CODES");
	    
	    for(int i=0;i<listarr.length ; i++)
	    {
	    	System.out.println(" "+listarr[i]+" ");
	    }
	    
	    */
	///dID U ATTACH A NUMBER VAL;UE TO THE 26 OPCODES SELECTED? IF YES , WHAT VALUE PUT FOR OP-CODES IN SEQUENCE WHICH ARE NOT INT 
	    
	    //----------------------------------------------------------
	    
	    String[] trial = { "push",  "mov",  "sub", "mov", "adc", "mov", "mov", "or", "mov", "sub", "push", 
	    		 "mov", "mov"," mov","mov", "sbb", "mov", "and", "mov",  "or", "mov", "mov","add","or", "mov", "add", "mov"};
	    
	//	String[]  O ={"a","b","c","d","e", "f","g","h","i","j","k","l","m","n","o","p",
		//		       "q","r","s","t","u","v","w","x","y","z"," "} ;              // observations   
		
		int T= 27  ;                                                             // length of observation sequence 
		
		
		int T2=30; // new T 
		
		double [] c = new double[T];           //T was 27                        // temp ARRAY of size T
		
		double[][] alpha =new double[T][2];   //T was 27 
		double [][] beta =new double[T][2];       //T was 27 
		double [][][] gamma = new double [T][2][2];     //T was 27 
		double [][] gammabad = new double [T][2];          //T was 27                  //used in the gamma computation step  
		
		//---------------------------------------------------------------------------
        //----HMM STARTS
		System.out.println("infinity "+ oldLogProb);
		
		System.out.println("size : "+ Btrans.length);
		
		for(int i =0 ; i<Btrans.length ; i++)
		{
			for(int j=0; j<Btrans[i].length;j++)                          
			{
				System.out.print("     "+df.format(Btrans[i][j]));              //Printing matrix without transpose in text (original matrix)
			}
			System.out.println(" ");
		}
		//-----------------------------------------------------------------------------------------
	    //Forward Algorithm starts 
        double timer = 10 ;
        for(int zzz=0 ; zzz< 500 ; zzz++)
    {                                                            //open do 
		 
		c[0]=0;
		for(int i=0; i<2; i++)
		{	
			alpha[0][i]= matpie[i]*Btrans[i][(0)] ;
			c[0]=c[0]+alpha[0][i];
			//System.out.println("First value of alpha:"+alpha[0][i]);
		}
		//performing scaling over alpha[0][i] 
		   
		c[0] = 1/ c[0];
		for(int i=0 ; i<2; i++)
		{
			alpha[0][i]= c[0] * alpha[0][i];
			System.out.println("First value of alpha:"+alpha[0][i]);
		}
		
		//computing alpha[t][i] : 
		for(int t=1; t <T ; t++)///-----------CHANGEDbrr(27)
		{
			c[t]=0;
			for(int i=0 ; i<2;i++)
			{
				alpha[t][i]=0;
				for(int j=0;j<2;j++)
				{
					
					alpha[t][i]=alpha[t][i]+alpha[t-1][j]* ( matA[j][i])  ;
				}
				
				alpha[t][i]= alpha[t][i]*(Btrans[i][t] );                 //as in text (O[o]);  
				c[t]=c[t]+ alpha[t][i];
			}
			
			//scale alpha[t][i]
			c[t]=1/c[t]   ; 
			
			for(int i=0;i<2;i++)
			{
				alpha[t][i] = c[t] * alpha[t][i];
				System.out.println(" alpha" + "[ "+t+" ]"+ "[ "+i+" ]" + String.format("  %5f", alpha[t][i] ) );
			}
			System.out.println(" " );
		}  
		System.out.println(" " );	
	//---------------------------------------------------------------------------------------------
    //Backward Algorithm 
		
		for (int i = 0; i<2 ; i++ )
		{
			beta[(T-1)][i]= c[T-1];
		}
		//Beta Pass
		System.out.println("Beta Pass");
		for( int t=(T-2);t>0; t--)   /// CHANGED FROM 26 TO t-2 
		{
			for(int i=0;i<2;i++)
			{
				beta[t][i]=0;
				for(int j=0;j<2;j++)
				{
					
					beta[t][i]= beta[t][i]+ matA[i][j]*(Btrans[j][t+1])* (beta[t+1][j]) ;
					
				}
				//scale beta[t][i] with the same scale factor as alpha[t][i]
				beta[t][i]=c[t] * beta[t][i];
				System.out.println(" beta" + "[ "+t+" ]"+ "[ "+i+" ]" + String.format("  %5f", beta[t][i] ) );
			}
			System.out.println(" " );
		}
		System.out.println(" " );	
		//Compute the gamma[t][i][j] and gamma[t][i]
		
		for(int t=0 ; t<(T-1) ;t++)
				
		    {
			     double denom = 0;
			     for(int i=0;i<2;i++)
			     {
			    	 for(int j=0 ; j<2;j++)
			    	 {
			    		 denom= (denom) + (alpha[t][i]) * (matA[i][j] )* (Btrans[j][t+1])* (beta[t+1][j]);
			    		 
			    	 }
			     }
			     for(int i=0;i<2;i++)
			     {
			    	 gammabad[t][i]= 0;
			        for(int j= 0 ;j<2;j++)
			    	 {
			    		 gamma[t][i][j]=  (  (alpha[t][i]) * (matA[i][j]) * (Btrans[j][t+1]) * (beta[t+1][j])  / (denom)   );
			    		
			    		//System.out.println((" GAMMA" + "[ "+t+" ]"+ "[ "+i+" ]" + String.format("  %5f", gamma[t][i][j] ) ));
			    	    
			    		 gammabad[t][i]=gammabad[t][i]+ gamma[t][i][j];
			    	  
			    	     System.out.println((" GAMMA" + "[ "+t+" ]"+ "[ "+i+" ]" + String.format("  %5f", gammabad[t][i] ) ));
			    	 }
			     }
		     }
		//Special case gammaT-1(i)
		double denom = 0 ;
		for(int i =0 ; i < 2; i++)
		{
			
			denom = denom +  alpha[T-1][i];
		}
		for(int i = 0 ; i < 2; i++)
		{
			gammabad[T-1][i] =  alpha[T-1][i]/denom   ;
		}
		
		//6 . Re-estimate pie 
		
		for(int i = 0 ;i<2;i++)
		{
			matpie[i] = gammabad[0][i] ;
		}
		double numer = 0 ; 
		//Re-estimate A 
		for(int i=0;i<2;i++)
		{
			for(int j =0 ; j<2 ; j++)
			{
				numer = 0 ;
				denom =0 ;
				for(int t=0 ; t<(T-2) ; t++)          ////please check 
				{
					numer = numer + gamma[t][i][j];
					denom = denom + gammabad[t][i];
				}
				matA[i][j]  =  numer / denom ;
			}
		}
		//Re-estimate B 
		
		for(int i =0 ; i<2 ; i++)
		{
			for(int j = 0 ; j< T; j ++)//.............CHANGEDbrr
			{
				numer = 0  ;
				denom = 0  ;
			
			for(int t=0 ;t<27 ; t++ )   ///------changed from 26 to T-2 brrrr
			{
				if(t==j)                             //check , in algo O[t]
				{
					numer= numer+ gammabad[t][i];
				}
				denom = denom + gammabad[t][i];
			}
			Btrans[i][j] =  numer / denom ;
		     }
		}
		
		//--------------Printing Btrans again (Re-estimated )
		
        System.out.println("size of re-estimated B : "+ Btrans.length);
		
		for(int print =0 ; print<Btrans.length ; print++)
		{
			for(int print2=0; print2<Btrans[print].length;print2++)                          
			{
				System.out.print(" B re-estimated     "+df.format(Btrans[print][print2]));              //Printing matrix without transpose in text (original matrix)
			}
			System.out.println(" ");
		}
		
		//Compute log (P(O/lamda))
		
		logProb = 0 ;
		for(int i=0 ; i< 2; i++)
		{
			logProb= logProb + Math.log(c[i]); 
		}
		logProb = -logProb ;
    
		//To iterate or not to iterate , that is the question  
		
		iters = iters + 1;
		double qqq;
		qqq= logProb - oldLogProb;
		System.out.println();
		delta  =Math.abs(qqq) ;
		
		if(  (iters <minIters+1)  || (delta > Math.E) )
		{
			oldLogProb = logProb ;
			timer = timer+1;
			System.out.println("The timer is "+ timer );
 //       while(oldLogProb == logProb);
		}
		else
		{
			timer = timer* (-1);
		}
		

	}             //close while 
	
	System.out.println("The score for the set is "+ logProb );
	//------ended without printing all 3 matrices -----// 
	
	}	
}






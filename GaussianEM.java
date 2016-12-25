package assignment6infosec;
import java.util.ArrayList;
import java.util.Scanner;
import Jama.*;

public class GaussianEM {
	
	public static void main (String args[])
	{
	    double [] tow   = { 0.5704 , 0.4296 } ;
	  
		double [][] theta1 = {{2.6269 , 1.0548 , 12.7306 },{63.0160, 12.7306 , 181.5183}};
		
		double [][] theta2 = { {3.6756 , 1.2119 , 14.1108}, {75.1981, 14.1108 , 189.2046} }; 
		
		double [][] S1 = {{1.0548 , 12.7306 },{12.7306, 181.5183}}; 
		double [][] S2 = {{1.2119, 14.1108}, {14.1108, 189.2046} } ;
		
		double[] mu1  =  {2.6269 , 63.0160} ;
		double[] mu2  =  { 3.6756 , 75.1981  };
		
		double[] [] p = new double [2][20];
				
		double[][] X={{3.600,79},{1.800,54} , {2.283,62 }, {3.333,74} , {2.883,55},{4.533,85},{1.950,51} ,{1.833,54},{4.700,88}
		,{3.600,85},{1.600,52},{4.350,85},{3.917,84},{4.200,78},{1.750,62},{1.800,51},{4.700,83},{2.167,52},{4.800,84},{1.750,47}};
		
	//Matrix zzzzz = sss.times(yyy));
	//	System.out.println(sss.times(yyy));
		
		double temp1;
		ArrayList pow1 = new ArrayList();
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<2;j++)
			{
				temp1=X[i][j]-mu1[j]; 
				pow1.add(temp1);
			}
			
		}
		System.out.println(" The difference array for x and mu1 is "+pow1);		
		System.out.println(pow1.size());
		
		double temp2;
		ArrayList pow2 = new ArrayList();
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<2;j++)
			{
				temp2=X[i][j]-mu2[j]; 
				pow2.add(temp2);
			}	
		}
		System.out.println("The difference array for x and mu2 is "+pow2);		
		System.out.println(pow2.size());
		
		//Initializing the differences to matrices for faster computation (Matrices obtained in pow1 and pow2 )
		
		double[][] mat11= {{0.9731000000000001, 15.984000000000002  }};
		double[][] mat12=  {{ -0.8269, -9.015999999999998}};
		double[][] mat13=  {{-0.3439000000000001, -1.0159999999999982 }};
		double[][] mat14=  {{ 0.7061000000000002, 10.984000000000002}};
		double[][] mat15=  {{  0.2561, -8.015999999999998}};
		double[][] mat16=  {{  1.9061000000000003, 21.984}};
		double[][] mat17=  {{-0.6769000000000001, -12.015999999999998 }};
		double[][] mat18=  {{-0.7939, -9.015999999999998 }};
		double[][] mat19=  {{2.0731, 24.984	 }};
		double[][] mat110=  {{0.9731000000000001, 21.984 }};
		double[][] mat111=  {{-1.0269, -11.015999999999998 }};
		double[][] mat112=  {{ 1.7230999999999996, 21.984}};
		double[][] mat113=  {{ 1.2900999999999998, 20.984}};
		double[][] mat114=  {{ 1.5731000000000002, 14.984000000000002	}};
		double[][] mat115=  {{-0.8769, -1.0159999999999982 }};
		double[][] mat116=  {{ -0.8269, -12.015999999999998	}};
		double[][] mat117=  {{ 2.0731, 19.984	}};
		double[][] mat118=  {{  -0.4599000000000002, -11.015999999999998}};
		double[][] mat119= {{  2.1731, 20.984		}};
		double[][] mat120 = {{-0.8769, -16.016 }};
	//-----------------------------------------------------------	
		double[][] mat21= {{-0.07560000000000011, 3.8019000000000034 }};
		double[][] mat22=  {{ -1.8756000000000002, -21.198099999999997}};
		double[][] mat23=  {{ -1.3926000000000003, -13.198099999999997}};
		double[][] mat24=  {{ -0.3426, -1.1980999999999966 }};
		double[][] mat25=  {{-0.7926000000000002, -20.198099999999997 }};
		double[][] mat26=  {{0.8574000000000002, 9.801900000000003 }};
		double[][] mat27=  {{ -1.7256000000000002, -24.198099999999997}};
		double[][] mat28=  {{ -1.8426000000000002, -21.198099999999997}};
		double[][] mat29=  {{	1.0244, 12.801900000000003 }};
		double[][] mat210=  {{-0.07560000000000011, 9.801900000000003	 }};
		double[][] mat211=  {{ -2.0756, -23.198099999999997 }};
		double[][] mat212=  {{0.6743999999999994, 9.801900000000003	 }};
		double[][] mat213=  {{ 0.24139999999999961, 8.801900000000003	}};
		double[][] mat214=  {{ 0.5244, 2.8019000000000034		}};
		double[][] mat215=  {{ -1.9256000000000002, -13.198099999999997}};
		double[][] mat216=  {{ -1.8756000000000002, -24.198099999999997	}};
		double[][] mat217=  {{ 1.0244, 7.801900000000003	}};
		double[][] mat218=  {{ -1.5086000000000004, -23.198099999999997}};
		double[][] mat219= {{  1.1243999999999996, 8.801900000000003	}};
		double[][] mat220 = {{  -1.9256000000000002, -28.198099999999997  }};
		
		
		
//	____________________________________________________________________________________________________________	
		//     p[j][i] = tow[j] ( f(xi, theta[j]) / tow[1] *f(xi , theta [1] ) + tow[2] *f(x[i], theta[2])
		
	   double detS1;
	   double detS2;
	   
	   detS1 = (S1[0][0] * S1[1][1] ) - ( S1[0][1] * S1[1][0]  ); 
	   detS2 = (S2[0][0] * S2[1][1] ) - ( S2[0][1] * S2[1][0]  ); 
	   
	   
	   System.out.println("Determinant S1 = "+ String.format("%4f", detS1));
	   System.out.println("Determinant S2 = "+ String.format("%4f", detS1));
	   System.out.println(Math.E);
	   
	   ArrayList func1 = new ArrayList();
	   double  d1 ; 
	   for(int i=0;i<20;i++)
	   {
		d1=  (Math.pow(Math.E, -0.5 * (X[i][0] - mu1[0]  )*(1/detS1)*(X[i][1]- mu1[1] )) ) / ( (2*3.14) * Math.sqrt(detS1) ) ; 
		System.out.println(d1);
	    func1.add(d1);
	   }
	   
	   ArrayList func2 = new ArrayList();
	   double  d2 ;
	   
	   for(int i=0;i<20;i++)
	   {
		d2=  (Math.pow(Math.E, -0.5*(X[i][0] - mu1[0])*(1/detS1)*(X[i][1]- mu1[1])) ) / ( (2*3.14) * Math.sqrt(detS2) ) ; 
		System.out.println(d2);
	    func2.add(d2);
	   }
	   
	}
}

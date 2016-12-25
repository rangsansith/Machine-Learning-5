package assignment6infosec;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;


public class kmeans1 {
	
	public static void main(String args[])
	{
       double[] data={3.600,79,1.800,54 , 2.283,62 , 3.333,74 , 2.883,55,4.533,85,1.950,51 ,1.833,54,4.700,88,3.600,85,1.600,52,4.350,85,3.917,84,4.200,78,1.750,62,1.800,51,4.700,83,2.167,52,4.800,84,1.750,47};
       
       int k=2 ;  
	   //-------------
	   double[] centroid1={2.66,60.66};
	   double[] centroid2={3.7332,49.133};
	   //-----------------
	   int size= data.length/k;
	   
	   ArrayList distance1 =new ArrayList();
	   for(int i=0;i<data.length;i=i+2)
	   {
		   float d1 ;
		   
		   d1 = (float) Math.sqrt((2.66-data[i])*(2.66-data[i])+(60.66-data[i+1])*(60.66-data[i+1])) ;
		   
		   distance1.add(d1);
	   }
     System.out.println("The distance of points in the set from initial centroid 1 is ");
     System.out.println (distance1);
     ///-----------------End for the first centroid , doing same for centroid 2---------------------///
     ArrayList distance2 =new ArrayList();
     for(int i=0;i<data.length;i=i+2)
     {
    	 float d2 ;
    	 d2 = (float) Math.sqrt((3.7332-data[i])*(3.7332-data[i])+(49.133-data[i+1])*(49.133-data[i+1])) ;
    	 distance2.add(d2);
     }
     System.out.println("The distance of points in the set from initial centroid 2 is ");
     System.out.println (distance2);
    // System.out.println (distance2.size());
   ///-----------------End of distance for centroid 2---------------------///
     
     ArrayList cluster1 =new ArrayList();
     ArrayList cluster2 =new ArrayList();
         
     for(int i=0;i<distance1.size();i++)
     {
         if((float)distance1.get(i) < (float)distance2.get(i) )
         {
        	 if(i==0)
        	 {
        		 cluster1.add(data[i]);
        		 cluster1.add(data[i+1]);
        	 }
        	 else
        	 {
        	 cluster1.add(data[i*2]);
        	 cluster1.add(data[(i*2)+1]);
        	 }
         }
         else
         {
        	 
        	 if(i==0)
        	 {
        		 cluster2.add(data[i]);
        		 cluster2.add(data[i+1]);
        	 }
        	 else
        	 {
        	 cluster2.add(data[i*2]);
        	 cluster2.add(data[(i*2)+1]);
        	 }   	 
        	 
        	// cluster2.add(data[i]);
         }     
     }
         
         System.out.println(("Cluster 1 contains the points...."));
         System.out.println(cluster1);
         System.out.println(("Cluster 2 contains the points ...."));
         System.out.println(cluster2);
         

         
         
     
	}	
}

import matplotlib.pyplot as plt
import numpy as np

import math


U = np.array( [(0.1641 , 0.2443 , -0.0710) ,
              (0.6278, 0.1070 , 0.2934 ),
              (-0.2604 , -0.8017 , 0.3952),
              (-0.5389 , 0.4277, 0.3439 ),
              (0.4637 , -0.1373 , 0.3644 ),
              (0.0752 , -0.2904 , -0.7083)]  )
print(U.shape)


S= np.array(   [ (4.0414,0,0) , (0,2.2239,0) , (0,0,1.7237) ] )
print (S.shape)

V = (   (-0.2739 , 0.6961 , -0.4364 ) ,
         (0.3166 , 0.2466 , 0.7674   ),
         (-0.6631 , -0.5434 , 0.1224 ),
         (0.6205 , -0.3993 , -0.4534 )
)

#print (U.dot(S))

intermediate =  np.array (  (U.dot(S) )  )
print ('U INTO S')
print (intermediate)
print (intermediate.shape)

print("US VT ")

final = intermediate.dot(np.transpose(V))


np.set_printoptions(precision=7,suppress=True)
print  (final)
print(final.shape)
print("-----------------------------------------------------------")
print (" ===========  2 nd part ===================")



print('STARTS ')
A2 = np.array(final)

#A = np.array(final)
#print(A2)
#print(A2.shape)
print("++++++++++++++++++++++++++++++++++++++++++++++++++++++")
A2trans=np.array(np.transpose(A2))
#print(A2trans)
#print (A2trans.shape)
C =  np.array( ( (np.dot(A2,A2trans)))*(1/4) )
#print('intermediate2')
print("Covariance matrix C = 1/n AAtranspose")
print(C)

'''
print (" C = 1/n AA(transpose)    ")
C = np.array((1/4 * (intermediate2)))
print (C)
'''

C_Eigenvalues, C_eigenvectors = np.linalg.eig(C)
print("\n eigenvalue matrix of C: "+str(np.array(C_Eigenvalues)))
print("\neigenvector matrix of C: "+str(np.array(C_eigenvectors)))


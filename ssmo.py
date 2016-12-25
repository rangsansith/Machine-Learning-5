# Single sequential minimal optimization

import numpy as np
from random import randint, seed
from matplotlib import pyplot as plt

training_data = np.array([
    [3,3],
    [3,4],
    [2,3],
    [1,1],
    [1,3],
    [2,2]
])

color_map = {1: "red", -1: "blue"}

labels = [1,1,1,-1,-1,-1]

def get_fixed_index_sequence():
    lambda_indices_sequence = np.array([
        [1,2], [2,3], [3,4], [4,5], [5,6],
        [1,3], [2,4], [3,5], [4,6],
        [1,4], [2,5], [3,6],
        [1,5], [2,6],
        [1,6],
        [2,1], [3,2], [4,3], [5,4], [6,5],
        [3,1], [4,2], [5,3], [6,4],
        [4,1], [5,2], [6,3],
        [5,1], [6,2],
        [6,1]
    ])
    # Creating 0 indices
    lambda_indices_sequence = lambda_indices_sequence - [1,1]
    return lambda_indices_sequence

def get_random_index_sequence():
    index_sequence = np.zeros((1000,2), dtype=int)
    for _ in range(1000):
        for i in range(6):
            # setting seed to get not so random results
            seed(i)
            j = randint(0,5)
            while j == i:
                j = randint(0,5)
            index_sequence[i] = np.array([i, j])
    return index_sequence

def score(X, training_data, labels, lambdas, b):
    result = 0.0
    for i in range(0, len(training_data)):
        result += lambdas[i] * labels[i] * (np.dot(training_data[i], X))
    return result + b


def ssmo(training_data, labels, c, epsilon, are_lambda_indices_fixed=True):
    size = len(training_data)
    lambdas = [0] * size
    b = 0

    # Indice selection
    if are_lambda_indices_fixed:
        index_sequence = get_fixed_index_sequence()
    else:
        index_sequence = get_random_index_sequence()

    for i in range(10):
        lambda_change = -1

        if lambda_change == False:
            break

        for (i,j) in index_sequence:
            X_i = training_data[i]
            X_j = training_data[j]

            d = 2 * (np.dot(X_i, X_j)) - np.dot(X_i, X_i) - np.dot(X_j, X_i)

            if abs(d) > epsilon:
                e_i = score(X_i, training_data, labels, lambdas, b) - labels[i]
                e_j = score(X_j, training_data, labels, lambdas, b) - labels[j]
                lambda_i_temp = lambdas[i]
                lambda_j_temp = lambdas[j]
                lambdas[j] = lambdas[j] - (labels[j] * (e_i - e_j)/d)
                if labels[i] == labels[j]:
                    l = max(0, lambdas[i] + lambdas[j] - c)
                    h = min(c, lambdas[i] + lambdas[j])
                else:
                    l = max(0, lambdas[j] - lambdas[i])
                    h = min(c, c + lambdas[j] - lambdas[i])

                # determining lambda_j
                if lambdas[j] > h:
                    lambdas[j] = h
                elif lambdas[j] < l:
                    lambdas[j] = l

                lambdas[i] = lambdas[i] + labels[i] * labels[j] * (lambda_j_temp - lambdas[j])

                if lambdas[i] != lambda_i_temp:
                    lambda_change = True
                elif lambda_change != True and lambdas[i] == lambda_i_temp:
                    lambda_change = False


                b_i = b - e_i - labels[i] * (lambdas[i] - lambda_i_temp) \
                    * (np.dot(X_i, X_i)) - labels[j] * (lambdas[j] - lambda_j_temp) \
                    * (np.dot(X_i, X_j))

                b_j = b - e_j - labels[i] * (lambdas[i] - lambda_i_temp) \
                    * (np.dot(X_i, X_j)) - labels[j] * (lambdas[j] - lambda_j_temp) \
                    * (np.dot(X_j, X_j))

                if 0 < lambdas[i] and lambdas[i] < c:
                    b = b_i
                elif 0 < lambdas[j] and lambdas[j] < c:
                    b = b_j
                else:
                    b = (b_i + b_j) / 2

    return (lambdas, b)

def plot_seperating_hyperplane(lambdas, b, training_data, labels):
    x_points = training_data.T[0]
    y_points = training_data.T[1]

    size = len(training_data)

    # w1
    w1 = 0
    for i in range(size):
        w1 += lambdas[i] * x_points[i] * labels[i]

    # w2
    w2 = 0
    for i in range(size):
        w2 += lambdas[i] * y_points[i] * labels[i]

    x_points = np.linspace(0,5)
    y_points = map(lambda x: (-b - (w1 * x))/w2, x_points)

    plt.plot(x_points, y_points, c="green")


# (lambdas, b) = ssmo(training_data, labels, 2.5, 0.00001)
#
# print lambdas
# print b
#
# plot_seperating_hyperplane(lambdas, b, training_data, labels)

(lambdas, b) = ssmo(training_data, labels, 2.5, 0.00001, False)

print (lambdas)
print (b)

plot_seperating_hyperplane(lambdas, b, training_data, labels)


plt.scatter(training_data[0:3].T[0], training_data[0:3].T[1], c="red", s=40, label="1")
plt.scatter(training_data[3:].T[0], training_data[3:].T[1], c="blue", s=40, label="-1")

plt.legend(loc='upper left', numpoints=1, ncol=2, fontsize=10, bbox_to_anchor=(0.00625, 0.0725))

plt.show()

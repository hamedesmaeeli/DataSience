from sklearn import datasets
from sklearn.cluster import KMeans
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
iris = datasets.load_iris()
print(iris.data)
print(iris.target)

# create a model object with 3 clusters
# http://scikit-learn.org/stable/modules/generated/sklearn.cluster.KMeans.html#sklearn.cluster.KMeans
# http://scikit-learn.org/stable/modules/clustering.html#k-means
model = KMeans(n_clusters=3)


print(model)

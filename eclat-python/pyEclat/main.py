#!pip install pyECLAT
import pandas as pd
import matplotlib.pyplot as plt

from pyECLAT import ECLAT

from pyECLAT import Example1, Example2

ex1 = Example1().get()
ex2 = Example2().get()

dataframe = pd.read_csv('./data/base2.csv', header=None)

print(dataframe)

eclat_instance = ECLAT(data=dataframe, verbose=True)  # verbose=True to see the loading bar

eclat_instance.df_bin  # generate a binary dataframe, that can be used for other analyzes.
eclat_instance.uniq_  # a list with all the names of the different items

print('generated a binary dataframe, that can be used for other analyzes:')
print(eclat_instance.df_bin)

print('a list with all the names of the different items:')
print(eclat_instance.uniq_)

get_ECLAT_indexes, get_ECLAT_supports = eclat_instance.fit(min_support=0.04,
                                                           min_combination=1,
                                                           max_combination=3,
                                                           separator=' & ', verbose=True)
                                                                 separator=' & ',

                                                                       print('Supports values of products')
print(get_ECLAT_supports)

# This function will search for all possible combinations that have support > 0.02 (in this case)
get_ECLAT_indexes_all, get_ECLAT_supports_all = eclat_instance.fit_all(min_support=0.02,

                                                                       verbose=True)
print('all possible combinations that have support > 0.02 (in this case)')
print(get_ECLAT_supports_all)


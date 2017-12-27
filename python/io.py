#! __author__ = "amoraitis"
from __future__ import print_function
import sys

print('Hello World')

name = input("What is your name? ")
print("Nice to meet you "+ name)

def size_clothes(height):
    if height > 190:
        return 'L'
    elif height < 160:
        return 'S'
    else:
        return 'M'

height = input("Give the height to calculate the size: ")
print(size_clothes(int(height)))
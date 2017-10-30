#include <iostream>
#include <vector>
#include <set>
#include <string>  

void printStringVector (const std::vector<std::string> &vector) {
	// Use an iterator to print the contents of the vector.
	// The iterator is const_iterator which means that we 
	// cannot use it to change the contents of the set.
	std::vector<std::string>::const_iterator cit;
	
	std::cout << std::endl;
	std::cout << "Strings contained in the vector:" << std::endl;
	std::cout << "--------------------------------" << std::endl;

	for (cit = vector.begin(); cit != vector.end(); ++cit) {
		std::cout << *cit << std::endl;
	}

	std::cout << "--------------------------------" << std::endl;
	std::cout << std::endl;
}


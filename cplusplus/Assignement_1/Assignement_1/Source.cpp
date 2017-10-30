using namespace std;
#include <iostream>
#include <math.h>
#include <string>
int Question1stub();
int Question2stub();
int Question4stub();
int Question5stub();
int Question6stub();
int main() {
	return Question6stub();
}
int Question1stub()
{
	// declare variable to hold integer value
	int value;

	// display welcome messages to user
	cout << "Welcome - This program reads an integer\n";
	cout << "and determines if it is odd or even\n\n";

	// TODO: Enter code to read integer and store in variable value
	cout << "Please enter an integer value -  ";
	// Put code here.....
	cin >> value;
	// TODO: Enter code to determine if integer is odd or even
	if (value % 2 == 0) {
		cout << "\n" << value << " is even";
	}
	else {
		cout << "\n" << value << " is odd";
	}
	// Put code here.....

	cout << "\nThanks and Goodbye";

	system("PAUSE");
	return EXIT_SUCCESS;
}
int Question2stub()
{
	// Variable declaration
	int firstInteger;
	int secondInteger;

	// display welcome messages to user
	cout << "Welcome - This program reads two integers\n";
	cout << "and determines if first is a multiple of second\n\n";

	// Read first integer
	cout << "Enter first integer value -  ";
	// Put code here.....
	cin >> firstInteger;
	// Read second integer
	cout << "Enter second integer value -  ";
	// Put code here.....
	cin >> secondInteger;
	// Determine if first is a multiple of second
	// Put code here.....
	if (firstInteger%secondInteger == 0) {
		cout << firstInteger << " is multiple of " << secondInteger;
	}
	else {
		cout << firstInteger << " is not multiple of " << secondInteger;
	}

	cout << "\nThanks and Goodbye";

	system("PAUSE");
	return EXIT_SUCCESS;
}
int main3()
{
	// TODO: Use multiple cout statements to print shapes as shown in assignment pdf file
	// Put code here ...
	//easy
	cout << "\nThanks and Goodbye";

	//system("PAUSE");
	return EXIT_SUCCESS;
}
int Question4stub()
{
	// Variable declaration
	int digits;

	// display welcome messages to user
	cout << "Welcome - This program reads a five-digit integer\n";
	cout << "separates the integer into its digits and prints them\n";
	cout << "separated by three spaces each\n\n";

	// TODO: Read integer using cin
	cout << "Enter five digit integer value -  ";
	// Put code here ...
	cin >> digits;
	// check if digit is a five digit integer
	if (to_string(digits).length() < 5 || to_string(digits).length() > 5)
	{
		cout << "\nERROR - Please enter a five digit integer";
	}
	else {
		// TODO: Separate the integer into its digits and prints them separated by three spaces each
		// Put code here ...
		cout << digits / 10000<<"   ";
		cout << digits / 1000 % 10 << "   ";
		cout << digits / 100 % 10 << "   ";
		cout << digits / 10 % 10<<"   ";
		cout << digits % 10<<"   ";
		
	}

	cout << "\nThanks and Goodbye";

	system("PAUSE");
	return EXIT_SUCCESS;
}
int Question5stub()
{

	// display welcome messages to user
	cout << "Welcome - This program calculates the squares and cubes\n";
	cout << "of the integers from 0 to 10 and prints using tabs\n\n";

	// TODO: Hint use pow(base, power) to calculate squares and cubes
	// Put code here ...
	cout << "integer\tsquare\tcube\n";
	for (int i = 0; i < 11; i++) {
		cout << i << "\t" << pow(i, 2) << "\t" << pow(i, 3) << "\n";
	}

	cout << "\nThanks and Goodbye";

	system("PAUSE");
	return EXIT_SUCCESS;
}
int Question6stub()
{
	// Variable declaration
	double weightInKilograms;
	double heightInMeters;
	double BMI;

	// display welcome messages to user
	cout << "Welcome - This Body Mass Index (BMI) calculator application that reads\n";
	cout << "the user’s weight in kilograms and height in meters,\n";
	cout << "calculates and display the user’s body mass index\n\n";

	// TODO: display information from the Department of Health and Human Services
	// Put code here ...
	cout << "BMI VALUES ETC";
	// TODO: read in values
	cout << "Enter weight (in kg): ";
	// Put code here ...
	cin >> weightInKilograms;
	cout << "Enter height (in meters): ";
	// Put code here ...
	cin >> heightInMeters;
	// TODO: calculate BMI
	// Put code here ...
	cout << "Your BMI: " << weightInKilograms / (heightInMeters*heightInMeters);
	// display result
	// Put code here ...

	cout << "\nThanks and Goodbye";

	system("PAUSE");
	return EXIT_SUCCESS;
}

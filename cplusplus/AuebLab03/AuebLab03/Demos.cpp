#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <set>
#include <vector>
#include <string.h>
#include <algorithm>

#include "Utils.h"

using namespace std;

void string_examples ( void ) {
	// For an extented set of examples with std::strings check this URL http://anaturb.net/C/string_exapm.htm
	char *line = "Some text";

	// Initialization 
	// Using a char *
    string s1 (line);
    cout << "s1  is: " << s1 << endl;

    // copy constructor
    string s2 (s1);
    cout << "s2  is: " << s2 << endl;
		
    // 1st arg - C++ string
    // 2nd arg - start position
    // 3rd arg - number of characters
    string s3 (s2,5,4); // copy word 'text' from s2
    cout << "s3  is: " << s3 << endl;

	// again using iterators
	// 1 - start iterator
    // 2 - end iterator
    string s4 (s2.begin()+5,s2.end());
    cout << "s4  is: " << s4 << endl;

	// We can add strings
	string s5 = s4 + " Some";
	cout << "s5  is: " << s5 << endl;

	// We can append to strings
	s5 += " And Some";
	cout << "s5  is: " << s5 << endl;
	// or by using append()
	s5.append(" And Again");
	cout << "s5  is: " << s5 << endl;

	// Iterate all characters
	string s6("Hello World");
	string::iterator it;
	cout << "s6 char-by-char: " << endl;
	// TODO: use the iterator to print the characters of the string

	// Get the const char * from a string
	const char *buf = s6.c_str();	// or .data()
	cout << "buf is: " << buf << endl;

	// Compare
	if (s1.compare(s2) == 0) {
		cout << "s1 is equal to s2" << endl;
	}

	// Check if empty
	string s7;
	if (s7.empty())
		cout << "s7 is empty" << endl;

	// Find last occurence of sub string
	cout << "Location of last \"And\" in s5 (" << s5 << ") is :: " << s5.rfind("And") << endl;

	// TODO Now try string.find(string s) and see the difference

	// Get the substring of a string
	// substr member function returns the substring of a string
	// string.substr(size_t pos = 0, size_t len = npos) const;
	// TODO: use substr to get the substring of s5 from the last "And" to the end of it and show it on console
}

void simpleFileWriteExample ( void ) {
	// Creates an instance of ofstream, and opens example.txt (in ASCII (Text) Mode)
	cout << "Opening ofstream_ascii_example.txt file for writing...\n";
	/* ofstream :: Output stream class to operate on files.
	 *             File streams are associated with files either on construction (as next line), 
	 *             or by calling member open (see the point we call open to append something at the end of the file).
	 *
	 * Some basic public members of ofstream
	 *
	 * operator <<	:: outputs formatted text
	 * put			:: puts a character
					-- put (char c)

	 * write		:: writes a block of data (binary format)
					-- write(const char* s, streamsize n)

	 * tellp		:: tells us where we are (which position)
					-- tellp()

     * seekp		:: set the position
					-- seekp (streampos pos);
					-- seekp (streamoff offset, ios_base::seekdir way)
						- way can be one of the following
						-	ios_base::beg	beginning of the stream
						-	ios_base::cur	current position in the stream
						-	ios_base::end	end of the stream

	 * flush		:: flushed data from output stream buffer to the file

	 * close		:: closes the file
	 */

	//
	ofstream oFile("ofstream_ascii_example.txt");
	if (!oFile.is_open()) {
		printf("could not open ofstream_ascii_example.txt");
		return;
	}
	else {
		string text("Output some text ffor ascii!!\n");
		oFile << text;
		cout << "Writing int values to file";
		for (int i = 0; i < 10; i++) {
			oFile << i*i << endl;
		}
		oFile << "\n";
		double PI = 3.14156536;
		for (int i = 0; i < 11; i++) {
			oFile << setprecision(11 - i) << PI << endl;
		}
		oFile << endl;
		
		oFile.seekp(0, ios_base::beg);
		oFile << "Surname Name";
		cout << "Dont forget to close file";
		oFile.close();
	}
	cout << "Writing to the file with append...";
	oFile.open("ofstream_ascii_example.txt", ios::app);
	oFile << endl << "Appended string" << endl;
	oFile.close();
}

void simpleReadExample ( const char *fName ) {
	/* ifstream :: Input stream class to read from files.
	 * 
	 * Some basic public members of ifstream
	 *
	 * operator >>	:: extracts formatted input
	 * get			:: get characters
				    -- get (char& c)
					-- get (char* s, streamsize n);		// default delim '\n'
				    -- get (char* s, streamsize n, char delim);

	 * getline		:: get a line
					-- get (char* s, streamsize n ); 	// default delim '\n'
					-- getline (char* s, streamsize n, char delim );

	 * read			:: the write equivalent
					-- read (char* s, streamsize n);

	 * tellg		:: as previously stated but not its tellg NOT tellp
					-- tellg()

     * seekg		:: as previously stated but not its seekg NOT seekp
					-- seekg (streampos pos);
					-- seekg (streamoff offset, ios_base::seekdir way)
						- way can be one of the following
						-	ios_base::beg	beginning of the stream
						-	ios_base::cur	current position in the stream
						-	ios_base::end	end of the stream

	 * clear			:: clears error bit properties (ex. when you reach end of file eof bit is raised)
					   in order to use seekp to go back to start you have to first use clear

     * close			:: closes the file
	 */


	// Example of reading data from ASCII file
	ifstream ifile(fName);
	string text;
	int x;
	float y;
	ifile >> text >> x >> y;
	cout << "Read from file: " << text << " " << x << " " << y;





}

void readWriteBinaryExample(void) {
	fstream file("binaryReadWriteExample.txt", ios::out | ios::in | ios::binary);
	if (!file.is_open()) {
		printf("Couldnot open file!\n");
		return;
	}
	else {
		string text = "CUSTOM HEADER FILE\n";
		file.write(text.c_str(),text.size());
		int numOfInts;
		cout << "Give an int\n";
		cin >> numOfInts;
		cout << "Computing and ";
		file.write((char *)&numOfInts, sizeof(int));
		for (int i = 0; i < numOfInts; i++) {
			cout << "Computing factorial of " << i << endl;
			int val = i*i;
			file.write((char*)&val, sizeof(int));
		}

		//Make sure everything passed to the file
		file.flush();

		//Now go back to the start of the file, 2 ways
		file.clear();
		file.seekg(0, ios::beg);

		const int intSize = 64;
		char buf[intSize];
		file.getline(buf, intSize);
		if (_stricmp(buf, "CUSTOM HEADER FILE")==0) {
			cout << "This is a fie of our own format!" << endl;
			//read how many values we except to read
			int numVals;
			file.read((char*)&numVals, sizeof(int));
			cout << "File contains " << numVals << " integers\n";

			//initialize an array of appropriate size
			int *valsArray = new int[numVals];
			//Read all values with one command
			file.read((char *)valsArray, sizeof(int)*numVals);
			//Print the values
			for (int i = 0; i < numOfInts; i++) {
				cout << "Value " << i << " is: " << valsArray[i]<<endl;
			}


			file.close();
		}
	}

}

int readInputFile (char *inputFile, vector<string> vContainer) {
	return 0;
}
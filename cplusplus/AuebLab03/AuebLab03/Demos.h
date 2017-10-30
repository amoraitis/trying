#ifndef DEMOS_H_
#define DEMOS_H_

#include <string>
#include <vector>
#include <set>

// Declarations
void string_examples();

void simpleFileWriteExample ( void );
void simpleReadExample ( const char *fName );

void readWriteBinaryExample ( void );

int readInputFile ( char *inputFile, std::vector<std::string> vContainer, std::set<std::string> sContainer );

#endif	// DEMOS_H_
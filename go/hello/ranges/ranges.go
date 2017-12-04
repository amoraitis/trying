package main

import "fmt"

func main() {
	nums := []int{2, 3, 4}
	sum := 0
	for _, num := range nums {
		sum += num
	}
	fmt.Println("sum:", sum)

	for i, num := range nums {
		if num == 3 {
			fmt.Println("index of 3:", i)
		}
	}

	kvs := map[string]string{"a": "apple", "b": "banana"}
	//iterate over maps like this
	for k, v := range kvs { // now v has the role of the matching object
		fmt.Printf("%s -> %s\n", k, v)
	}
	//or like this if we dont care for values
	for k := range kvs {
		fmt.Println("key:", k)
	}
	//k, for keys & v, for values
	for i, c := range "go" {
		fmt.Println(i, c)
	}

}

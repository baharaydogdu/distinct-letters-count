# DistinctLettersCount

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-program">About The Program</a>
      <ul>
		<li><a href="#purpose">Purpose</a></li>
		<li><a href="#constraints">Contraints</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>   
    <li><a href="#design-approach">Design Approach</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#sample-input-output">Sample Input Output</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About The Program

### Purpose

This program counts the minimum number of letters that must be deleted from to create a word in which no two letters occur the same number of times.

### Constraints

Program throws exceptions according to below constraints.

1. The minimum character number of the input line is 1.

2. The maximum character number of the input line is 300000.

3. The string value consists of only lowercase letters (a-z).

### Built With

This program is written in Java 8.

## Design Approach

The main function of this program works in 3 basic steps.

1) Get input from file

	* Get input data as a stream
	* Convert it to list of strings
	* Give the list to validation function

2) Validate the input

	* Take input list	
	* Check all contraints for each line
	* Give valid input list to process function

3) Process the input

	* Take valid input list
	* Create a frequency map and call algorithm for each element
	* Give output list


### Usage

This program can be run on the command line in order to solve this problem. The program takes one command line argument, which contains the path to a text file. This text file should contain several lines, each line describing one test case for the problem.

Usage Commands:

1) Clone the repository 

	```sh
	git clone git@github.com:baharaydogdu/distinct-letters-count.git
	```
	
	
2) Compile the main class
	
	```sh
	javac DistinctLettersCount.java
	```
	
3) Run program

	```sh
	java DistinctLettersCount ..\input.txt 
	```
	
	(Please do not forget to give file path as an argument to program)


## Sample Input Output

A sample input file looks like this:


Input:

```

aaaabbbb

ccaaffddecee

eeee

example

```





The sample output for the sample input file above should look like this:


Output:

```

1- 1

2- 6

3- 0

4- 4

```



## Contact


Movie Wall

Author: Richard Dzreke

Overview

Movie Wall is a Java program that allows users to explore movie and actor data from a CSV file. You can search for actors, view the movies they starred in, and the roles they played. If the exact actor name isn’t found, the program suggests the closest match.

Features

Loads movie and cast data from a CSV file.

Allows users to search for actors by name.

Displays all movies and roles for the selected actor.

Suggests the closest matching actor if the search fails.

Supports continuous searching until the user types EXIT.

Uses binary search and custom similarity scoring for efficient and accurate lookups.

Files

Movie_Wall.java – Main program handling data loading, searching, and user interaction.

CSVReader_Names and CSVReader methods – Parse actor names and movie data from CSV.

How to Run

Compile:

javac CS245_Project01/Movie_Wall.java


Run:

java CS245_Project01.Movie_Wall path/to/movies.csv


Then follow the prompts to search for actors. Type EXIT to quit.

Notes

Actor names are automatically capitalized for consistent matching.

The program handles large datasets efficiently using quicksort and binary search.

Similarity scoring helps suggest the most likely actor when an exact match isn’t found.

Challenges & Learnings

This project helped me practice:

Reading and parsing CSV files.

Implementing binary search and quicksort for data lookup.

Designing a user-friendly command-line interface.

Handling string similarity and edge cases in searches.

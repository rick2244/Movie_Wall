# Text Analyzer

Function: Analyzes any webpage (by URL) and provide a summary of the text

Key Features: 
- Accepting a URL to process the text
- Using a property file to indicate words to avoid processing (like articles or common
words)
- Output Top 10 most common words in the text
- Output the histogram of words - top 100 words and their count
- Output the shortest and the longest occurring words
- Executing the user commands provided as arguments and make the output
accordingly

Reading content from a URL:
- Achieved using the Java library jsoup
- Example run: java TextAnalyzer https://en.wikipedia.org/wiki/Pet_door
- The java.util.Properties class is used to read the property file
(textanalyzer.properties) and subsequently to get the values stored in the property file. The property file
will have some information on words to avoid when processing the text.
      Example of property file:
        #words to avoid processing
        avoid=a,the,i,so
- If a property file does not exist, default values will be used or execution of the parsing will be abandoned

Processing the contents:
- Once the content is retrieved from the URL and after separating the text, the text contents
are processed and kept in a doubly chained LinkedList, while avoiding words that populate the property file.
- An ArrayList is used to keep track of the avoid words

User Commands:
- After processing words inputs are taken from the user to give relevent information on the data
      Top10 Longest Shortest Summary
      Will produce an output of
      Top 10 words: Word1: count | Word2: count | Word3: count ....
      Longest: Word1: count
      Shortest: Word1: count
      Summary: Total Words: count | Total Letters: count
- Command taxonomy:
      Top10 - top 10 occurring words
      Longest - the longest word by character count (print the first word in case of ties)
      Shortest - the shortest word by character count (print the first word in case of ties)
      Summary - Prints total words, total letters
  

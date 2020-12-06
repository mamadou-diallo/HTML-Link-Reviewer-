## HTML-Link-Reviewer

This is a command line tool that filters through a document and looks for HTML links and validates them.
There are three methods to use this tool.


### Method 1 

You may compile this tool using the code found in this repo. 
Using Intellij is recommended. You must make sure to have JSOUP and JSON in your jar libraries. 
Once compiled 

Make sure to have your arguments set in your IDE. 



### Method 2 
Download [HTML-Link-Retriever-](https://search.maven.org/artifact/com.github.mamadou-diallo/HTML-Link-Reviewer-/1.0.0/jar)

Once downloaded, you may run the tool using the following command :  "java -jar /path/to/HTML-Link-Retriever-.jar 'file here' 'insert arguments here'"

### Method 3
You may also use the HTML-Link-Retriever-.exe file and run it in the command line.
You can use the tool as following : /path/to/HTML-Link-Retriever-.exe 'file here' 'insert arguments here'

### Argument options
Results should come back color coded to match validity of an HTML link. 
Using this tool with no file name will prompt user to use the tool with a file name. 

Use --v or --version to show the name and version of the tool.

The following arguments can be used to generate specific results : 
--all ( Default command that returns both good and bad results)
--good ( Only returns good results)
--bad ( Only returns bad results)'

Exit Codes : 
0 - Everything was ran successfully
1 - There was an Unknown Error Code retrieved
2 - A connection could not be established



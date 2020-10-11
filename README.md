# HTML-Link-Reviewer

This is a command line tool that filters through a document and looks for HTML links and validates them.
Before trying to use this compile this tool, one must have jsoup installed on their system. 
To begin using this tool, simply input the name of the file that you're looking to open ( Must be in the same directory ).
Results should come back color coded to match validity of an HTML link. 
Using this tool with no file name will prompt user to use the tool with a file name. 
Use --v or --version to show the name and version of the tool.
The following arguments can be used to generate specific results : 
--all ( Default command that returns both good and bad results)
--good ( Only returns good results)
--bad ( Only returns bad results)
Exit Codes : 
0 - Everything was ran succesfully
1 - There was an Unknown Error Code retrieved
2 - A connection could not be established
A JFlex+Cup implementation for the Tiny language.

To build the parser, type "make" in the current directory, which will 
generate parser.java and Lexer.java 

  To test source code like "1.cm", type 

    "java -classpath /usr/share/java/cup.jar:. Main 1.cm" 

and the syntax tree will be displayed on the screen.

  To rebuild the parser, type "make clean" and type "make" again.



The test programs included are:

1.cm
	No errors

2.cm
	Lexical Errors

3.cm
	Syntax Errors

4.cm
	Both Lexican and Syntax Errors

5.cm
	Anything goes	
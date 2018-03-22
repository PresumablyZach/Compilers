Created By: Adam Bollinger and Zach Gyorffy

To build the compiler, type "make" in the current directory, which will
generate files such as parser.java and Lexer.java

To test source code like "1.cm", type

    "java -classpath /usr/share/java/cup.jar:. CM 1.cm"

    To test the symbol table of source code like "1.cm", type

     "java -classpath /usr/share/java/cup.jar:. CM -a testPrograms/1.cm"

and the syntax tree will be displayed on the screen.

To rebuild the parser, type "make clean" and type "make" again.



To build the code, we used the sample code Prof. Song provided as a starting point and as a reference.



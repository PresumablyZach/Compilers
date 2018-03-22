/*
  Created by: Adam Bollinger and Zach Gyorffy
  File Name: CM.java
  To Build: 
  After the scanner, cMinus.flex, and the parser, cMinus.cup, have been created.
    javac CM.java
  
  To Run: 
    java -classpath /usr/share/java/cup.jar:. CM gcd.cm

  where gcd.cm is an test input file for the cm language.
*/

import java.io.*;
import java.util.*;
   
class CM {
    /* Valid argument strings */
    static private List<String> validArgs = Arrays.asList("-a", "-s");
    
    /* Prints the proper usage of the program for use in error handling */
    static private void printUsage() {
        System.out.println("Proper Usage:");
        System.out.println("    java CM [-a] [-s] [file_name.cm]");
        System.out.println("        -a: print abstract syntax tree");
        System.out.println("        -s: print symbol table");
    }
    
    static public void main(String argv[]) {
        String filename;
        List<String> userArgs = new ArrayList<String>();
        
        /* Validate Arguments */
        for (int i = 0; i < argv.length - 1; i++) {
            if (!validArgs.contains(argv[i])) {
                System.out.println(argv[i] + " is not a valid argument");
                printUsage();
                return;
            }
            if (argv[i].equals("-a"))
                userArgs.add("-a");
            if (argv[i].equals("-s"))
                userArgs.add("-s");
        }
        
        /* Initialize Filename */
        filename = argv[argv.length-1];

        /* Start the parser */
        try {
            parser p = new parser(filename, userArgs, new Lexer(new FileReader(filename)));
            Object result = p.parse().value;
        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }
    }
}

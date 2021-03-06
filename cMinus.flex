/*
  Created By: Adam Bollinger and Zach Gyorffy
  File Name: cMinus.flex
  To Build: jflex cMinus.flex

  and then after the parser is created
    javac Lexer.java
*/
   
/* --------------------------Usercode Section------------------------ */
   
import java_cup.runtime.*;
      
%%
   
/* -----------------Options and Declarations Section----------------- */
   
/* 
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java. 
*/
%class Lexer

%eofval{
  return null;
%eofval};

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column
    
/* 
   Will switch to a CUP compatibility mode to interface with a CUP
   generated parser.
*/
%cup
   
/*
  Declarations
   
  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.  
*/
%{   
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
   

/*
  Macro Declarations
  
  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.  
*/
   
/* A line terminator is a \r (carriage return), \n (line feed), or
   \r\n. */
LineTerminator = \r|\n|\r\n
   
/* White space is a line terminator, space, tab, or form feed. */
WhiteSpace     = {LineTerminator} | [ \t\f]
   
/* A literal integer is is a number beginning with a number between
   one and nine followed by zero or more numbers between zero and nine
   or just a zero.  */
digit = [0-9]
number = {digit}+
   
/* A identifier integer is a word beginning a letter between A and
   Z, a and z, or an underscore followed by zero or more letters
   between A and Z, a and z, zero and nine, or an underscore. */
letter = [a-zA-Z]
identifier = {letter}+

%state COMMENT

%%
/* ------------------------Lexical Rules Section---------------------- */
   
/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */

<YYINITIAL>"else"             { return symbol(sym.ELSE); }
<YYINITIAL>"if"               { return symbol(sym.IF); }
<YYINITIAL>"int"              { return symbol(sym.INT); }
<YYINITIAL>"return"           { return symbol(sym.RETURN); }
<YYINITIAL>"void"             { return symbol(sym.VOID); }
<YYINITIAL>"while"            { return symbol(sym.WHILE); }
/* ------------------------------------------------------------------- */
<YYINITIAL>"+"                { return symbol(sym.PLUS); }
<YYINITIAL>"-"                { return symbol(sym.MINUS); }
<YYINITIAL>"*"                { return symbol(sym.MUL); }
<YYINITIAL>"/"                { return symbol(sym.DIV); }
<YYINITIAL>"<"                { return symbol(sym.LT); }
<YYINITIAL>">"                { return symbol(sym.GT); }
<YYINITIAL>"<="               { return symbol(sym.LTE); }
<YYINITIAL>">="               { return symbol(sym.GTE); }
<YYINITIAL>"=="               { return symbol(sym.EQUAL); }
<YYINITIAL>"!="               { return symbol(sym.NOT); }
<YYINITIAL>"="                { return symbol(sym.ASSIGN); }
/* ------------------------------------------------------------------- */
<YYINITIAL>";"                { return symbol(sym.SEMI); }
<YYINITIAL>","                { return symbol(sym.COMMA); }
<YYINITIAL>"("                { return symbol(sym.LPAREN); }
<YYINITIAL>")"                { return symbol(sym.RPAREN); }
<YYINITIAL>"["                { return symbol(sym.LBRACK); }
<YYINITIAL>"]"                { return symbol(sym.RBRACK); }
<YYINITIAL>"{"                { return symbol(sym.LBRACE); }
<YYINITIAL>"}"                { return symbol(sym.RBRACE); }
/* ------------------------------------------------------------------- */
<YYINITIAL>{identifier}       { return symbol(sym.ID, yytext()); }
<YYINITIAL>{number}           { return symbol(sym.NUM, yytext()); }
<YYINITIAL>{WhiteSpace}*      { /* skip whitespace */ }   
<YYINITIAL>"/*"               { yybegin(COMMENT); }
<COMMENT>"*/"                 { yybegin(YYINITIAL); }
<COMMENT>[^]|\n               { /* Skip Comments */ }
<YYINITIAL>.                  { return symbol(sym.ERROR); }

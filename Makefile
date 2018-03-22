JAVA=java
JAVAC=javac
JFLEX=jflex
CLASSPATH=-classpath /usr/share/java/cup.jar:.
#CUP=$(JAVA) $(CLASSPATH) java_cup.Main <
CUP=cup

all: CM.class

CM.class: absyn/*.java parser.java sym.java Lexer.java Scanner.java CM.java

%.class: %.java
	$(JAVAC) $(CLASSPATH)  $^

Lexer.java: cMinus.flex
	$(JFLEX) cMinus.flex

parser.java: cMinus.cup
	$(CUP) -dump -expect 3 cMinus.cup

clean:
	rm -f parser.java Lexer.java sym.java *.class absyn/*.class *~

test:
	java $(CLASSPATH) CM testPrograms/gcd.cm

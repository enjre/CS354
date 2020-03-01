 
import java.util.*;
/**
 * 
 * Class represents the scanner for the program 
 * and programming language being interpreted
 * @author andremaldonado
 *
 */
public class Scanner {

    private String program;	// source program being interpreted
    private int pos;		// index of next char in program
    private Token token;	// last/current scanned token

    // sets of various characters and lexemes
    private Set<String> whitespace=new HashSet<String>();
    private Set<String> digits=new HashSet<String>();
    private Set<String> letters=new HashSet<String>();
    private Set<String> legits=new HashSet<String>();
    private Set<String> keywords=new HashSet<String>();
    private Set<String> operators=new HashSet<String>();
    private Set<String> comments= new HashSet<String>();

    // initializers for previous sets
/**
 * fills a set
 * @param s
 * @param lo
 * @param hi
 */
    private void fill(Set<String> s, char lo, char hi) {
	for (char c=lo; c<=hi; c++)
	    s.add(c+"");
    }    

    /**
     * initialize whitespace
     * @param s
     */
    private void initWhitespace(Set<String> s) {
	s.add(" ");
	s.add("\n");
	s.add("\t");
    }

    /**
     * initialize my unique comment syntax
     * @param s
     */
    private void initComment(Set<String> s) {
    	s.add("_");
    }
    
    /**
     * initialize digits
     * @param s
     */
    private void initDigits(Set<String> s) {
	fill(s,'0','9');
    }

    /**
     * initialize letters uppercase and lowercase
     * @param s
     */
    private void initLetters(Set<String> s) {
	fill(s,'A','Z');
	fill(s,'a','z');
    }

    /**
     * initialize letters and digits
     * @param s
     */
    private void initLegits(Set<String> s) {
	s.addAll(letters);
	s.addAll(digits);
    }
	/**
	 * initialize the defined operators
	 * @param s
	 */
    private void initOperators(Set<String> s) {
	s.add("=");
	s.add("+");
	s.add("-");
	s.add("*");
	s.add("/");
	s.add("(");
	s.add(")");
	s.add(";");
    }

    /**
     * empty for now
     * initialize the language keywords
     * @param s
     */
    private void initKeywords(Set<String> s) {
    }

    // constructor:
    //   - squirrel-away source program
    //   - initialize sets
    public Scanner(String program) {
	this.program=program;
	pos=0;
	token=null;
	initWhitespace(whitespace);
	initDigits(digits);
	initLetters(letters);
	initLegits(legits);
	initKeywords(keywords);
	initOperators(operators);
	initComment(comments);
    }

	    // handy string-processing methods
	/**
	 * tells when we are done scanning
	 * @return
	 */
    public boolean done() {
	return pos>=program.length();
    }

    /**
     * keeps scanning
     * @param s
     */
    private void many(Set<String> s) {
	while (!done() && s.contains(program.charAt(pos)+""))
	    pos++;
    }
    
    /**
     * identifies a char as past
     * @param c
     */
    private void past(char c) {
	while (!done() && c!=program.charAt(pos))
	    pos++;
	if (!done() && c==program.charAt(pos))
	    pos++;
    }

    // scan various kinds of lexeme

    /**
     * moves to the next number
     */
    private void nextNumber() {
	int old=pos;
	many(digits);
	
	String dot = ".";
	
	if(dot.equals(program.substring(pos, pos+1))) {
		
		pos++;
		many(digits);
	}
	
	
	
	token=new Token("num",program.substring(old,pos));
    }
    /**
     * moves to the next comment with my syntax
     */
    private void nextComment() {
    		pos++;
    		past('_');
    }
    
    /**
     * identifies next keyword
     */
    private void nextKwId() {
	int old=pos;
	many(letters);
	many(legits);
	String lexeme=program.substring(old,pos);
	token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
    }

    /**
     * identifies next operator
     */
    private void nextOp() {
	int old=pos;
	pos=old+2;
	if (!done()) {
	    String lexeme=program.substring(old,pos);
	    if (operators.contains(lexeme)) {
		token=new Token(lexeme); // two-char operator
		return;
	    }
	}
	pos=old+1;
	String lexeme=program.substring(old,pos);
	token=new Token(lexeme); // one-char operator
    }

    // This method determines the kind of the next token (e.g., "id"),
    // and calls a method to scan that token's lexeme (e.g., "foo").
    public boolean next() {
	if (done()) {
	    token=new Token("EOF");
	    return false;
	}
	many(whitespace);
	String c=program.charAt(pos)+"";
	
	if(comments.contains(c)) {
		nextComment();
		return next();
	}
	
	else if (digits.contains(c))
	    nextNumber();
	else if (letters.contains(c))
	    nextKwId();
	else if (operators.contains(c))
	    nextOp();
	else {
	    System.err.println("illegal character at position "+pos);
	    pos++;
	    return next();
	}
	return true;
    }

    // This method scans the next lexeme,
    // if the current token is the expected token.
    public void match(Token t) throws SyntaxException {
	if (!t.equals(curr()))
	    throw new SyntaxException(pos,t,curr());
	next();
    }

    /**
     * defines the current token
     * @return
     * @throws SyntaxException
     */
    public Token curr() throws SyntaxException {
	if (token==null)
	    throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
	return token;
    }

    /**
     * gets the position 
     * @return pos
     */
    public int pos() { return pos; }

    // for unit testing
    public static void main(String[] args) {
	try {
	    Scanner scanner=new Scanner(args[0]);
	    while (scanner.next())
		System.out.println(scanner.curr());
	} catch (SyntaxException e) {
	    System.err.println(e);
	}
    }

}

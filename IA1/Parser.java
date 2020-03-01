// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;
/**
 * Parses the arguments
 * @author andremaldonado
 *
 */
public class Parser {

    private Scanner scanner;

    /**
     * matches the tokens in the string s
     * @param s
     * @throws SyntaxException
     */
    private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }

    /**
     * current token
     * @return
     * @throws SyntaxException
     */
    private Token curr() throws SyntaxException {
	return scanner.curr();
    }

    /**
     * current position
     * @return
     */
    private int pos() {
	return scanner.pos();
    }

    /**
     * defines the multiplication and division operations
     * @return
     * @throws SyntaxException
     */
    private NodeMulop parseMulop() throws SyntaxException {
	if (curr().equals(new Token("*"))) {
	    match("*");
	    return new NodeMulop(pos(),"*");
	}
	if (curr().equals(new Token("/"))) {
	    match("/");
	    return new NodeMulop(pos(),"/");
	}
	return null;
    }

    /**
     * defines the add and subtract operations
     * @return
     * @throws SyntaxException
     */
    private NodeAddop parseAddop() throws SyntaxException {
	if (curr().equals(new Token("+"))) {
	    match("+");
	    return new NodeAddop(pos(),"+");
	}
	if (curr().equals(new Token("-"))) {
	    match("-");
	    return new NodeAddop(pos(),"-");
	}
	return null;
    }

    /**
     * parses the nodefact and checks if it matches
     * @return
     * @throws SyntaxException
     */
    private NodeFact parseFact() throws SyntaxException {
	
    if(curr().equals(new Token("-"))) {
    	match("-");
    	NodeFact nf = parseFact();
    	return new NodeFactUn(nf);
    }
    	
    	if (curr().equals(new Token("("))) {
	    match("(");
	    NodeExpr expr=parseExpr();
	    match(")");
	    return new NodeFactExpr(expr);
	}
	if (curr().equals(new Token("id"))) {
	    Token id=curr();
	    match("id");
	    return new NodeFactId(pos(),id.lex());
	}
	Token num=curr();
	match("num");
	return new NodeFactNum(num.lex());
    }

    /**
     * Parses the node term
     * @return
     * @throws SyntaxException
     */
    private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact=parseFact();
	NodeMulop mulop=parseMulop();
	if (mulop==null)
	    return new NodeTerm(fact,null,null);
	NodeTerm term=parseTerm();
	term.append(new NodeTerm(fact,mulop,null));
	return term;
    }

    /**
     * parses the node expression
     * @return
     * @throws SyntaxException
     */
    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm();
	NodeAddop addop=parseAddop();
	if (addop==null)
	    return new NodeExpr(term,null,null);
	NodeExpr expr=parseExpr();
	expr.append(new NodeExpr(term,addop,null));
	return expr;
    }

    /**
     * parses the node assignment
     * @return
     * @throws SyntaxException
     */
    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id");
	match("=");
	NodeExpr expr=parseExpr();
	NodeAssn assn=new NodeAssn(id.lex(),expr);
	return assn;
    }

    /**
     * parses the node statement
     * @return
     * @throws SyntaxException
     */
    private NodeStmt parseStmt() throws SyntaxException {
	NodeAssn assn=parseAssn();
	match(";");
	NodeStmt stmt=new NodeStmt(assn);
	return stmt;
    }

    /**
     * parses the node
     * @param program
     * @return
     * @throws SyntaxException
     */
    public Node parse(String program) throws SyntaxException {
	scanner=new Scanner(program);
	scanner.next();
	NodeStmt stmt=parseStmt();
	match("EOF");
	return stmt;
    }

}

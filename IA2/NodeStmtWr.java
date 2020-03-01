/**
 * writing statements
 * @author andremaldonado
 *
 */
public class NodeStmtWr extends NodeStmt{

	private NodeExpr expr;
	/**
	 * constructor
	 * @param expr
	 */
	public NodeStmtWr(NodeExpr expr) {
		this.expr= expr;
	}
	
	/**
	 * eval 
	 * @param env
	 * @return
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException{
		double o = expr.eval(env);
		System.out.println(o);
		return o;
	}
}

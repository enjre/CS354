/**
 * node for statement assignment
 * @author andremaldonado
 *
 */
public class NodeStmtAssn extends NodeStmt {

	private String id;
	private NodeExpr expr;
	
	/**
	 * constructor
	 * @param id
	 * @param expr
	 */
	public NodeStmtAssn(String id, NodeExpr expr) {
		this.id= id;
		this.expr= expr;
	}
	
	
	public double eval(Environment env) throws EvalException{
		return env.put(id,  expr.eval(env));
	}

}

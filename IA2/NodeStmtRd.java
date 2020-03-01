import java.util.Scanner;
/**
 * reading statements
 * @author andremaldonado
 *
 */
public class NodeStmtRd extends NodeStmt{
	
	private String id;
	static Scanner sc;
	
	/**
	 * constructor
	 * @param id
	 */
	public NodeStmtRd(String id) {
		this.id= id;
	}
	
	public double eval(Environment env) throws EvalException{
		sc = new Scanner(System.in);
		double val = sc.nextDouble();
		return env.put(id, val);
	}
	
}

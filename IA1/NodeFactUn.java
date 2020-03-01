/**
 * Unary Node for the minus sign meaning a negative number
 * @author andremaldonado
 *
 */
public class NodeFactUn extends NodeFact{
	
	private NodeFact nf;
	
	/**
	 * 
	 * @param nf
	 */
	public NodeFactUn(NodeFact nf) {
		 this.nf = nf;
	}
	
	/**
	 * @return number
	 */
	public double eval(Environment e) throws EvalException{
		return (nf.eval(e) * -1);
	}

}

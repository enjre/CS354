// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.
/**
 * represents the base level node extended by other classes
 * @author andremaldonado
 *
 */
public abstract class Node {

    protected int pos= 0;

    public double eval(Environment env) throws EvalException {
	throw new EvalException(pos,"cannot eval() node!");
    }

}

public class TaillesNonConcordantesException extends Exception{
	public TaillesNonConcordantesException(String message) {
		super("Problem de tailles non concordantes entre les deux matrices"+message);
	}

}
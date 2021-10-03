package forum;
public class TestSimpleChatter {
	public static void main(String[] args) {
		SimpleChatter a = new SimpleChatter("alice");
		a.messageRecu("forum", "bob", "message");
		// affiche sur une ligne :
		//(chez alice) bob@forum> message
	}
}

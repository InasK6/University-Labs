package forum;

public class SimpleChatter implements IChatter {
	private String name;
	
	public SimpleChatter(String n) {
		name=n;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void messageRecu(String forum, String auteur, String message) {
		// TODO Auto-generated method stub
		System.out.println("(chez "+name+") "+ auteur+"@"+forum+"> "+message);
	}

}

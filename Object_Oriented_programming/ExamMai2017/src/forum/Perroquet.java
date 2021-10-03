package forum;

public class Perroquet implements IChatter {

	private String name;
	private IForum cible;
	public Perroquet(String n, IForum f) {
		name=n;
		cible=f;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void messageRecu(String forum, String auteur, String message) {
		// TODO Auto-generated method stub
		if(!forum.equals(cible.getName())) {
			cible.posterMessage(name, message);
		}
	}

}

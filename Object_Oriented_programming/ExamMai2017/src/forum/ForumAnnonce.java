package forum;

public class ForumAnnonce extends ForumDecorator {

	public ForumAnnonce(IForum f) {
		super(f);
		// TODO Auto-generated constructor stub
	}
	
	// surcharge
	@Override
	public boolean rejoindre(IChatter chatter) {
		boolean b=super.rejoindre(chatter);
		if(b==true)
			super.posterMessage(getName(), chatter.getName()+" rejoint la conversation");
		return b;
	}

	@Override
	public boolean quitter(IChatter chatter) {
		boolean b=super.quitter(chatter);
		if (b)
			super.posterMessage(getName(), chatter.getName()+" quitte la conversation");
		return b;
	}
}

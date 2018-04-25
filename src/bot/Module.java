package bot;
import sx.blah.discord.api.*;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.modules.IModule;

public class Module implements IModule
{
	public static IDiscordClient client;
    
    public boolean enable(IDiscordClient dclient) {
		client = dclient;
		EventDispatcher dispatcher = client.getDispatcher();
		dispatcher.registerListener(new AnnotationListener());
		return true;
	}
	public void disable() {
		
	}
	public String getAuthor() {
		return "INotcent";
	}
	public String getMinimumDiscord4JVersion() {
		return null;
	}
	public String getName() {
		return "LOLBOT";
	}
	public String getVersion() {
		return "1.0";
	}
	
}

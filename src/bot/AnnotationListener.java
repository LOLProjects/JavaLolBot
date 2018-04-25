package bot;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;

public class AnnotationListener 
{
	static final String folder = "words\\";
	static Random rnd = new Random();
	static final int PatternCount = Pattern.values().length;
	
	@EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        System.out.println("Ready");
        System.out.println(folder);
    }
	
	@EventSubscriber
	public void OnMesageEvent(MessageReceivedEvent event) 
    {
		IMessage message = event.getMessage();
		if (message.getContent().startsWith("-LOL"))
		{
			String newName = GetLolName(Pattern.values()[rnd.nextInt(Pattern.values().length)]);
			SendNewNameMessage(message.getChannel(), newName);
			event.getGuild().changeName(newName);
		}
    }
	
	public void SendNewNameMessage(IChannel channel, String newName)
    {
		new MessageBuilder(Module.client).withEmbed(new EmbedBuilder().withDesc("Name has been changed to \"" + newName + "\" !").withColor(new Color(100, 200, 100)).build()).withChannel(channel).build();
	}
	
	public String GetLolName(Pattern p)
	{
		switch (p)
		{
		case NounOnNoun:
			return GetLNoun() + " On " + GetLNoun();
		case AdjAdjNoun:
			return GetLAdj() + ' ' + GetOAdj() + ' ' + GetLNoun();
		}
		return "Error";
	}
	
	public String GetLNoun()
	{
		return GetRandomWord(GetWordsInFile(folder + "Lnouns.txt"));		
	}
	
	public String GetONoun()
	{
		return GetRandomWord(GetWordsInFile(folder + "Onouns.txt"));		
	}
	
	public String GetLAdj()
	{
		return GetRandomWord(GetWordsInFile(folder + "Ladj.txt"));		
	}
	
	public String GetOAdj()
	{
		return GetRandomWord(GetWordsInFile(folder + "Oadj.txt"));		
	}
	
	public ArrayList<String> GetWordsInFile(String path)
	{
		Scanner s;
		ArrayList<String> list = new ArrayList<String>();
		File file = new File(path);
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(path);
			return list;
		}
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		return list;
	}
	
	public String GetRandomWord(ArrayList<String> words)
	{
		if (words.size() == 0)
			return "Error";
		String r = words.get(rnd.nextInt(words.size()));
		r = r.substring(0,  1).toUpperCase() + r.substring(1);
		return r;
	}
	
	public enum Pattern
	{
		NounOnNoun,
		AdjAdjNoun;
	}
}

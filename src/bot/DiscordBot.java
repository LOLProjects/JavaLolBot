package bot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import sx.blah.discord.api.*;
import sx.blah.discord.util.DiscordException;

public class DiscordBot 
{
	public static void main(String[] args) {
		String token;
		try {
			token = Files.readAllLines(Paths.get("token.txt")).get(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Token file could not be read.");
			System.out.println("You must create a file \"token.txt\" with your discord token in it.");
			System.out.println("Exiting");
			return;
		}
		IDiscordClient dc = createClient(token);
		Module module = new Module();
		module.enable(dc);
	}

	public static IDiscordClient createClient(String token) 
	{ 
        ClientBuilder clientBuilder = new ClientBuilder(); 
        clientBuilder.withToken(token); 
        try 
        {
        	return clientBuilder.login(); // Creates the client instance and logs the client in
        } 
        catch (DiscordException e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
}

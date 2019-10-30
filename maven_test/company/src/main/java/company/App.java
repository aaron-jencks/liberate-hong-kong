package company;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Terminal terminal = TerminalBuilder.terminal();

        System.out.println("The size of the terminal is " + terminal.getWidth() + "x" + terminal.getHeight());

        System.out.println( "Hello World!" );
    }
}

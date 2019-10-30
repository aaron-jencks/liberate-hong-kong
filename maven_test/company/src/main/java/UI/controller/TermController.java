package UI.controller;

import java.util.ArrayDeque;
import java.io.IOException;

// import org.jline.terminal.Terminal;
// import org.jline.terminal.TerminalBuilder;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import UI.controller.ITermController;
import UI.IMenu;
import UI.IMenuItem;

public class TermController implements ITermController {
    private int term_width;
    private int term_height;
    private Screen term = null;
    private IMenu current = null;
    private ArrayDeque<IMenu> active_windows = new ArrayDeque<>();

    public TermController() throws IOException
    { 
        term = new TerminalScreen(new DefaultTerminalFactory().createTerminal());
        term.startScreen();
        TerminalSize size = term.getTerminalSize();
        term_width = size.getColumns();
        term_height = size.getRows();
    }

    @Override
    public void set_main_window(IMenu window)
    {
        term.clear();
        active_windows.push(window);
        window.display();
        current = window;
    }

    @Override
    public IMenuItem interact()
    {
        if(current != null)
            return current.prompt();
        return null;
    }

    @Override
    public void close_window()
    {
        term.clear();
        active_windows.pop();

        current = null;

        if(!active_windows.isEmpty())
        {
            current = active_windows.getFirst();
            current.display();
        }
    }

    @Override
    public int get_window_count() { return active_windows.size(); }

    @Override
    public int get_term_width() { return term_width; }

    @Override
    public int get_term_height() { return term_height; }
}

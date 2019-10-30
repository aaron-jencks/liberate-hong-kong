package UI.controller;

import java.util.ArrayDeque;
import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import UI.controller.ITermController;
import UI.IMenu;
import UI.IMenuItem;
import UI.UIUtil;

public class TermController implements ITermController {
    private int term_width;
    private int term_height;
    private IMenu current = null;
    private ArrayDeque<IMenu> active_windows = new ArrayDeque<>();

    public TermController() throws IOException
    {
        Terminal t = TerminalBuilder.terminal();
        term_width = t.getWidth();
        term_height = t.getHeight();
    }

    @Override
    public void set_main_window(IMenu window)
    {
        UIUtil.clrscr();
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
        UIUtil.clrscr();
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

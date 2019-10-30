package company.Entity.Controller;

import java.util.ArrayDeque;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import company.Entity.Interface.ITermController;
import UI.IMenu;
import UI.IMenuItem;
import UI.UIUtil;

public class TermController implements ITermController {
    private int term_width;
    private int term_height;
    private IMenu current = null;
    private ArrayDeque<IMenu> active_windows = new ArrayDeque<>();

    public TermController()
    {
        Terminal t = TerminalBuilder.terminal();
        term_width = t.getWidth();
        term_height = t.getHeight();
    }

    /**
     * Creates a new main window on the terminal, pushing this window onto the stack of already open windows.
     * @param window The window to open.
     */
    @Override
    public void set_main_window(IMenu window)
    {
        UIUtil.clrscr();
        active_windows.push(window);
        window.display();
        current = window;
    }

    /**
     * Interacts with the current window, prompting the user for an input
     * @return Returns the IMenu item selected by the user
     */
    @Override
    public IMenuItem interact()
    {
        if(current != null)
            return current.prompt();
        return null;
    }

    /**
     * Closes the current window and opens any underlying window, if available.
     */
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

    /**
     * Returns the number of active windows at the given time.
     */
    public int get_window_count() { return active_windows.size(); }
}

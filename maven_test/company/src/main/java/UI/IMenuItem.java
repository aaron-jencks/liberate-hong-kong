package UI;

/**
 * Represents an item on a menu
 */
public interface IMenuItem {

    /**
     * Determines if the current menu item is available for display
     * @return Returns true if the menu item can be displayed, false otherwise.
     */
    public boolean is_available();

    /**
     * Activates whatever action is associated with this item.
     */
    public void Activate();
}
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
     * @return Returns a menu that can be posted to display the information represented by this
     * item, or null if it doesn't have an associated menu.
     */
    public AMenu activate();
}
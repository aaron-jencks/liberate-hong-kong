package UI;

import java.util.function.Predicate;

/**
 * Represents a predicate that can be used to validate menu prompts
 */
public class MenuItemValidator implements Predicate<Integer> {
    /**
     * Represents the Length of the list of items in the menu
     */
    private int len;

    /**
     * Creates a new Menu Validator with the upper bound set to {@code len} 
     */
    public MenuItemValidator(int len)
    {
        this.len = len;
    }

    @Override
    public boolean test(Integer v)
    {
        return v > 0 && v <= len;
    }
}
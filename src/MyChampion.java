import java.util.List;
import java.util.ArrayList;

/**
 * Your champion. Change the class name, the name field, and make it yours!
 */
public class MyChampion extends Champion {
    public static final String NAME = "My Champion";

    public MyChampion() {
        // Create a champion with an attack of 5, a defense of 5, and max health of 106 omg budget roland lmao
        super("Patron Librarian: Roland", 5, 5, 106);
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>(super.getActions());
        // actions.add(new Durandal()); // Replace with your custom actions
        // actions.add(new Mook());
        // actions.add(new Ragna());
        // actions.add(new Zelkova());
        // actions.add(new Allas());
        // actions.add(new Wheels());
        return actions;
    }
}
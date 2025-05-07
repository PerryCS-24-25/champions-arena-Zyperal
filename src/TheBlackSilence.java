import java.util.ArrayList;
import java.util.List;
/**
 * Your champion. Change the class name, the name field, and make it yours!
 */
public class TheBlackSilence extends Champion {
    public static final String NAME = "TheBlackSilence";
    public static int moveCounter = 0;
    public TheBlackSilence() {
        // Create a champion with an attack of 5, a defense of 5, and max health of 106 omg budget roland lmao
        super("Patron Librarian: Roland", 5, 5, 106);
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>(super.getActions());
        actions.add(new Durandal()); // Replace with your custom actions
        actions.add(new Mook());
        actions.add(new Ragna());
        actions.add(new Zelkova());
        actions.add(new Allas());
        actions.add(new Wheels());
        actions.add(new CrystalAtelier());
        actions.add(new AtelierLogic());
        
        return actions;
    }

class Durandal extends Action {
    public Durandal() {
        super("Durandal");
    }

    /**
     * Executes the Durandal action.
     * This action deals 5-9 damage twice to the opponent + 1 permanant dmg
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (9 - 5 + 1)) + 5;
        final int dmgInst2 = (int) (Math.random() * (9 - 5 + 1)) + 5;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;
        context.wielder.getLoadout().addTemporaryModifier(new FreeDmg());
    }
}

/**
 * A temporary modifier that adds +1 damage permanantly (kinda).
 */
class FreeDmg extends TemporaryModifier {
    public FreeDmg() {
        super("Free Dmg", "Adds +1 damage Permanantly.", 15);
    }

    @Override
    public int modifyAttack(int baseDamage, BattleContext context) {
        return baseDamage + 1;
    }
}


class Mook extends Action {
    public Mook() {
        super("Mook");
    }

    /**
     * Executes the Durandal action.
     * This action deals 8-15 damage +3 dmg to the opponent
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (15 - 8 + 1)) + 8;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+3, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );

        moveCounter = moveCounter + 1;

    }
}

class Ragna extends Action {
    public Ragna() {
        super("Ragna");
    }

    /**
     * Executes the Ragna action.
     * This action deals 3-7 dmg thrice to the opponent + enemy bleed for 2 turns
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (7 - 3 + 1)) + 3;
        final int dmgInst2 = (int) (Math.random() * (7 - 3 + 1)) + 3;
        final int dmgInst3 = (int) (Math.random() * (7 - 3 + 1)) + 3;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2+dmgInst3, context);

        context.enemy.getLoadout().addTemporaryModifier(new BleedEffect());

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes & Punches for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;
        context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());

    }
}

class BleedEffect extends TemporaryModifier {
    public BleedEffect() {
        super("Bleed", "Deals 2 dmg for 2 turns", 2);
    }
    @Override
    public  void onTurnEnd(BattleContext context) {
        final int  bldDmg = 2;
        context.wielder.rawDamage(bldDmg, context);

        context.getLog().addEntry(
            context.wielder, null, getName(), context.wielder.getName() + "Takes " + bldDmg + " Bleed Damage.", context.round, BattleLog.EntryType.STATUS);
    }
}

class Zelkova extends Action {
    public Zelkova() {
        super("Zelkova");
    }

    /**
     * Executes the Zelkova action.
     * This action deals 4-8 dmg & 3-8 dmg to the opponent
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (8 - 4 + 1)) + 4;
        final int dmgInst2 = (int) (Math.random() * (8 - 3 + 1)) + 3;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes & Smashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );

        moveCounter = moveCounter + 1;

    }
}

class Allas extends Action {
    public Allas() {
        super("Allas");
    }

    /**
     * Executes the Allas action.
     * This action deals 5-9 dmg & 5-8 dmg to the opponent + -2 dmg to the enemy
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (9 - 5 + 1)) + 5;
        final int dmgInst2 = (int) (Math.random() * (8 - 5 + 1)) + 5;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Pierces for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );

        moveCounter = moveCounter + 1;

    }
}

class Wheels extends Action {
    public Wheels() {
        super("Wheels");
    }

    /**
     * Executes the Wheels action.
     * This action deals 14-24 dmg to the opponent + 5-8 def to the user
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (24 - 14 + 1)) + 14;
        final int actualDamage = context.enemy.takeDamage(dmgInst1, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;

    }
}

class CrystalAtelier extends Action {
    public CrystalAtelier() {
        super("CrystalAtelier");
    }

    /**
     * Executes the CrystalAtelier action.
     * This action deals 7-11 dmg twice + 4-8 dmg to the opponent + 8-11 def to the user
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (11 - 7 + 1)) + 7;
        final int dmgInst2 = (int) (Math.random() * (11 - 7 + 1)) + 7;
        final int dmgInst3 = (int) (Math.random() * (8 - 4 + 1)) + 4;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2+dmgInst3, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;

    }
}

class AtelierLogic extends Action {
    public AtelierLogic() {
        super("AtelierLogic");
    }

    /**
     * Executes the AtelierLogic action.
     * This action deals 4-8 dmg + 5-8 dmg + 7-12 dmg to the opponent
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (12 - 7 + 1)) + 7;
        final int dmgInst2 = (int) (Math.random() * (8 - 5 + 1)) + 5;
        final int dmgInst3 = (int) (Math.random() * (8 - 4 + 1)) + 4;
        final int actualDamage = context.enemy.takeDamage(dmgInst1+dmgInst2+dmgInst3, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Blasts for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;

    }
}

class OldBoys extends Action {
    public OldBoys() {
        super("OldBoys");
    }

    /**
     * Executes the OldBoys action.
     * This action deals 4-8 dmg to the opponent + 5-9 def to the user
     * @param context The context of the battle, including the wielder and enemy.
     */
    @Override
    public void execute(BattleContext context) {
        final int dmgInst1 = (int) (Math.random() * (8 - 5 + 1)) + 5;
        final int actualDamage = context.enemy.takeDamage(dmgInst1, context);

        context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Smashes for " + actualDamage + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        moveCounter = moveCounter + 1;

    }
}


class TriggerFurioso extends Action {
    public TriggerFurioso() {
        super("TriggerFurioso");
    }

    @Override
    public void execute(BattleContext context) {
        Loadout loadout = context.wielder.getLoadout();
        Gambit pocketed = loadout.getPocketedGambit();

        if (pocketed == null) {
            context.getLog().addEntry(
                context.wielder, null, getName(),
                context.wielder.getName() + " tries to play a Gambit... but none is ready!",
                context.round, BattleLog.EntryType.INFO
            );
            return;
        }

        if(moveCounter % 9 == 0) {
        pocketed.activate(context);
        loadout.swapPocketedGambit(null); // remove it from pocket
        }
        else {
            context.getLog().addEntry(
                context.wielder, null, getName(),
                context.wielder.getName() + " tries to play a Gambit... but none is ready!",
                context.round, BattleLog.EntryType.INFO);
                return;
        }

        context.getLog().addEntry(
            context.wielder, null, pocketed.getName(),
            context.wielder.getName() + " activates the Gambit: " + pocketed.getName() + "!",
            context.round, BattleLog.EntryType.MODIFIER
        );
    }

    }
}

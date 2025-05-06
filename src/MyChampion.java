import java.util.ArrayList;
import java.util.List;
/**
 * Your champion. Change the class name, the name field, and make it yours!
 */
public class TheBlackSilence extends Champion {
    public static final String NAME = "TheBlackSilence";
    public int moveCounter = 0;
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
        context.wielder.getLoadout().addTemporaryModifier(new FreeDef());

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
        context.wielder.getLoadout().addTemporaryModifier(new CrystalFreeDef());

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
        context.wielder.getLoadout().addTemporaryModifier(new CrystalFreeDef());
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
        context.wielder.getLoadout().addTemporaryModifier(new OldBoysFreeDef());
        moveCounter = moveCounter + 1;

    }
}

public class Furioso extends Gambit {
    public Furioso() {
        super("Furioso", "Trigger Condition: Only usable every 9 actions not including this attack. Inflicts 5 Bleed and 20-39 dmg to the target.", 1);
    }

    @Override
    public void activate(BattleContext context) {
        if(moveCounter % 9 == 0 ) {
            super.activate(context);
            final int dmgInst1 = (int) (Math.random() * (39 - 20 + 1)) + 20;
            context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());
            context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());
            context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());
            context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());
            context.wielder.getLoadout().addTemporaryModifier(new BleedEffect());
            context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes, Punches, Smashes, Pierces, and Blasts for " + dmgInst1 + " damage!",
            context.round, BattleLog.EntryType.ACTION
        );
        }
        else {
            final int dmgInst1 = 0;
            context.getLog().addEntry(
            context.wielder, context.enemy, getName(),
            context.wielder.getName() + " Slashes, Punches, Smashes, Pierces, and Blasts for " + dmgInst1 + " damage!",
            context.round, BattleLog.EntryType.ACTION);
        }
    }
}

/**
 * A temporary modifier that adds +5-8 def permanantly (kinda).
 */
class FreeDef extends TemporaryModifier {
    public FreeDef() {
        super("Free Def", "Adds +5-8 defense Permanantly.", 15);
    }

    @Override
    public int modifyDefense(int baseDefense, BattleContext context) {
        return baseDefense + (int) (Math.random() * (8 - 5 + 1)) + 5;
    }
}

/**
 * A temporary modifier that adds +8-11 def permanantly (kinda).
 */
class CrystalFreeDef extends TemporaryModifier {
    public CrystalFreeDef() {
        super("Free Def (Crystal Atelier)", "Adds +8-11 defense Permanantly.", 15);
    }

    @Override
    public int modifyDefense(int baseDefense, BattleContext context) {
        return baseDefense + (int) (Math.random() * (11 - 8 + 1)) + 8;
    }
}

/**
 * A temporary modifier that adds +5-9 def permanantly (kinda).
 */
class OldBoysFreeDef extends TemporaryModifier {
    public OldBoysFreeDef() {
        super("Free Def (Old Boys)", "Adds +5-9 defense Permanantly.", 15);
    }

    @Override
    public int modifyDefense(int baseDefense, BattleContext context) {
        return baseDefense + (int) (Math.random() * (9 - 5 + 1)) + 5;
    }
}

}

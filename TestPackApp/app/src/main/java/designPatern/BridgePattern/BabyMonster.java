package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class BabyMonster extends Monster{

    public BabyMonster() {
        treasure = "火柴棍";
    }

    @Override
    public String dropTreasure() {
        return treasure;
    }
}

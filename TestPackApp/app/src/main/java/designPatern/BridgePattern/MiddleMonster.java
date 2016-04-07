package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class MiddleMonster extends Monster{

    public MiddleMonster() {
        treasure = "黄金甲";
    }

    @Override
    public String dropTreasure() {
        return treasure;
    }
}

package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class BossMonster extends Monster{


    @Override
    public String dropTreasure() {
        return treasure;
    }
}

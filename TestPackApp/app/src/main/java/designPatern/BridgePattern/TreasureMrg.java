package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class TreasureMrg {

    public TreasureMrg() {

        Monster monster = new BabyMonster();

        GamePlayer gamePlayer = new MiddlePlayer();

        gamePlayer.killMonster(monster);
    }
}

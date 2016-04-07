package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class SeniorPlayer extends GamePlayer{
    @Override
    void killMonster(Monster monster) {
        System.out.print("SeniorPlayer has killed"+ monster.getClass().getName() +"and get a treasure="+monster.treasure);
    }
}

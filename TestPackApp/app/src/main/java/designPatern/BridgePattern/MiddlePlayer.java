package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class MiddlePlayer extends GamePlayer{
    @Override
    void killMonster(Monster monster) {
        System.out.print("MiddlePlayer has killed"+ monster.getClass().getName() +"and get a treasure="+monster.treasure);
    }
}

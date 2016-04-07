package designPatern.BridgePattern;

/**
 * Created by ryan on 4/6/16.
 */
public class JuiorPlayer extends GamePlayer{

    @Override
    void killMonster(Monster monster) {
        System.out.print("JuiorPlayer has killed"+ monster.getClass().getName() +"and get a treasure="+monster.treasure);
    }
}

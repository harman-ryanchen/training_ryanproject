package OopPrinciples;

/**
 * Created by ryan on 3/29/16.
 */
public class HumanMrg {

    private static HumanMrg humanMrg;
    private boolean isNorth = false;

    public static HumanMrg getInstance(){
        if (humanMrg==null){
            humanMrg = new HumanMrg();
        }
        return humanMrg;
    }

    public void chiFan(Human human){
        Fan fan;
        if (isNorth){
            fan = new YuMiFan();
        }else{
            fan = new RiceFan();
        }
        human.chifan(fan);
    }
    public void jianShen(Human human){
        human.jianshen();
    }
}

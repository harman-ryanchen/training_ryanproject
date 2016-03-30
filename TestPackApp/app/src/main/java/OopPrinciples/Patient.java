package OopPrinciples;

/**
 * Created by ryan on 3/30/16.
 */
public class Patient {

    public void requestDazhen(NurseA a){
        a.dazhen();
        System.out.println("这个病人需要打针");
    }
    public void requestZhixie(NurseB b){
        b.zhixie();
        System.out.println("这个病人需要止血");
    }
}

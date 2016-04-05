package designPatern;

/**
 * Created by ryan on 3/30/16.
 */
public class XiJinPing {
    private static XiJinPing xiJinPing;

    private XiJinPing() {

    }
    public static XiJinPing getInstance(){
        if (xiJinPing==null){
            xiJinPing = new XiJinPing();
        }
        return xiJinPing;
    }
}

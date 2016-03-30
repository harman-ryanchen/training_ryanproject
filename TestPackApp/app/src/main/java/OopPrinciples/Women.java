package OopPrinciples;

/**
 * Created by ryan on 3/29/16.
 */
public class Women extends Human{

    @Override
    public void jianshen(){
        if (weight>100){
            System.out.println("体重上升了,健身一下吧");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        weight--;
                        Thread.sleep(300);
                        jianshen();
                        System.out.println("健身完了,体重下降了吧");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    void chifan(final Fan fan) {
        if (weight<100){
            System.out.println("体重轻了好多,吃个饭吧");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("煮点饭吃");
                        fan.zhu();
                        chifan(fan);
                        Thread.sleep(300);
                        System.out.println("吃饭了,体重上升");
                        weight++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

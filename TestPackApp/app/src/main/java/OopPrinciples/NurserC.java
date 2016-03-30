package OopPrinciples;

/**
 * Created by ryan on 3/30/16.
 */
public class NurserC implements NurseA , NurseB {
    @Override
    public void dazhen() {
        System.out.println("会打针");
    }

    @Override
    public void zhixie() {
        System.out.println("也会止血");
    }
}

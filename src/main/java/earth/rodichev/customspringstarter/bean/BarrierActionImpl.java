package earth.rodichev.customspringstarter.bean;

public class BarrierActionImpl implements BarrierAction {

    @Override
    public void action() {
        System.out.println("Барьер сработал");
    }
}

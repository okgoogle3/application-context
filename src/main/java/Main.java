
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        while (true) {
            sleep(1500);
            ((Component2) ApplicationContext.getInstance().getComponents().get(Class.forName("Component2")))
                    .increaseChangeableField();
            ApplicationContext.getInstance().getComponentsObjects();
        }
    }
}

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext.getInstance().getComponentsObjects();
        Component2 component = (Component2) ApplicationContext.getInstance().getComponents().get(Class.forName("Component2"));
    }
}
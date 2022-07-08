@MyComponent
public class Component1 {

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Class: " + this.getClassName();
    }
}
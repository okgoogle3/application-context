@MyComponent
public class Component2 {

    @MyAutowired
    Component1 component1;

    public String getClassName(){
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Class: " + this.getClassName() + " autowired: " + component1.getClassName();
    }
}
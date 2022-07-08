@MyComponent
public class Component2 {

    @MyAutowired
    Component1 component1;

    private int testChangeableField = 0;
    public void increaseChangeableField(){
        testChangeableField++;
    }

    public String getClassName(){
        return this.getClass().getSimpleName() + " (" + testChangeableField + ")";
    }

    @Override
    public String toString() {
        return "\nThis class is " + this.getClassName() + " autowired is " + component1.getClassName();
    }
}
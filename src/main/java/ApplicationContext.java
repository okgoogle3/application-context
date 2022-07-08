import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationContext {

    private Map<Class<?>, Object> components;

    private static ApplicationContext instance = null;

    public static ApplicationContext getInstance(){
        if (instance == null){
            instance = new ApplicationContext();
        }
        return instance;
    }

    public ApplicationContext(){
        Reflections reflections = new Reflections("", new SubTypesScanner(false));
        List<Class<?>> componentClasses = reflections
                .getSubTypesOf(Object.class)
                .stream()
                .filter(c -> c.isAnnotationPresent(MyComponent.class))
                .toList();
        components = componentClasses.stream()
                .collect(Collectors.toMap(c -> c, c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }));
        components.forEach((aClass, object) -> {
            try {
                WireComponent(aClass, object);
            } catch (IllegalAccessException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void WireComponent(Class<?> someClass, Object object) throws IllegalAccessException, ClassNotFoundException {
        List<Field> autowiredFields = Arrays.stream(someClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAutowired.class)).toList();
        for (Field autowiredField : autowiredFields) {
            autowiredField.setAccessible(true);
            Class<?> autowiredFieldClass = Class.forName(autowiredField.getType().getName());
            autowiredField.set(object, components.get(autowiredFieldClass));
        }
    }

    public void getComponentsObjects(){
        System.out.println(components.values());
    }
}

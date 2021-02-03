import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    public static Properties props = new Properties();

    static {
        try {
            props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String prop) {
        return props.get(prop);
    }
}

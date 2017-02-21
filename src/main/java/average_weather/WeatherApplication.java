package average_weather;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by s.sergienko on 17.02.2017.
 */
public class WeatherApplication {
    private static CityTemp cityTemp;

    public static void main(String args[]) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/weather-context.xml");

        cityTemp = context.getBean(CityTemp.class);

        for (Map.Entry<String, Integer> entry : cityTemp.getRowTodayCitiesTemp("Kiev", "Dnipropetrovsk").entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

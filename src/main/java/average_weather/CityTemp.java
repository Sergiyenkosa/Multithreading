package average_weather;

import java.util.Map;

/**
 * Created by s.sergienko on 17.02.2017.
 */
public interface CityTemp {
    Map<String, Integer> getRowTodayCitiesTemp(String... cities);
}

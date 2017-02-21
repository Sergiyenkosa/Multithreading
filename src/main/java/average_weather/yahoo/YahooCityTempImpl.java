package average_weather.yahoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import average_weather.CityTemp;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by s.sergienko on 17.02.2017.
 */
public class YahooCityTempImpl implements CityTemp{
    private static final Logger log = LoggerFactory.getLogger(YahooCityTempImpl.class);

    @Override
    public Map<String, Integer> getRowTodayCitiesTemp(String... cities) {
        Map<String, Integer> citiesAverageTemp = new ConcurrentHashMap<>();
        log.info(Arrays.toString(cities));

        for (String city : cities) {
            new Thread() {
                @Override
                public void run() {
                    Quote quote = new RestTemplate().getForObject(URI.create(
                            "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather." +
                                    "forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
                                    "places(1)%20where%20text%3D%22" + city + "%22)&format=json&env=" +
                                    "store%3A%2F%2Fdatatables.org%2Falltableswithkeys"), Quote.class);

                    Forecast forecast = quote.getQuery().getResults().getChannel().getItem().getForecast()[0];
                    int rowTempC = ((forecast.getLow()+forecast.getHigh())/2-32)*5/9;
                    log.info(city + " " + String.valueOf(rowTempC));
                    citiesAverageTemp.put(city, rowTempC);
                }
            }.start();
        }

        while (citiesAverageTemp.size()!= cities.length) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error(e.toString());
                break;
            }
        }

        return citiesAverageTemp;
    }
}

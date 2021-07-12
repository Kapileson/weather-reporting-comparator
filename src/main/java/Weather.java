import utilities.Config;

import java.util.Objects;

public class Weather {

    private int temperature = 0;
    private int humidity = 0;
    private int pressure = 0;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = Integer.parseInt(temperature);
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = Integer.parseInt(humidity);
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = Integer.parseInt(pressure);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Weather weather = (Weather) obj;
        return Math.abs(temperature - weather.temperature) <= Integer.parseInt(Config.getAllowedVarianceTemp()) &&
                Math.abs(humidity - weather.humidity) <= Integer.parseInt(Config.getAllowedVarianceHumid()) &&
                Math.abs(pressure - weather.pressure) <= Integer.parseInt(Config.getAllowedVariancePressure());
    }

    @Override
    public String toString() {
        return "Weather {" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, humidity, pressure);
    }
}

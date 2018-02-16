import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement
{
    Observable observable;
    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(Observable observable)
    {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg)
    {
        if(observable instanceof WeatherData)
        {
            WeatherData weatherData = (WeatherData) observable;
            lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
            display();
        }
    }

    @Override
    public void display()
    {
        if(lastPressure < currentPressure)
            System.out.println("Forecast: Improving weather on the way!");
        else if(lastPressure > currentPressure)
            System.out.println("Forecast: Watch out for cooler, rainy weather");
        else
            System.out.println("Forecast: More of the same");
    }
}

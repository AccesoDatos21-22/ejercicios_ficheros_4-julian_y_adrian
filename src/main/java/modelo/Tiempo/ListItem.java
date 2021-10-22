package modelo.Tiempo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("temp")
	private Temp temp;

	@SerializedName("deg")
	private int deg;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("clouds")
	private int clouds;

	@SerializedName("feels_like")
	private FeelsLike feelsLike;

	@SerializedName("speed")
	private double speed;

	@SerializedName("dt")
	private int dt;

	@SerializedName("pop")
	private int pop;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("gust")
	private double gust;

	public int getSunrise(){
		return sunrise;
	}

	public Temp getTemp(){
		return temp;
	}

	public int getDeg(){
		return deg;
	}

	public int getPressure(){
		return pressure;
	}

	public int getClouds(){
		return clouds;
	}

	public FeelsLike getFeelsLike(){
		return feelsLike;
	}

	public double getSpeed(){
		return speed;
	}

	public int getDt(){
		return dt;
	}

	public int getPop(){
		return pop;
	}

	public int getSunset(){
		return sunset;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public int getHumidity(){
		return humidity;
	}

	public double getGust(){
		return gust;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"sunrise = '" + sunrise + '\'' + 
			",temp = '" + temp + '\'' + 
			",deg = '" + deg + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",clouds = '" + clouds + '\'' + 
			",feels_like = '" + feelsLike + '\'' + 
			",speed = '" + speed + '\'' + 
			",dt = '" + dt + '\'' + 
			",pop = '" + pop + '\'' + 
			",sunset = '" + sunset + '\'' + 
			",weather = '" + weather + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",gust = '" + gust + '\'' + 
			"}";
		}
}
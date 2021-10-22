package modelo.Marvel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("urls")
	private List<UrlsItem> urls;

	@SerializedName("stories")
	private Stories stories;

	@SerializedName("series")
	private Series series;

	@SerializedName("comics")
	private Comics comics;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("modified")
	private String modified;

	@SerializedName("id")
	private int id;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("events")
	private Events events;

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public List<UrlsItem> getUrls(){
		return urls;
	}

	public Stories getStories(){
		return stories;
	}

	public Series getSeries(){
		return series;
	}

	public Comics getComics(){
		return comics;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getModified(){
		return modified;
	}

	public int getId(){
		return id;
	}

	public String getResourceURI(){
		return resourceURI;
	}

	public Events getEvents(){
		return events;
	}
}
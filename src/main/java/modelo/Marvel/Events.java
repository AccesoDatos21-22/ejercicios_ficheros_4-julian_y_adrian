package modelo.Marvel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Events{

	@SerializedName("collectionURI")
	private String collectionURI;

	@SerializedName("available")
	private int available;

	@SerializedName("returned")
	private int returned;

	@SerializedName("items")
	private List<ItemsItem> items;

	public String getCollectionURI(){
		return collectionURI;
	}

	public int getAvailable(){
		return available;
	}

	public int getReturned(){
		return returned;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}
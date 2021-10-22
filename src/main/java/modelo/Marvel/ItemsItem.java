package modelo.Marvel;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("type")
	private String type;

	public String getName(){
		return name;
	}

	public String getResourceURI(){
		return resourceURI;
	}

	public String getType(){
		return type;
	}
}
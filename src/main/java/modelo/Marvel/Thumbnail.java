package modelo.Marvel;

import com.google.gson.annotations.SerializedName;

public class Thumbnail{

	@SerializedName("path")
	private String path;

	@SerializedName("extension")
	private String extension;

	public String getPath(){
		return path;
	}

	public String getExtension(){
		return extension;
	}
}
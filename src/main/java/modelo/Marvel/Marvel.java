package modelo.Marvel;

import com.google.gson.annotations.SerializedName;

public class Marvel{

	@SerializedName("copyright")
	private String copyright;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("attributionHTML")
	private String attributionHTML;

	@SerializedName("attributionText")
	private String attributionText;

	@SerializedName("etag")
	private String etag;

	@SerializedName("status")
	private String status;

	public String getCopyright(){
		return copyright;
	}

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public String getAttributionHTML(){
		return attributionHTML;
	}

	public String getAttributionText(){
		return attributionText;
	}

	public String getEtag(){
		return etag;
	}

	public String getStatus(){
		return status;
	}
}
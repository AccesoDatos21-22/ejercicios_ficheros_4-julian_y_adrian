package modelo.Marvel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("total")
	private int total;

	@SerializedName("offset")
	private int offset;

	@SerializedName("limit")
	private int limit;

	@SerializedName("count")
	private int count;

	@SerializedName("results")
	private List<ResultsItem> results;

	public int getTotal(){
		return total;
	}

	public int getOffset(){
		return offset;
	}

	public int getLimit(){
		return limit;
	}

	public int getCount(){
		return count;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}
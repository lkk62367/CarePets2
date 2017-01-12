package will.tw.carepets.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by william on 2016/12/21.
 */

public class Result {
    @SerializedName("offset")
    public int offset;
    @SerializedName("limit")
    public int limit;
    @SerializedName("count")
    public int count;
    @SerializedName("sort")
    public String sort;
    @SerializedName("results")
    public Results[] results;
}

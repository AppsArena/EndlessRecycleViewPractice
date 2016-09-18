package example.com.endlessrecycleviewpracticez;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mitu on 9/18/16.
 */
public class CustomerOrderListLimitModel {
    @SerializedName("Index")
    private int Index;
    @SerializedName("Count")
    private int Count;
    public CustomerOrderListLimitModel(int index, int count) {
        Index = index;
        Count = count;
    }

    public int getIndex() {
        return Index;
    }

    public int getCount() {
        return Count;
    }
}

package example.com.endlessrecycleviewpracticez;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mitu on 9/18/16.
 */
public interface UserPanelApiInterface {
    @POST("/CustomerInfo/OrderList/{customerId}/{status}")
    Call<List<CustomerInfoOrderListModel>> getCustomerInfo(@Path("customerId") int customerId, @Path("status") int status, @Body CustomerOrderListLimitModel customerOrderListLimitModel);
    @GET("/CustomerInfo/OrderListCount/{customerId}/{status}")
    Call<CountModel> getTotalCountofOrderlist(@Path("customerId") int customerId,@Path("status") int status);

}

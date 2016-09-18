package example.com.endlessrecycleviewpracticez;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewCustomerInfo;

    private CustomerOrderInfoAdapter mAdapterCustomerinfo;
    private UserPanelApiInterface mUserPanelApiInterfaceforTotalCount;
    private int mTotalCount;
    private int mDisplayedposition2;
    private LinearLayoutManager mLlmanager;
    private android.os.Handler mHandler;

    private static List<CustomerInfoOrderListModel> mDataSetcustomerinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager mLayoutManagerCustomerInfo;
        Retrofit retrofit = example.com.endlessrecycleviewpracticez.RetrofitSingleton.getInstance(getApplicationContext());
        mRecyclerViewCustomerInfo = (RecyclerView) findViewById(R.id.recycleviewCustomerInfo);
        mUserPanelApiInterfaceforTotalCount = retrofit.create(UserPanelApiInterface.class);
        mLayoutManagerCustomerInfo = new GridLayoutManager(getApplicationContext(), 1);
        mRecyclerViewCustomerInfo.setLayoutManager(mLayoutManagerCustomerInfo);
        mDataSetcustomerinfo = new ArrayList<>();
        mHandler = new Handler();
        totalCount();
        customerinfo();
        mAdapterCustomerinfo = new CustomerOrderInfoAdapter(mDataSetcustomerinfo, mRecyclerViewCustomerInfo, getApplicationContext());
        endlessScrolling();

    }

    private void totalCount() {

        Call<CountModel> totalcountoforderlist = mUserPanelApiInterfaceforTotalCount.getTotalCountofOrderlist(85618, 5);
        totalcountoforderlist.enqueue(new Callback<CountModel>() {
            @Override
            public void onResponse(Call<CountModel> call, Response<CountModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mTotalCount = response.body().getCount();
                    Log.d("totalcount", "onResponse: "+mTotalCount);

                }
            }

            @Override
            public void onFailure(Call<CountModel> call, Throwable t) {

            }
        });


    }

    private void endlessScrolling(){
        mRecyclerViewCustomerInfo.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLlmanager = (LinearLayoutManager) mRecyclerViewCustomerInfo.getLayoutManager();
                mDisplayedposition2 = mLlmanager.findFirstVisibleItemPosition();
            }
        });

        mAdapterCustomerinfo.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mDataSetcustomerinfo.add(null);
                mAdapterCustomerinfo.notifyItemInserted(mDataSetcustomerinfo.size() -1);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mDataSetcustomerinfo.remove(mDataSetcustomerinfo.size() - 1);
                        mAdapterCustomerinfo.notifyItemRemoved(mDataSetcustomerinfo.size() - 1);

                        int start = mDataSetcustomerinfo.size();
                        if( start < mTotalCount){
                            loadMore(start, 5);
                        }
                        else {
                            return;
                        }
                    }
                }, 200);
            }
        });
    }

    private void customerinfo(){


        Call<List<CustomerInfoOrderListModel>> customerInfo = mUserPanelApiInterfaceforTotalCount.getCustomerInfo(85618, 5,new CustomerOrderListLimitModel(0,6));
        customerInfo.enqueue(new Callback<List<CustomerInfoOrderListModel>>() {
            @Override
            public void onResponse(Call<List<CustomerInfoOrderListModel>> call, Response<List<CustomerInfoOrderListModel>> response) {
                Log.d("totalcount", "onResponse booking code : "+response.body().get(0).BookingCode);
                mDataSetcustomerinfo.addAll(response.body());
                mRecyclerViewCustomerInfo.setAdapter(mAdapterCustomerinfo);
            }

            @Override
            public void onFailure(Call<List<CustomerInfoOrderListModel>> call, Throwable t) {

            }
        });
    }


    private void loadMore(int index, int count){


        Call<List<CustomerInfoOrderListModel>> customerInfo = mUserPanelApiInterfaceforTotalCount.getCustomerInfo(85618, 5,new CustomerOrderListLimitModel(index,count));

        customerInfo.enqueue(new Callback<List<CustomerInfoOrderListModel>>() {
            @Override
            public void onResponse(Call<List<CustomerInfoOrderListModel>> call, Response<List<CustomerInfoOrderListModel>> response) {
                if(response.isSuccessful()  ) {
                    if (response.body().size() !=0) {

                        //  Log.d("USerLogin", "customerinfo: in the recycleview");
                        mDataSetcustomerinfo.addAll(response.body());
                        mRecyclerViewCustomerInfo.setAdapter(mAdapterCustomerinfo);
                    }

                    mLlmanager = (LinearLayoutManager) mRecyclerViewCustomerInfo.getLayoutManager();
                    mLlmanager.scrollToPositionWithOffset(mDisplayedposition2 , mDataSetcustomerinfo.size());
                    mAdapterCustomerinfo.setLoaded();
                }


            }

            @Override
            public void onFailure(Call<List<CustomerInfoOrderListModel>> call, Throwable t) {
                // Toast.makeText(getActivity()," নেটওয়ার্ক কানেকশন  ইরর",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

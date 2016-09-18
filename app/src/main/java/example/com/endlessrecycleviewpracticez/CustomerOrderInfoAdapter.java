package example.com.endlessrecycleviewpracticez;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mitu on 9/18/16.
 */
public class CustomerOrderInfoAdapter  extends RecyclerView.Adapter {

    private boolean mLoading;
    private List<CustomerInfoOrderListModel> mDataset;

    private OnLoadMoreListener mOnLoadMoreListener;
    private Context mContext;
    private int mVisibleThreshold = 5;
    private int mLastVisibleItem, mTotalItemCount;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;


    public CustomerOrderInfoAdapter(List<CustomerInfoOrderListModel> myDataset, RecyclerView recyclerView, Context applicationContext) {
        mDataset = myDataset;
        mContext = applicationContext;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            final LinearLayoutManager linearLayoutManager =
                    (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mTotalItemCount = linearLayoutManager.getItemCount();
                    mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!mLoading && mTotalItemCount <=(mLastVisibleItem + mVisibleThreshold)){
                        if (mOnLoadMoreListener != null){
                            mOnLoadMoreListener.onLoadMore();
                        }
                        mLoading = true;
                    }
                }
            });

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_layout_customer_order_info_list_layout, parent, false);
            vh = new DataObjectHolder(view);

        }else {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_layout_customer_order_info_list_layout, parent, false);
            vh = new DataObjectHolder(view);
        }
        return vh;
    }
    public void setLoaded() {
        mLoading = false;
    }
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataObjectHolder) {
            String bookingCode = "";
            if(mDataset.get(position).getDealTitle() != null) {
                bookingCode = mDataset.get(position).getDealTitle();
            }
            ((DataObjectHolder) holder).titleTextView.setText(String.valueOf(bookingCode));
        }
        else {

            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView titleTextView;
        LinearLayout buyAgainLinearLayout;

        public DataObjectHolder(View itemView) {
            super(itemView);

            buyAgainLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutorderlist);
            titleTextView = (TextView) itemView.findViewById(R.id.customer_info_Title_text_view);

        }

        @Override
        public void onClick(View view) {

        }
    }
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}

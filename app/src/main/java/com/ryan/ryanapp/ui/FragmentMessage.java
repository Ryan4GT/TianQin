package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.ui.customeview.PullToRefreshRecyclerView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentMessage extends FragmentBase {

    private final int HANDLER_GET_DATA = 1;
    private final int HANDLER_REFRESH = 2;
    private PullToRefreshRecyclerView<String> refreshRecyclerView;
    private List<String> msgs;
    private boolean isLoading;
    private int pageIndex = 1;

    public static FragmentMessage newInstance(Map<String, String> params) {
        FragmentMessage fragmentMessage = new FragmentMessage();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMessage.setArguments(args);
        return fragmentMessage;
    }
    public FragmentMessage() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (fragmentRootView = inflater.inflate(R.layout.fragment_message, container, false));
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        toolbar.setTitle(R.string.fragment_message_title);
        LinearLayout recyclerViewContainer = (LinearLayout) fragmentRootView.findViewById(R.id.recyclerViewContainer);
        refreshRecyclerView = new PullToRefreshRecyclerView<String>(getActivity()) {
            @Override public void onBindBaseViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                if(viewHolder instanceof FriendsViewHolder) {
                    FriendsViewHolder friendsViewHolder = (FriendsViewHolder) viewHolder;
                }
            }
            @Override public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup viewGroup, int itemType) {
                View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_message_item, viewGroup, false);
                return new FriendsViewHolder(itemView);
            }
            @Override public int getBaseItemViewType(int position) {
                return 0;
            }
            @Override public void onLoadMore() {
            }
            @Override public void onRefreshing() {
                pageIndex = 1;
            }
        };
        recyclerViewContainer.addView(refreshRecyclerView);
        msgs = refreshRecyclerView.getDatas();
    }


    public class FriendsViewHolder extends RecyclerView.ViewHolder {
        public ImageView headImageView;
        public TextView nicknameView;
        public FriendsViewHolder(View itemView) {
            super(itemView);
            headImageView = (ImageView) itemView.findViewById(R.id.headImageView);
            nicknameView = (TextView) itemView.findViewById(R.id.nicknameView);
        }
    }

}

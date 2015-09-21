package me.itangqi.buildingblocks.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.adapter.DailyListAdapter;
import me.itangqi.buildingblocks.api.ZhihuApi;
import me.itangqi.buildingblocks.mvp.bean.Daily;
import me.itangqi.buildingblocks.mvp.presenters.ViewPagerPresenter;
import me.itangqi.buildingblocks.mvp.view.IViewPager;
import me.itangqi.buildingblocks.widget.SimpleDividerItemDecoration;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IViewPager {
    private List<Daily> mNewsList = new ArrayList<>();
    private DailyListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String date;
    private ViewPagerPresenter mPresenter;

    @Bind(R.id.cardList)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        // TODO you can use bundle to transfer data
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            date = bundle.getString("date");
            mPresenter = new ViewPagerPresenter(this, date);
            setRetainInstance(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_list, container, false);
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity()
        ));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary);
        mAdapter = new DailyListAdapter(getActivity(), mNewsList);
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public List<Daily> getNewsContent() {
        mNewsList = mPresenter.getNewsContent();
        return mNewsList;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

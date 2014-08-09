package com.github.devnied.emvnfccard.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.activity.IContentActivity;
import com.github.devnied.emvnfccard.adapter.ViewPagerAdapter;
import com.github.devnied.emvnfccard.fragment.viewPager.IFragment;
import com.github.devnied.emvnfccard.fragment.viewPager.impl.CardDetailFragment;
import com.github.devnied.emvnfccard.fragment.viewPager.impl.LogFragment;
import com.github.devnied.emvnfccard.view.SlidingTabLayout;

/**
 * View pager
 * 
 * @author Millau Julien
 * 
 */
public class ViewPagerFragment extends Fragment implements IRefreshable {

	/**
	 * Tab layout
	 */
	private SlidingTabLayout mTabLayout;
	/**
	 * View pager
	 */
	private ViewPager mViewPager;

	/**
	 * View pager Adapter
	 */
	private ViewPagerAdapter mViewPagerAdapter;

	/**
	 * Fragment list
	 */
	private List<IFragment> fragments = new ArrayList<IFragment>();

	/**
	 * Content activity
	 */
	private IContentActivity mContentActivity;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.viewpager, container, false);
	}

	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		// Add fragments
		fragments.add(new CardDetailFragment(mContentActivity.getCard(), getString(R.string.viewpager_carddetail)));
		fragments.add(new LogFragment(mContentActivity.getLog(), getString(R.string.viewpager_log)));
		// View pager
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
		mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), fragments);
		mViewPager.setAdapter(mViewPagerAdapter);

		// Tab layout
		mTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
		mTabLayout.setViewPager(mViewPager);
		mTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

			@Override
			public int getIndicatorColor(final int position) {
				return Color.parseColor("#80d8ff");
			}

			@Override
			public int getDividerColor(final int position) {
				return Color.parseColor("#03a9f4");
			}

		});
	}

	/**
	 * Constructor using field
	 * 
	 * @param pContent
	 */
	public ViewPagerFragment(final IContentActivity pContent) {
		mContentActivity = pContent;
		pContent.setRefreshableContent(this);
	}

	@Override
	public void update() {
		for (IFragment frag : fragments) {
			if (frag instanceof LogFragment) {
				((LogFragment) frag).updateLog(mContentActivity.getLog());
			} else if (frag instanceof CardDetailFragment) {
				((CardDetailFragment) frag).update(mContentActivity.getCard());
			}
		}
		if (mViewPagerAdapter != null) {
			mViewPagerAdapter.notifyDataSetChanged();
			mTabLayout.updateTabs();
		}
	}
}
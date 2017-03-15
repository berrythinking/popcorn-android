/*
 * This file is part of Butter.
 *
 * Butter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Butter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Butter. If not, see <http://www.gnu.org/licenses/>.
 */

package butter.droid.ui.medialist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butter.droid.MobileButterApplication;
import butter.droid.base.providers.media.MediaProvider;
import butter.droid.ui.medialist.base.BaseMediaListFragment;

public class MediaListFragment extends BaseMediaListFragment implements MediaListView {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileButterApplication.getAppContext()
                .getComponent()
                .mediaListComponentBuilder()
                .mediaListModule(new MediaListModule(this))
                .build()
                .inject(this);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//
//        //don't load initial data in search mode
//        if (mAdapter.getItemCount() == 0) {
//            mCurrentCall = providerManager.getCurrentMediaProvider()
//                    .getList(new MediaProvider.Filters(mFilters), mCallback);/* fetch new items */
//            setState(State.LOADING);
//        } else {
//            updateUI();
//        }
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagingManager.getNextPage();
    }

    public static BaseMediaListFragment newInstance(MediaProvider.Filters.Sort filter,
            MediaProvider.Filters.Order defOrder, String genre) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SORT, filter);
        args.putSerializable(EXTRA_ORDER, defOrder);
        args.putString(EXTRA_GENRE, genre);

        MediaListFragment fragment = new MediaListFragment();
        fragment.setArguments(args);
        return fragment;
    }

}

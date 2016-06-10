
package com.cyd.lmplayer.ui.adapters;

import static com.cyd.lmplayer.Constants.SIZE_THUMB;
import static com.cyd.lmplayer.Constants.SRC_FIRST_AVAILABLE;
import static com.cyd.lmplayer.Constants.TYPE_ALBUM;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.cyd.lmplayer.R;
import com.cyd.lmplayer.cache.ImageInfo;
import com.cyd.lmplayer.cache.ImageProvider;
import com.cyd.lmplayer.helpers.utils.MusicUtils;
import com.cyd.lmplayer.ui.fragments.list.RecentlyAddedFragment;
import com.cyd.lmplayer.views.ViewHolderList;

import java.lang.ref.WeakReference;

public class RecentlyAddedAdapter extends SimpleCursorAdapter {

    private AnimationDrawable mPeakOneAnimation, mPeakTwoAnimation;

    private WeakReference<ViewHolderList> holderReference;

    private Context mContext;
    
    private ImageProvider mImageProvider;

    public RecentlyAddedAdapter(Context context, int layout, Cursor c, String[] from, int[] to,
            int flags) {
        super(context, layout, c, from, to, flags);
    	mContext = context;
    	mImageProvider = ImageProvider.getInstance( (Activity) mContext );
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = super.getView(position, convertView, parent);
        
        Cursor mCursor = (Cursor) getItem(position);
        // ViewHolderList
        ViewHolderList viewholder;

        if (view != null) {

            viewholder = new ViewHolderList(view);
            holderReference = new WeakReference<ViewHolderList>(viewholder);
            view.setTag(holderReference.get());

        } else {
            viewholder = (ViewHolderList)convertView.getTag();
        }

        // Track name
        String trackName = mCursor.getString(RecentlyAddedFragment.mTitleIndex);
        holderReference.get().mViewHolderLineOne.setText(trackName);

        // Artist name
        String artistName = mCursor.getString(RecentlyAddedFragment.mArtistIndex);
        holderReference.get().mViewHolderLineTwo.setText(artistName);

        // Album name
        String albumName = mCursor.getString(RecentlyAddedFragment.mAlbumIndex);
        

        // Album ID
        String albumId = mCursor.getString(RecentlyAddedFragment.mAlbumIdIndex);

        ImageInfo mInfo = new ImageInfo();
        mInfo.type = TYPE_ALBUM;
        mInfo.size = SIZE_THUMB;
        mInfo.source = SRC_FIRST_AVAILABLE;
        mInfo.data = new String[]{ albumId , artistName, albumName };
        
        mImageProvider.loadImage( viewholder.mViewHolderImage, mInfo );

        holderReference.get().mQuickContext.setVisibility(View.GONE);
        // Now playing indicator
        long currentaudioid = MusicUtils.getCurrentAudioId();
        long audioid = mCursor.getLong(RecentlyAddedFragment.mMediaIdIndex);
        if (currentaudioid == audioid) {
            holderReference.get().mPeakOne.setImageResource(R.anim.peak_meter_1);
            holderReference.get().mPeakTwo.setImageResource(R.anim.peak_meter_2);
            mPeakOneAnimation = (AnimationDrawable)holderReference.get().mPeakOne.getDrawable();
            mPeakTwoAnimation = (AnimationDrawable)holderReference.get().mPeakTwo.getDrawable();
            try {
                if (MusicUtils.mService.isPlaying()) {
                    mPeakOneAnimation.start();
                    mPeakTwoAnimation.start();
                } else {
                    mPeakOneAnimation.stop();
                    mPeakTwoAnimation.stop();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            holderReference.get().mPeakOne.setImageResource(0);
            holderReference.get().mPeakTwo.setImageResource(0);
        }
        return view;
    }
}

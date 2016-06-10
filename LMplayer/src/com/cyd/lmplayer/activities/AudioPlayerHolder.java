package com.cyd.lmplayer.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyd.lmplayer.helpers.utils.ApolloUtils;
import com.cyd.lmplayer.helpers.utils.MusicUtils;
import com.cyd.lmplayer.helpers.utils.ThemeUtils;
import com.cyd.lmplayer.preferences.SettingsHolder;
import com.cyd.lmplayer.service.ApolloService;
import com.cyd.lmplayer.service.ServiceToken;
import com.cyd.lmplayer.ui.adapters.PagerAdapter;
import com.cyd.lmplayer.ui.fragments.AudioPlayerFragment;
import com.cyd.lmplayer.ui.fragments.LrcShowFragment;
import com.cyd.lmplayer.ui.fragments.list.TracksFragment;
import com.cyd.lmplayer.IApolloService;
import com.cyd.lmplayer.R;

import static com.cyd.lmplayer.Constants.INTENT_ADD_TO_PLAYLIST;
import static com.cyd.lmplayer.Constants.INTENT_PLAYLIST_LIST;
import static com.cyd.lmplayer.Constants.MIME_TYPE;
import static com.cyd.lmplayer.Constants.PLAYLIST_QUEUE;
import static com.cyd.lmplayer.Constants.THEME_ITEM_BACKGROUND;

/**
 * To show the menu for the main play pager  ,to show the three Fragments
 * @Note This is the "holder" for the @TracksFragment(queue) and @AudioPlayerFragment
 */
public class AudioPlayerHolder extends Activity implements ServiceConnection {

    private ServiceToken mToken;

    // Options
    private static final int FAVORITE = 0;

    private static final int SEARCH = 1;

    private static final int EFFECTS_PANEL = 0;
    //滑动
    private PagerAdapter mPagerAdapter;
    //系统设置
    @Override
    protected void onCreate(Bundle icicle) {
        // 对于主题选择器和溢出菜单项
        if (ThemeUtils.overflowLight(this)) {
            setTheme(R.style.Apollo_Holo);
        } else {
            setTheme(R.style.Apollo_Holo_Light);
        }
        // 判断设备是否平板
        if (!ApolloUtils.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Control Media volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // Layout
        setContentView(R.layout.audio_player_browser);

        // 设置背景颜色
        initColorstrip();

        // 滑屏，上面导航那块
        initActionBar();

        // Important!
        initPager();
        super.onCreate(icicle);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // If an activity is requesting access to this activity, and
        // the activity is in the stack, the the fragments may need
        // be refreshed. Update the page adapter
        if (mPagerAdapter != null) {
            mPagerAdapter.refresh();
        }
        super.onNewIntent(intent);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder obj) {
        MusicUtils.mService = IApolloService.Stub.asInterface(obj);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        MusicUtils.mService = null;
    }

    /**
     * Update the MenuItems in the ActionBar
     */
    private final BroadcastReceiver mMediaStatusReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            invalidateOptionsMenu();
        }

    };

    @Override
    protected void onStart() {
        // Bind to Service
        mToken = MusicUtils.bindToService(this, this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ApolloService.META_CHANGED);
        filter.addAction(ApolloService.PLAYSTATE_CHANGED);

        registerReceiver(mMediaStatusReceiver, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        // Unbind
        if (MusicUtils.mService != null) {
            MusicUtils.unbindFromService(mToken);
            mToken = null;
        }

        unregisterReceiver(mMediaStatusReceiver);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, FAVORITE, 0, R.string.cd_favorite).setShowAsAction(
                MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(0, SEARCH, 0, R.string.cd_search).setIcon(R.drawable.apollo_holo_light_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_now_playing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favorite = menu.findItem(FAVORITE);
        MenuItem search = menu.findItem(SEARCH);
        if (MusicUtils.mService != null && MusicUtils.getCurrentAudioId() != -1) {
            if (MusicUtils.isFavorite(this, MusicUtils.getCurrentAudioId())) {
                favorite.setIcon(R.drawable.apollo_holo_light_favorite_selected);
            } else {
                favorite.setIcon(R.drawable.apollo_holo_light_favorite_normal);
                // Theme chooser
                ThemeUtils.setActionBarItem(this, favorite, "apollo_favorite_normal");
            }
        }
        // Theme chooser
        ThemeUtils.setActionBarItem(this, search, "apollo_search");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent();
                intent.setClass(this, MusicLibrary.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            }
            case FAVORITE: {
                MusicUtils.toggleFavorite();
                invalidateOptionsMenu();
                break;
            }
            case SEARCH: {
                //onSearchRequested();
            	Intent intent = new Intent();
            	intent.setClass(AudioPlayerHolder.this, SearchActivity.class);
            	AudioPlayerHolder.this.startActivity(intent);
				
                break;
            }
            case R.id.add_to_playlist: {
                Intent intent = new Intent(INTENT_ADD_TO_PLAYLIST);
                long[] list = new long[1];
                list[0] = MusicUtils.getCurrentAudioId();
                intent.putExtra(INTENT_PLAYLIST_LIST, list);
                startActivity(intent);
                break;
            }
            case R.id.eq: {
                Intent i = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
                i.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, MusicUtils.getCurrentAudioId());
                startActivityForResult(i, EFFECTS_PANEL);
                break;
            }
            case R.id.play_store: {
               // ApolloUtils.shopFor(this, MusicUtils.getArtistName());
                break;
            }
            case R.id.share: {
                shareCurrentTrack();
                break;
            }
            case R.id.settings: {
	        	startActivityForResult(new Intent(this, SettingsHolder.class),0);
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }   

    private void initActionBar() {
        ApolloUtils.showUpTitleOnly(getActionBar());

         //获取各应用包下指定资源ID
        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        int upId = Resources.getSystem().getIdentifier("up", "id", "android");

        TextView actionBarTitle = (TextView)findViewById(titleId);
        ImageView actionBarUp = (ImageView)findViewById(upId);

        // Theme chooser
        ThemeUtils.setActionBarBackground(this, getActionBar(), "action_bar_background");
        ThemeUtils.setTextColor(this, actionBarTitle, "action_bar_title_color");
        ThemeUtils.initThemeChooser(this, actionBarUp, "action_bar_up", THEME_ITEM_BACKGROUND);
    }

    /**
     * @return Share intent
     * @throws RemoteException
     */
    private String shareCurrentTrack() {
        if (MusicUtils.getTrackName() == null || MusicUtils.getArtistName() == null) {

        }

        Intent shareIntent = new Intent();
        String currentTrackMessage = getResources().getString(R.string.now_listening_to) + " "
                + MusicUtils.getTrackName() + " " + getResources().getString(R.string.by) + " "
                + MusicUtils.getArtistName();

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentTrackMessage);

        startActivity(Intent.createChooser(shareIntent,
                getResources().getString(R.string.share_track_using)));
        return currentTrackMessage;
    }

    /**
     * Initiate ViewPager and PagerAdapter
     */
    public void initPager() {
        // Initiate PagerAdapter展示滑动
        mPagerAdapter = new PagerAdapter(getFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString(MIME_TYPE, Audio.Playlists.CONTENT_TYPE);
        bundle.putLong(BaseColumns._ID, PLAYLIST_QUEUE);
        mPagerAdapter.addFragment(new TracksFragment(bundle));
        // Artists
        mPagerAdapter.addFragment(new AudioPlayerFragment());
        mPagerAdapter.addFragment(new LrcShowFragment());

        // Initiate ViewPager
        ViewPager mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mViewPager.setPageMargin(getResources().getInteger(R.integer.viewpager_margin_width));
        mViewPager.setPageMarginDrawable(R.drawable.viewpager_margin);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(1);//设置第一页

        // Theme chooser
        ThemeUtils.initThemeChooser(this, mViewPager, "viewpager", THEME_ITEM_BACKGROUND);
        ThemeUtils.setMarginDrawable(this, mViewPager, "viewpager_margin");
    }

    /**
     * 设置背景颜色
     */
    private void initColorstrip() {
        FrameLayout mColorstrip = (FrameLayout)findViewById(R.id.colorstrip);
        mColorstrip.setBackgroundColor(getResources().getColor(R.color.holo_blue_dark));
        ThemeUtils.setBackgroundColor(this, mColorstrip, "colorstrip");
    }
}

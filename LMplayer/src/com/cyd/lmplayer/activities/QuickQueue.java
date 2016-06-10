/**
 * 
 */

package com.cyd.lmplayer.activities;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio;

import com.cyd.lmplayer.ui.fragments.grid.QuickQueueFragment;

import static com.cyd.lmplayer.Constants.MIME_TYPE;
import static com.cyd.lmplayer.Constants.PLAYLIST_QUEUE;

/**
 * @author ³ÂÑÅµÏ
 */
public class QuickQueue extends Activity {

    @Override
    protected void onCreate(Bundle icicle) {
        // This needs to be called first
        super.onCreate(icicle);

        // Control Media volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Bundle bundle = new Bundle();
        bundle.putString(MIME_TYPE, Audio.Playlists.CONTENT_TYPE);
        bundle.putLong(BaseColumns._ID, PLAYLIST_QUEUE);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new QuickQueueFragment(bundle)).commit();
    }
}

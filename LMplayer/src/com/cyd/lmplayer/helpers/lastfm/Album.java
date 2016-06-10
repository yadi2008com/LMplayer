

package com.cyd.lmplayer.helpers.lastfm;

import java.util.HashMap;
import java.util.Map;

import com.cyd.lmplayer.helpers.DomElement;
import com.cyd.lmplayer.helpers.utils.StringUtilities;


/**
 * Wrapper class for Album related API calls and Album Bean.
 * 
 * @author ³ÂÑÅµÏ
 */
public class Album extends MusicEntry {

    static final ItemFactory<Album> FACTORY = new AlbumFactory();

    private String artist;

    private Album(String name, String url, String artist) {
        super(name, url);
        this.artist = artist;
    }

    private Album(String name, String url, String mbid, int playcount, int listeners,
            boolean streamable, String artist) {
        super(name, url, mbid, playcount, listeners, streamable);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    /**
     *
     * 
     * @param artist Artist's name
     * @param albumOrMbid Album name or MBID
     * @param apiKey The API key
     * @return Album metadata
     */
    public static Album getInfo(String artist, String albumOrMbid, String apiKey) {
        return getInfo(artist, albumOrMbid, null, apiKey);
    }

    /**
     * Get the metadata for an album on Last.fm using the album name or a
     * musicbrainz id. See playlist.fetch on how to get the album playlist.
     * 
     * @param artist Artist's name
     * @param albumOrMbid Album name or MBID
     * @param username The username for the context of the request. If supplied,
     *            the user's playcount for this album is included in the
     *            response.
     * @param apiKey The API key
     * @return Album metadata
     */
    public static Album getInfo(String artist, String albumOrMbid, String username, String apiKey) {
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtilities.isMbid(albumOrMbid)) {
            params.put("mbid", albumOrMbid);
        } else {
            params.put("artist", artist);
            params.put("album", albumOrMbid);
        }
        if (username != null) {
        	params.put("username", username);
		}
        Result result = Caller.getInstance().call("album.getInfo", apiKey, params);
        return ResponseBuilder.buildItem(result, Album.class);
    }

    private static class AlbumFactory implements ItemFactory<Album> {
        @Override
        public Album createItemFromElement(DomElement element) {
            Album album = new Album(null, null, null);
            MusicEntry.loadStandardInfo(album, element);
            if (element.hasChild("artist")) {
                album.artist = element.getChild("artist").getChildText("name");
                if (album.artist == null)
                    album.artist = element.getChildText("artist");
            }
            return album;
        }
    }
}

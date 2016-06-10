

package com.cyd.lmplayer.helpers.lastfm;

import java.util.HashMap;
import java.util.Map;

import com.cyd.lmplayer.helpers.DomElement;
import com.cyd.lmplayer.helpers.utils.StringUtilities;

/**
 * Bean that contains artist information.<br/>
 * This class contains static methods that executes API methods relating to
 * artists.<br/>
 * Method names are equivalent to the last.fm API method names.
 * 
 * @author ³ÂÑÅµÏ
 */
public class Artist extends MusicEntry {

    static final ItemFactory<Artist> FACTORY = new ArtistFactory();

    protected Artist(String name, String url) {
        super(name, url);
    }

    protected Artist(String name, String url, String mbid, int playcount, int listeners,
            boolean streamable) {
        super(name, url, mbid, playcount, listeners, streamable);
    }

    /**
     * Get {@link Image}s for this artist in a variety of sizes.
     * 
     * @param artistOrMbid The artist name in question
     * @param apiKey A Last.fm API key
     * @return a list of {@link Image}s
     */
    public static PaginatedResult<Image> getImages(String artistOrMbid, String apiKey) {
        return getImages(artistOrMbid, -1, -1, apiKey);
    }

    /**
     * Get {@link Image}s for this artist in a variety of sizes.
     * 
     * @param artistOrMbid The artist name in question
     * @param page Which page of limit amount to display
     * @param limit How many to return. Defaults and maxes out at 50
     * @param apiKey A Last.fm API key
     * @return a list of {@link Image}s
     */
    public static PaginatedResult<Image> getImages(String artistOrMbid, int page, int limit,
            String apiKey) {
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtilities.isMbid(artistOrMbid)) {
            params.put("mbid", artistOrMbid);
        } else {
            params.put("artist", artistOrMbid);
        }
        if (page != -1) {
        	params.put("page", Integer.toString(page));
		}
        if (limit != -1) {
        	params.put("limit", Integer.toString(limit));
		}     
        Result result = Caller.getInstance().call("artist.getImages", apiKey, params);
        return ResponseBuilder.buildPaginatedResult(result, Image.class);
    }

    private static class ArtistFactory implements ItemFactory<Artist> {
        @Override
        public Artist createItemFromElement(DomElement element) {
            Artist artist = new Artist(null, null);
            MusicEntry.loadStandardInfo(artist, element);
            return artist;
        }
    }
}

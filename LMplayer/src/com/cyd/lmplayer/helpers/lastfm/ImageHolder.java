

package com.cyd.lmplayer.helpers.lastfm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.cyd.lmplayer.helpers.DomElement;

/**
 * Abstract superclass for all items that may contain images (such as
 * {@link Artist}s, {@link Album}s or {@link Track}s).
 * 
 * @author ³ÂÑÅµÏ
 */
public abstract class ImageHolder {

    protected Map<ImageSize, String> imageUrls = new HashMap<ImageSize, String>();

    /**
     * Returns a Set of all {@link ImageSize}s available.
     * 
     * @return all sizes
     */
    public Set<ImageSize> availableSizes() {
        return imageUrls.keySet();
    }

    /**
     * Returns the URL of the image in the specified size, or <code>null</code>
     * if not available.
     * 
     * @param size The preferred size
     * @return an image URL
     */
    public String getImageURL(ImageSize size) {
        return imageUrls.get(size);
    }

    protected static void loadImages(ImageHolder holder, DomElement element) {
        Collection<DomElement> images = element.getChildren("image");
        for (DomElement image : images) {
            String attribute = image.getAttribute("size");
            ImageSize size = null;
            if (attribute == null) {
                size = ImageSize.LARGE; 
            } else {
                try {
                    size = ImageSize.valueOf(attribute.toUpperCase(Locale.ENGLISH));
                } catch (IllegalArgumentException e) {
                    // if they suddenly again introduce a new image size
                }
            }
            if (size != null)
                holder.imageUrls.put(size, image.getText());
        }
    }
    		
    public String getLargestImage() {
    	Set<ImageSize> sizes = availableSizes();
    	if(sizes.contains(ImageSize.MEGA))
        	return getImageURL(ImageSize.MEGA);
        else if(sizes.contains(ImageSize.EXTRALARGE))
        	return getImageURL(ImageSize.EXTRALARGE);
        else if(sizes.contains(ImageSize.LARGE))
        	return getImageURL(ImageSize.LARGE);    	
        else if(sizes.contains(ImageSize.MEDIUM))
        	return getImageURL(ImageSize.MEDIUM);  
        else
        	return getImageURL(ImageSize.LARGE);
    }
    
}

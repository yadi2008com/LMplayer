
package com.cyd.lmplayer.helpers.lastfm;

import java.util.Locale;

import com.cyd.lmplayer.helpers.DomElement;

/**
 * An <code>Image</code> contains metadata and URLs for an artist's image.
 * Metadata contains title, votes, format and other. Images are available in
 * various sizes, see {@link ImageSize} for all sizes.
 * 
 * @author Janni Kovacs
 * @see ImageSize
 * @see Artist#getImages(String, String)
 */
public class Image extends ImageHolder {

    static final ItemFactory<Image> FACTORY = new ImageFactory();

    private String url;

    private Image() {
    }

    public String getUrl() {
        return url;
    }

    private static class ImageFactory implements ItemFactory<Image> {
        @Override
        public Image createItemFromElement(DomElement element) {
            Image i = new Image();
            i.url = element.getChildText("url");
            DomElement sizes = element.getChild("sizes");
            for (DomElement image : sizes.getChildren("size")) {
                // code copied from ImageHolder.loadImages
                String attribute = image.getAttribute("name");
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
                    i.imageUrls.put(size, image.getText());
            }
            return i;
        }
    }
}

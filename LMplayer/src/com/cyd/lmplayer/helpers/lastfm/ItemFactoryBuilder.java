

package com.cyd.lmplayer.helpers.lastfm;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>ItemFactoryBuilder</code> can be used to obtain {@link ItemFactory
 * ItemFactories} for a specific type.
 * 
 * @author Janni Kovacs
 * @see ItemFactory
 */
final class ItemFactoryBuilder {

    private static final ItemFactoryBuilder INSTANCE = new ItemFactoryBuilder();

    @SuppressWarnings("rawtypes")
    private final Map<Class, ItemFactory> factories = new HashMap<Class, ItemFactory>();

    private ItemFactoryBuilder() {
        // register default factories
        addItemFactory(Album.class, Album.FACTORY);
        addItemFactory(Artist.class, Artist.FACTORY);
        addItemFactory(Image.class, Image.FACTORY);
    }

    /**
     * Retrieve the instance of the <code>ItemFactoryBuilder</code>.
     * 
     * @return the instance
     */
    public static ItemFactoryBuilder getFactoryBuilder() {
        return INSTANCE;
    }

    public <T> void addItemFactory(Class<T> itemClass, ItemFactory<T> factory) {
        factories.put(itemClass, factory);
    }

    /**
     * Retrieves an {@link ItemFactory} for the given type, or <code>null</code>
     * if no such factory was registered.
     * 
     * @param itemClass the type's Class object
     * @return the <code>ItemFactory</code> or <code>null</code>
     */
    @SuppressWarnings("unchecked")
    public <T> ItemFactory<T> getItemFactory(Class<T> itemClass) {
        return factories.get(itemClass);
    }
}

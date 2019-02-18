package uk.co.bbc.mediaservices.summariser.domain;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents a container for durations for a particular user.
 */
public class Duration {

    Map<String,MutableInt> durations;

    /**
     * Safety method to return `durations` or create it if it is null.
     */
    protected Map<String,MutableInt> getCategoryDurations() {
        if (durations == null) {
            durations = new HashMap<String,MutableInt>();
        }

        return durations;
    }

    /**
     * Returns the total summed durations from all categories.
     * @return int The total duration in seconds
     */
    public int getTotalDuration() {
        int i = 0;
        Iterator it = getCategoryDurations().keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            i += getCategoryDurations().get(key).get();
        }
        return i;
    }

    /**
     * Returns a duration from a specified category. If no category is found,
     * return will be zero; a safe value to avoid side-effects.
     * @param category The category key to return
     * @return int The total duration in seconds
     */
    public int getCategoryDuration(String category) {
        MutableInt mi = getCategoryDurations().get(category);
        if (mi == null) {
            return 0;
        }
        return mi.get();
    }

    /**
     * Adds a single category duration. Reusing the same `category` will add
     * the `durationInSeconds`, not create a new category.
     * e.g. addCategoryDuration()
     * @param category The category key to use.
     * @param durationInSeconds The duration in seconds to add.
     */
    public void addCategoryDuration(String category, int durationInSeconds) {
        MutableInt duration = getCategoryDurations().get(category);

        if (duration == null) {
            durations.put(category, new MutableInt(durationInSeconds));
        } else {
            duration.add(durationInSeconds);
        }
    }
}

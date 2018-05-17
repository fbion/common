package ebook.thinking.chapter18.Preferences;


import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2016/3/25.
 */
public class PreferencesDemo {
    public static void main(String[] args) throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Rubby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()) {
            System.out.println(key + " : " + prefs.get(key, null));
        }
        System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
}

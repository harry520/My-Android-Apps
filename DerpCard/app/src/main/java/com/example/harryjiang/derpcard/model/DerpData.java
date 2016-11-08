package com.example.harryjiang.derpcard.model;

import com.example.harryjiang.derpcard.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DerpData {

    private static final String[] titles = {"Nothingness cannot be defined",
            "Time is like a river made up of the events which happen, and a violent stream; " +
                    "for as soon as a thing has been seen, it is carried away, and another comes" +
                    " in its place, and this will be carried away too,",
            "But when I know that the glass is already broken, every minute with it is precious.",
            "For me, it is far better to grasp the Universe as it really is than to persist in" +
                    " delusion, however satisfying and reassuring.",
            "The seeker after the truth is not one who studies the writings of the ancients and," +
                    " following his natural disposition, puts his trust in them, but rather the" +
                    " one who suspects his faith in them and questions what he gathers from them," +
                    " the one who submits to argument and demonstration, and not to the " +
                    "sayings of a human being whose nature is fraught with all kinds " +
                    "of imperfection and deficiency.",
            "You must take personal responsibility. You cannot change the circumstances, the" +
                    " seasons, or the wind, but you can change yourself. That is something you" +
                    " have charge of."
    };
    private static final String[] subTitles = {"Bruce Lee", "Marcus Aurelius", "Meng Tzu", "Ajahn Chah", "Carl Sagan", "Alhazen", "Jim Rohn"};
    private static final int icon = R.drawable.ic_tonality_black_36dp;

    // Repeat process 4 times, so that we have enough data to demonstrate a scrollable RecyclerView.
    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < titles.length; i++) {
                ListItem item = new ListItem();
                item.setTitle(titles[i]);
                item.setSubTitle(subTitles[i]);
                item.setImageResId(icon);
                data.add(item);
            }
        }
        return data;
    }

    public static ListItem getRandomListItem() {
        int rand = new Random().nextInt(6);
        ListItem item = new ListItem();
        item.setTitle(titles[rand]);
        item.setSubTitle(subTitles[rand]);
        item.setImageResId(icon);
        return item;
    }
}

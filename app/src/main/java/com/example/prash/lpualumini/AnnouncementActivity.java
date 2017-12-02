package com.example.prash.lpualumini;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AnnouncementActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Use our own list adapter
        setListAdapter(new SpeechListAdapter(this));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        ((SpeechListAdapter)getListAdapter()).toggle(position);
    }

    /**
     * A sample ListAdapter that presents content
     * from arrays of speeches and text.
     *
     */
    private class SpeechListAdapter extends BaseAdapter {
        public SpeechListAdapter(Context context)
        {
            mContext = context;
        }


        /**
         * The number of items in the list is determined by the number of speeches
         * in our array.
         *
         * @see android.widget.ListAdapter#getCount()
         */
        public int getCount() {
            return mTitles.length;
        }

        /**
         * Since the data comes from an array, just returning
         * the index is sufficent to get at the data. If we
         * were using a more complex data structure, we
         * would return whatever object represents one
         * row in the list.
         *
         * @see android.widget.ListAdapter#getItem(int)
         */
        public Object getItem(int position) {
            return position;
        }

        /**
         * Use the array index as a unique id.
         * @see android.widget.ListAdapter#getItemId(int)
         */
        public long getItemId(int position) {
            return position;
        }

        /**
         * Make a SpeechView to hold each row.
         * @see android.widget.ListAdapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            SpeechView sv;
            if (convertView == null) {
                sv = new SpeechView(mContext, mTitles[position], mDialogue[position], mExpanded[position]);
            } else {
                sv = (SpeechView)convertView;
                sv.setTitle(mTitles[position]);
                sv.setDialogue(mDialogue[position]);
                sv.setExpanded(mExpanded[position]);
            }

            return sv;
        }

        public void toggle(int position) {
            mExpanded[position] = !mExpanded[position];
            notifyDataSetChanged();
        }

        /**
         * Remember our context so we can use it when constructing views.
         */
        private Context mContext;

        /**
         * Our data, part 1.
         */
        private String[] mTitles =
                {
                        "Announcement (1)",
                        "Announcement (2)",
                        "Announcement (3)",
                        "Announcement (4)",
                        "Announcement (5)",
                        "Announcement (6)",
                        "Announcement (7)",
                        "Announcement (8)"
                };

        /**
         * Our data, part 2.
         */
        private String[] mDialogue =
                {
                        "So shaken as we are, so wan with care," +
                                "Find we a time for frighted peace to pant," +
                                "And breathe short-winded accents of new broils" +
                                "To be commenced in strands afar remote." +
                                "No more the thirsty entrance of this soil" +
                                "Shall daub her lips with her own children's blood;" +
                                "Nor more shall trenching war channel her fields," +
                                "Nor bruise her flowerets with the armed hoofs" +
                                "Of hostile paces: those opposed eyes," +
                                "Which, like the meteors of a troubled heaven," ,


                        "Hear him but reason in divinity," +
                                "And all-admiring with an inward wish" +
                                "You would desire the king were made a prelate:" +
                                "Hear him debate of commonwealth affairs," +
                                "Any retirement, any sequestration" +
                                "From open haunts and popularity.",

                        "I come no more to make you laugh: things now," +
                                "That bear a weighty and a serious brow," +
                                "Sad, high, and working, full of state and woe," +
                                "Such noble scenes as draw the eye to flow," +
                                "We now present. Those that can pity, here" +
                                "May, if they think it well, let fall a tear;" +
                                "The subject will deserve it. Such as give" +
                                "Their money out of hope they may believe," ,


                        "First, heaven be the record to my speech!" +
                                "In the devotion of a subject's love," +
                                "Tendering the precious safety of my prince," +
                                "And free from other misbegotten hate," +
                                "Come I appellant to this princely presence." +
                                "Now, Thomas Mowbray, do I turn to thee," +
                                "And mark my greeting well; for what I speak" +
                                "My body shall make good upon this earth," ,


                        "Now is the winter of our discontent" +
                                "Made glorious summer by this sun of York;" +
                                "And all the clouds that lour'd upon our house" +
                                "In the deep bosom of the ocean buried." +
                                "Now are our brows bound with victorious wreaths;" +
                                "Our bruised arms hung up for monuments;" +
                                "Of Edward's heirs the murderer shall be." +
                                "Dive, thoughts, down to my soul: here" +
                                "Clarence comes.",

                        "To bait fish withal: if it will feed nothing else," +
                                "it will feed my revenge. He hath disgraced me, and" +
                                "hindered me half a million; laughed at my losses," +
                                "mocked at my gains, scorned my nation, thwarted my" +
                                "Christian example? Why, revenge. The villany you" +
                                "teach me, I will execute, and it shall go hard but I" +
                                "will better the instruction.",

                        "Virtue! a fig! 'tis in ourselves that we are thus" +
                                "or thus. Our bodies are our gardens, to the which" +
                                "our wills are gardeners: so that if we will plant" +
                                "stings, our unbitted lusts, whereof I take this that" +
                                "you call love to be a sect or scion.",

                        "Blow, winds, and crack your cheeks! rage! blow!" +
                                "You cataracts and hurricanoes, spout" +
                                "Crack nature's moulds, an germens spill at once," +
                                "That make ingrateful man!"
                };

        /**
         * Our data, part 3.
         */
        private boolean[] mExpanded =
                {
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false
                };
    }

    /**
     * We will use a SpeechView to display each speech. It's just a LinearLayout
     * with two text fields.
     *
     */
    private class SpeechView extends LinearLayout {
        public SpeechView(Context context, String title, String dialogue, boolean expanded) {
            super(context);

            this.setOrientation(VERTICAL);

            // Here we build the child views in code. They could also have
            // been specified in an XML file.

            mTitle = new TextView(context);
            mTitle.setText(title);
            mTitle.setTextSize(30);
            mTitle.setTextColor(Color.rgb(247,29,29));
            mTitle.setBackgroundColor(Color.rgb(51,139,136));
            addView(mTitle, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            mDialogue = new TextView(context);
            mDialogue.setText(dialogue);
            mDialogue.setTextSize(20);
            addView(mDialogue, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            mDialogue.setVisibility(expanded ? VISIBLE : GONE);
        }

        /**
         * Convenience method to set the title of a SpeechView
         */
        public void setTitle(String title) {
            mTitle.setText(title);
        }

        /**
         * Convenience method to set the dialogue of a SpeechView
         */
        public void setDialogue(String words) {
            mDialogue.setText(words);
        }

        /**
         * Convenience method to expand or hide the dialogue
         */
        public void setExpanded(boolean expanded) {
            mDialogue.setVisibility(expanded ? VISIBLE : GONE);
        }

        private TextView mTitle;
        private TextView mDialogue;
    }
}

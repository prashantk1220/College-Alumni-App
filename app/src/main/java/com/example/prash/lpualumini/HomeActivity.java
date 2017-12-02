package com.example.prash.lpualumini;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.Gallery.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,  AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory{

    ImageSwitcher mSwitcher;
    String sname,sfeed,scollegId;
    TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //tv = (TextView)findViewById(R.id.textView);
        //tv2 = (TextView)findViewById(R.id.textView5);
        setSupportActionBar(toolbar);
//        Bundle b =getIntent().getExtras();
//        scollegId=b.getString("collegid");
        //sname=b.getString("sname");
        //sfeed=b.getString("sfeed");
        //tv.setText(sname);

        sfeed="this is a very good application";
       // tv2.setText(sfeed);
        //tv.setText(sname);

        mSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener(this);





//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Thanks for sharing", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onItemSelected(AdapterView parent, View v, int position, long id) {
        mSwitcher.setImageResource(mImageIds[position]);
    }

    public void onNothingSelected(AdapterView parent) {
    }

    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        return i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_events) {
            NotificationManager nm = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            Notification n = new Notification(
                    android.R.drawable.stat_notify_more,"AlertMessage",
                    System.currentTimeMillis()
            );

            n.defaults=Notification.DEFAULT_ALL; // set light, sound and vibrate
            Intent intent= new Intent(HomeActivity.this, HomeActivity.class);
            PendingIntent pi = PendingIntent.getActivity(HomeActivity.this, 0, intent, 0);

            Notification.Builder builder = new Notification.Builder(HomeActivity.this);

            builder.setAutoCancel(true);
            builder.setTicker("Events");
            builder.setContentTitle("No Current Events");
            builder.setContentText("Sorry no events now, Thank you !:) ");
            builder.setSmallIcon(R.drawable.lpu);
            builder.setContentIntent(pi);
            builder.setOngoing(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                builder.build();
            }

            n = builder.getNotification();
            nm.notify(11, n);

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this,GridphotoActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this,FeedbackActivity.class);
            //i.putExtra("sname",sname);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(this,AboutUsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_news) {
            Intent i = new Intent(this,NewsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_association) {
            Intent i = new Intent(this,AssociationActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_broadcast) {
            Intent i = new Intent(this,BroadcastActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_activities) {
            Intent i = new Intent(this,GamesActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Resources resources = getResources();

            Intent emailIntent = new Intent();
            emailIntent.setAction(Intent.ACTION_SEND);
            // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
            emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_native)));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
            emailIntent.setType("message/rfc822");

            PackageManager pm = getPackageManager();
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");


            Intent openInChooser = Intent.createChooser(emailIntent, resources.getString(R.string.share_chooser_text));

            List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                if (packageName.contains("android.email")) {
                    emailIntent.setPackage(packageName);
                } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm") || packageName.contains("whatsapp") || packageName.contains("linkedin")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    if (packageName.contains("twitter")) {
                        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_twitter));
                    } else if (packageName.contains("facebook")) {
                        // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                        // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                        // will show the <meta content ="..."> text from that page with our link in Facebook.
                        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_facebook));
                    } else if (packageName.contains("mms")) {
                        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_sms));
                    } else if (packageName.contains("android.gm")) {
                        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_gmail)));
                        intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
                        intent.setType("message/rfc822");
                    } else if (packageName.contains("whatsapp")) {

                        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_watsapp));
                    } else if (packageName.contains("linkedin")) {

                        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_linkedin));
                    }
                    intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                }
            }
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);

        } else if (id == R.id.nav_send) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("Verification code is 12345", "Verify");
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }

        private Context mContext;

    }

    private Integer[] mThumbIds = {
            R.drawable.sample_thumb_0, R.drawable.sample_thumb_1,
            R.drawable.sample_thumb_2, R.drawable.sample_thumb_3,
            R.drawable.sample_thumb_4, R.drawable.sample_thumb_5,
            R.drawable.sample_thumb_6, R.drawable.sample_thumb_7};

    private Integer[] mImageIds = {
            R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};


    public void message(View v){
        Intent i = new Intent(this,MessageActivity.class);
        startActivity(i);
    }

    public void announce(View v){
        Intent i = new Intent(this,AnnouncementActivity.class);
        startActivity(i);
    }

    public void gallery(View v){
        Intent i = new Intent(this,GridphotoActivity.class);
        startActivity(i);
    }

    public void feedback(View v){
        Intent i = new Intent(this,FeedbackActivity.class);
        startActivity(i);
    }

}

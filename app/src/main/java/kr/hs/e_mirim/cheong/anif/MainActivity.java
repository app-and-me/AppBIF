package kr.hs.e_mirim.cheong.anif;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    private FirebaseDatabase mFirebaseDatabase;

    ViewPager viewPager;
    //  LinearLayout sliderdotsppanel;
    //   private int dotscount;
//    private ImageView[] dots;

    Button btn1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //etContent = (EditText)findViewById(R.id.content);

        if( mFirebaseUser == null ){
            startActivity(new Intent(MainActivity.this,AuthActivity.class));
            finish();
            return;
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //  sliderdotsppanel = (LinearLayout) findViewById(R.id.SliderDots);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 4000, 4000);
    }

    public void firststory(View view) {
        Intent intent = new Intent(MainActivity.this, FirstStory.class);
        startActivity(intent);
    }

    public void secondstory(View view) {
        Intent intent = new Intent(MainActivity.this, SecondStory.class);
        startActivity(intent);
    }
    public void thirdstory(View view) {
        Intent intent = new Intent(MainActivity.this, ThirdStory.class);
        startActivity(intent);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
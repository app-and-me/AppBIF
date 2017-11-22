package kr.hs.e_mirim.cheong.anif;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondStory extends AppCompatActivity {
    Adapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_story);
        viewPager = (ViewPager) findViewById(R.id.view);
        adapter = new Adapter(this, 2);
        viewPager.setAdapter(adapter);
    }
}

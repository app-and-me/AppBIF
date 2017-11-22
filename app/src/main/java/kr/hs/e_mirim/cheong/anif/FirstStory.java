package kr.hs.e_mirim.cheong.anif;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstStory extends AppCompatActivity {
    Adapter adapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_story);
        viewPager = (ViewPager) findViewById(R.id.view);
        adapter = new Adapter(this, 1);
        viewPager.setAdapter(adapter);
    }
}

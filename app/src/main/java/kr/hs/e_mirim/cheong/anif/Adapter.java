package kr.hs.e_mirim.cheong.anif;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by LG on 2017-11-23.
 */

public class Adapter  extends PagerAdapter {
    private int[] images={R.drawable.app1,R.drawable.app2,R.drawable.app3,R.drawable.app4,R.drawable.app5,R.drawable.app6, R.drawable.app7,R.drawable.app8,R.drawable.app9,R.drawable.app10};
    private int[] images2={R.drawable.ss1,R.drawable.ss2,R.drawable.ss3,R.drawable.ss4,R.drawable.ss5,R.drawable.ss6,R.drawable.ss7,R.drawable.ss8,R.drawable.ss9};
    private int[] images3={R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10};
    private Context context;
    int num;
    public Adapter(Context context) {
        this.context = context;
    }
    public Adapter(Context context, int num) {
        Log.d(String.valueOf(num),"로그 내용");
        this.context = context;
        this.num = num;
    }
    @Override
    public int getCount() {
        if(num==1){return 9;}
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((LinearLayout)object);
    }

    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.firstslider,container,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        if(num==1){
        imageView.setImageResource(images2[position]);}
        else if(num==2){
            imageView.setImageResource(images[position]);
        }
        else if(num==3){
            imageView.setImageResource(images3[position]);
        }
        container.addView(v);
        return v;
    }
    public void destroyItem(ViewGroup container, int position, Object object){
        container.invalidate();
    }
}
package upiynar.cback;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setCustomView(getLayoutInflater().inflate(R.layout.custom_action_bar, null));
        getSupportActionBar().setTitle("Etkinlikler");
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        //Bak buradaki olay Unity'den beri bildiğimiz klasik olay,PagerAdapter isimli classı buraya inherite ettik.
        PagerAdapter pagerAdapter = new upiynar.cback.PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(vp);
        vp.setOnPageChangeListener(this);
        //Reyis yukardaki viewpager kodlarına dokunma şimdiden unuttum amk nasıl yazdığımı.

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("SAYFA DEGİSTİ ALGİLADIM","SAYFA DEGİSTİ ALGİLADIM"+ position);
        if (position == 0){
            getSupportActionBar().setTitle("Etkinlikler");
        }else if (position == 1){
            getSupportActionBar().setTitle("Haberler");
        }else if (position == 2){
            getSupportActionBar().setTitle("Video");
        }else if(position == 3){
            getSupportActionBar().setTitle("Panel");
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

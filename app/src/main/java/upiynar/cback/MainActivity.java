package upiynar.cback;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        //Bak buradaki olay Unity'den beri bildiğimiz klasik olay,PagerAdapter isimli classı buraya inherite ettik.
        PagerAdapter pagerAdapter = new upiynar.cback.PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(vp);
        //Reyis yukardaki viewpager kodlarına dokunma şimdiden unuttum amk nasıl yazdığımı.

    }
}

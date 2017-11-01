package upiynar.cback;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by shock on 11/1/2017.
 */

public class Etkinlikler_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Etkinlik> mEtkinlikListesi;
    public Etkinlikler_Adapter(Activity activity, List<Etkinlik> etkinlikler){
        //XML'i alıp View'e çevirecek inflater'ı örnekleyelim.
        mInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEtkinlikListesi = etkinlikler;
    }
    @Override
    public int getCount() {
        return mEtkinlikListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return mEtkinlikListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View etkinliklayout;
        etkinliklayout = mInflater.inflate(R.layout.etkinlik_lview_layout,null);
        ImageView etkinlik_image = (ImageView)etkinliklayout.findViewById(R.id.etkinlik_image);
        TextView etkinlik_name = (TextView)etkinliklayout.findViewById(R.id.etkinlik_name);
        TextView etkinlik_date = (TextView)etkinliklayout.findViewById(R.id.date_text);
        TextView etkinlik_time = (TextView)etkinliklayout.findViewById(R.id.etkinlik_time);
        //Ben bunları yazarken red zeppelin çalıyor, hava soğuk; keşke motorum hala olsaydı da çalışa akıp donarak şarap içebilseydim.
        //Şarap ve sigara
        //Şarap ve soğuk
        //Şarap ve rüzgar
        //Şarap ve dalga
        //Ahhh im gonna cry baby
        Etkinlik etkinlik = mEtkinlikListesi.get(position);
        etkinlik_name.setText(etkinlik.getEtkinlik_name());
        etkinlik_date.setText(etkinlik.getEtkinlik_date());
        etkinlik_time.setText(etkinlik.getEtkinlik_time());
        Picasso.with(etkinliklayout.getContext()).load(etkinlik.getEtkinlik_imgURL()).into(etkinlik_image);
        return etkinliklayout;
    }
}

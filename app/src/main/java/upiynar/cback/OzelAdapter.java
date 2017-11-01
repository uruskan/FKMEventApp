package upiynar.cback;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shock on 10/24/2017.
 */

public class OzelAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Haber> mHaberListesi;
    public OzelAdapter(Activity activity,List<Haber> etkinlikler){
        //XML'i alıp View'e çevirecek inflater'ı örnekleyelim.
        mInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHaberListesi = etkinlikler;
    }
    @Override
    public int getCount() {
        return mHaberListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return mHaberListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View etkinlikLayout;
        etkinlikLayout = mInflater.inflate(R.layout.haber_lview_layout,null);
        TextView baslik = (TextView) etkinlikLayout.findViewById(R.id.Baslik);
        TextView kisaAciklama = (TextView) etkinlikLayout.findViewById(R.id.kisaAciklama);
        TextView Tarih = (TextView) etkinlikLayout.findViewById(R.id.Tarih);
       ImageView simge = (ImageView) etkinlikLayout.findViewById(R.id.simge);
        Haber haber = mHaberListesi.get(position);
        baslik.setText(haber.getBaslik());
        kisaAciklama.setText(haber.getKisaAciklama());
        Tarih.setText(haber.getTarih());
        //Buraya picasso kodu ile resim atanacak mesela Picasso.with(context).load('haber.getresimURL').into(simge);
        Picasso.with(etkinlikLayout.getContext()).load(haber.getResimURL()).into(simge);

        return etkinlikLayout;
    }
}

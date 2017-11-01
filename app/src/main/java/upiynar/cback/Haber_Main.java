package upiynar.cback;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static java.security.AccessController.getContext;

public class Haber_Main extends AppCompatActivity  {
    TextView baslik,icerik;
    ImageView resim,popresim;
    PopupWindow popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber__main);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.Annen);
        final View popupView = inflater.inflate(R.layout.popup_layout,null);
        final int pozisyon = getIntent().getIntExtra("pozisyon",0);
        popresim = (ImageView)findViewById(R.id.popup_image);
        baslik = (TextView)findViewById(R.id.baslik);
        icerik = (TextView)findViewById(R.id.uzunAciklama);
        resim = (ImageView)findViewById(R.id.imageView);
        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick: ","ONCLİCK ALGILADIMM" );
                popup = new PopupWindow(popupView,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        true
                );
             popup.showAtLocation(mainLayout,Gravity.CENTER,0,0);

            }

        });
        final ArrayList<Haber> haberler = new ArrayList<Haber>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Haberler");
        Picasso.with(Haber_Main.this).load(haberler.get(pozisyon).getResimURL()).into(popresim);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String baslik = ds.child("Başlık").getValue().toString();
                    String kisaAciklama = ds.child("Kısa Açıklama").getValue().toString();
                    String tarih = ds.child("Tarih").getValue().toString();
                    String resimURL = ds.child("Resim URL").getValue().toString();
                    String uzunAciklama = ds.child("Uzun Aciklama").getValue().toString();
                    haberler.add(new Haber(baslik,kisaAciklama,tarih,resimURL,uzunAciklama));
                }
                icerik.setText(haberler.get(pozisyon).getUzunAciklama());
                baslik.setText(haberler.get(pozisyon).getBaslik());
                Picasso.with(Haber_Main.this).load(haberler.get(pozisyon).getResimURL()).into(resim);
                getSupportActionBar().setTitle(baslik.getText());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

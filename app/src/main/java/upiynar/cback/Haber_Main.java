package upiynar.cback;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Haber_Main extends AppCompatActivity {
    TextView icerik;
    ImageView resim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber__main);
        final int pozisyon = getIntent().getIntExtra("pozisyon",0);
        icerik = (TextView)findViewById(R.id.textView);
        resim = (ImageView)findViewById(R.id.imageView);
        final ArrayList<Haber> haberler = new ArrayList<Haber>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Haberler");
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
                Picasso.with(Haber_Main.this).load(haberler.get(pozisyon).getResimURL()).into(resim);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

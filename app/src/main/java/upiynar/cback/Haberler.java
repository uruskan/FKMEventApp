package upiynar.cback;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Haberler.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Haberler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Haberler extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //final List<Haber> etkinlikler = new ArrayList<Haber>();

    private OnFragmentInteractionListener mListener;

    public Haberler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Haberler.
     */
    // TODO: Rename and change types and number of parameters
    public static Haberler newInstance(String param1, String param2) {
        Haberler fragment = new Haberler();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_haberler, container, false);
        final List<Haber> haberler = new ArrayList<Haber>();
        final ListView listemiz = (ListView)rootView.findViewById(R.id.etkinlik_lview);
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
                haberler.add(new Haber(baslik,kisaAciklama,tarih,resimURL));
            }
                OzelAdapter adaptorum = new OzelAdapter(getActivity(),haberler);
                listemiz.setAdapter(adaptorum);
                listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Buraya tıklanınca işler yapılacak moruq.
                    //Ben şimdi şey yapmayı planlıyorum yeni activitye attırıcam buradan.
                    //Yeni activity nasıl oluşturuyoduk amk dsgfuerhuewhgue
                        //Sanki hiçbir şey bilmiyorum of bana niye bazen böyle oluyor.
                    }
                });
                dbRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /** @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

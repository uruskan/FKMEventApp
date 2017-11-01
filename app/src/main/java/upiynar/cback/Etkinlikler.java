package upiynar.cback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
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
 * {@link Etkinlikler.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Etkinlikler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Etkinlikler extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public Etkinlikler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Etkinlikler.
     */
    // TODO: Rename and change types and number of parameters
    public static Etkinlikler newInstance(String param1, String param2) {
        Etkinlikler fragment = new Etkinlikler();
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

        View etkinlikview = (View) inflater.inflate(R.layout.fragment_etkinlikler, container, false);
        //Todo : SEXXXXXXX
        final ArrayList<Etkinlik> etkinlikler = new ArrayList<Etkinlik>();
        final ListView liste = (ListView)etkinlikview.findViewById(R.id.etkinlik_lview);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Etkinlikler");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds1 : dataSnapshot.getChildren()){
                    String etkinlik_name = ds1.child("Etkinlik_Name").getValue().toString();
                    String etkinlik_date = ds1.child("Etkinlik_Date").getValue().toString();
                    String etkinlik_time = ds1.child("Etkinlik_Time").getValue().toString();
                    String etkinlik_imgURL = ds1.child("Etkinlik_imgURL").getValue().toString();
                    etkinlikler.add(new Etkinlik(etkinlik_name,etkinlik_date,etkinlik_time,etkinlik_imgURL));
                }
                Etkinlikler_Adapter adaptorum = new Etkinlikler_Adapter(getActivity(),etkinlikler);
                liste.setAdapter(adaptorum);
                liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Todo: Şimdi bundan sonrası şeys bro.Yani biraz şarap olsaydı şimki keşke.

                    }
                });
                dbRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  etkinlikview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
     */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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

package upiynar.cback;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Admin_Panel.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Admin_Panel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_Panel extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Admin_Panel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_Panel.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_Panel newInstance(String param1, String param2) {
        Admin_Panel fragment = new Admin_Panel();
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
        final View rootView  = inflater.inflate(R.layout.fragment_admin__panel, container, false);
        final EditText editBaslik = (EditText)rootView.findViewById(R.id.editBaslik);
        final EditText editKisaAciklama = (EditText)rootView.findViewById(R.id.editKisaAciklama);
        final EditText editTarih = (EditText)rootView.findViewById(R.id.editTarih);
        final EditText editResim = (EditText)rootView.findViewById(R.id.editresimURL);
        Button kaydet = (Button)rootView.findViewById(R.id.veri_ekle);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("Haberler");
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference idRef = dbRef.push();
                String t_baslik,t_kisaAciklama,t_Tarih,t_resimURL;
                t_baslik = editBaslik.getText().toString();
                t_kisaAciklama = editKisaAciklama.getText().toString();
                t_Tarih = editTarih.getText().toString();
                t_resimURL = editResim.getText().toString();
                if(!t_baslik.equals("") && !t_kisaAciklama.equals("") && !t_Tarih.equals("") && !t_resimURL.equals("")){
                    idRef.child("Başlık").setValue(t_baslik);
                    idRef.child("Kısa Açıklama").setValue(t_kisaAciklama);
                    idRef.child("Tarih").setValue(t_Tarih);
                    idRef.child("Resim URL").setValue(t_resimURL);
                    editBaslik.setText("Başlık Giriniz");
                    editKisaAciklama.setText("Kısa Açıklama Giriniz");
                    editTarih.setText("Tarih Giriniz");
                    editResim.setText("Resim URL'si");
                }
                else{
                    Toast.makeText(rootView.getContext(),"Lütfen Tüm Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
                }
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

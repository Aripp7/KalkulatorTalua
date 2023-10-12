package com.arip.kalkulatortalua;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IkatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IkatFragment extends Fragment {
    View view;
    EditText jml_ikat, jml_kertas, jml_butir;
    Button tambah, bersihkan;
    TextView totalbutir;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IkatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IkatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IkatFragment newInstance(String param1, String param2) {
        IkatFragment fragment = new IkatFragment();
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
        view =  inflater.inflate(R.layout.fragment_ikat, container, false);

        tambah          = view.findViewById(R.id.tambah);
        bersihkan       = view.findViewById(R.id.bersihkan);
        jml_ikat        = view.findViewById(R.id.jml_ikat);
        jml_kertas      = view.findViewById(R.id.jml_kertas);
        jml_butir       = view.findViewById(R.id.jml_btr);
        totalbutir      = view.findViewById(R.id.totalbutir);



        jml_butir.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                InputMethodManager in = (InputMethodManager) getContext(). getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(jml_butir
                                .getApplicationWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                hitung();
                // Must return true here to consume event



                return true;
            }
            return false;
        });

        jml_butir.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hitung();
                return true;
            }
            return false;
        });

        tambah.setOnClickListener(v -> {
            hitung();
        });
        // tombol clear all
        bersihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jml_ikat.setText("");
                jml_kertas.setText("");
                jml_butir.setText("");
                totalbutir.setText("Total : - Butir");
            }
        });

        return  view;
    }

    private void hitung(){



        if((jml_ikat.getText().length()>0 && jml_butir.getText().length()>0 && jml_kertas.getText().length()>0 ))
        {
            double ikat   = Double.parseDouble(jml_ikat.getText().toString());
            double kertas    = Double.parseDouble(jml_kertas.getText().toString());
            double butir  = Double.parseDouble(jml_butir.getText().toString());
            double total = (ikat * 300) + (kertas * 30) + butir;

            DecimalFormat decimalFormat = new DecimalFormat("#");
            String formattotal  = decimalFormat.format(total);

            totalbutir.setText("Total : "+formattotal+ " Butir");

            InputMethodManager imm = (InputMethodManager) getContext(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(jml_butir.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
        else {
            Toast toast = Toast.makeText(getActivity(), "Mohon Masukkan Angka", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
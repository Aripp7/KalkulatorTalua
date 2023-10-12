package com.arip.kalkulatortalua;

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
 * Use the {@link ButirFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButirFragment extends Fragment {
    View view;
    EditText angka_pertama;
    Button tambah, bersihkan;
    TextView ikat, kertas, butir, totalbutir;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ButirFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButirFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButirFragment newInstance(String param1, String param2) {
        ButirFragment fragment = new ButirFragment();
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

    // function utk mengitung()
    private void hitung(){
        if((angka_pertama.getText().length()>0))
        {
            double angka1   = Double.parseDouble(angka_pertama.getText().toString());
            double ikat1    = (int) Math.floor( (angka1 / 300) );
            double kertas1  = (int) Math.floor((angka1 - (ikat1 * 300)) / 30);
            double butir1   = (int) Math.floor( angka1 - ikat1 * 300 - kertas1 * 30);

            DecimalFormat decimalFormat = new DecimalFormat("#");
            String formatikat   = decimalFormat.format(ikat1);
            String formatkertas = decimalFormat.format(kertas1);
            String formatbutir  = decimalFormat.format(butir1);
            String formattotal  = decimalFormat.format(angka1);

            ikat.setText(formatikat + " Ikat");
            kertas.setText(formatkertas + " Kertas");
            butir.setText(formatbutir + " Butir");
            totalbutir.setText("Total Butir : "+formattotal);

            InputMethodManager imm = (InputMethodManager) getContext(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(angka_pertama.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
        else {
            Toast toast = Toast.makeText(getActivity(), "Mohon Masukkan Angka", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_butir, container, false);
        angka_pertama =  view.findViewById(R.id.angka_pertama);

        tambah          = view.findViewById(R.id.tambah);
        bersihkan       = view.findViewById(R.id.bersihkan);
        ikat            = view.findViewById(R.id.hasilikat);
        kertas          = view.findViewById(R.id.hasilkertas);
        butir           = view.findViewById(R.id.hasilbutir);
        totalbutir      = view.findViewById(R.id.totalbutir);


        // Handle "Enter" agar bisa langsung menghitung
        angka_pertama.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                if ((angka_pertama.getText().toString().length() > 0)) {
                    // Perform action on key press
                    InputMethodManager imm = (InputMethodManager) getContext(). getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(angka_pertama.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }

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
                angka_pertama.setText("");
                ikat.setText("0 Ikat");
                kertas.setText("0 Kertas");
                butir.setText("0 Butir");
                totalbutir.setText("Total Butir : 0");
            }
        });
        return view;


    }


}
package com.example.fuelconsumption.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fuelconsumption.MainActivity;
import com.example.fuelconsumption.R;
import com.example.fuelconsumption.TentangActivity;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private EditText kmAwal, kmAkhir, konsumsiBBM;
    private TextView Perbandingan, Hasil;
    private Button cekBBM, btntentang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        kmAwal = (EditText)view.findViewById(R.id.kmawal);
        kmAkhir = (EditText)view.findViewById(R.id.kmakhir);
        konsumsiBBM = (EditText)view.findViewById(R.id.konsumsibbm);
        cekBBM = (Button)view.findViewById(R.id.cekbbm);
        Perbandingan = (TextView)view.findViewById(R.id.perbandingan);
        Hasil = (TextView)view.findViewById(R.id.hasil);
        cekBBM.setOnClickListener(this);
        btntentang = (Button)view.findViewById(R.id.tentang);
        btntentang.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.cekbbm){
            String awal = kmAwal.getText().toString().trim();
            String akhir = kmAkhir.getText().toString().trim();
            String bensin = konsumsiBBM.getText().toString().trim();
            boolean isEmmptyFields = false;
            if (TextUtils.isEmpty(awal)){
                isEmmptyFields = true;
                kmAwal.setError("Field ini Tidak Boleh Kosong");
            }
            if (TextUtils.isEmpty(akhir)){
                isEmmptyFields = true;
                kmAkhir.setError("Field ini Tidak Boleh Kosong");
            }
            if (TextUtils.isEmpty(bensin)){
                isEmmptyFields = true;
                konsumsiBBM.setError("Field ini Tidak Boleh Kosong");
            }
            if (!isEmmptyFields){
                double a = Double.parseDouble(awal);
                double b = Double.parseDouble(akhir);
                double c = Double.parseDouble(bensin);
                double hasil = (b-a)/c;
                Perbandingan.setText(String.valueOf(String.format("%.2f",hasil)+" Km/Ltr"));
                if (hasil>13){
                    Hasil.setText(String.valueOf("Hasil : Irit"));
                }else if (hasil<=13){
                    Hasil.setText(String.valueOf("Hasil : Boros"));
                }
            }
        }
        if (v.getId()==R.id.tentang){
            Intent moveIntent = new Intent(getActivity(), TentangActivity.class);
            startActivity(moveIntent);
        }
    }
}
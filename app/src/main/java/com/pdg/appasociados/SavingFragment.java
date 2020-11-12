package com.pdg.appasociados;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SavingFragment extends Fragment {

    View vista;
    Bundle bundle = new Bundle();
    String tituloAhorro = "null";
    String subtituloAhorro = "null";
    String descripAhorro = "null";
    String CMDAAhorro = "null";
    String CMDCAhorro = "null";
    String R30Ahorro = "null";
    String R365Ahorro = "null";

    TextView titulo;
    TextView subtitulo;
    TextView descrip;
    TextView CDMA;
    TextView CMDC;
    TextView R30;
    TextView R365;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tituloAhorro = getArguments().getString("titulo", "Pos es null");
            descripAhorro = getArguments().getString("descripcion", "Pos es null");
            subtituloAhorro = getArguments().getString("subtitulo", "Pos es null");
            CMDCAhorro = getArguments().getString("CMDC", "Pos es null");
            CMDAAhorro = getArguments().getString("CMDA", "Pos es null");
            R30Ahorro = getArguments().getString("R30", "Pos es null");
            R365Ahorro = getArguments().getString("R365", "Pos es null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ahorro, container, false);
        titulo = vista.findViewById(R.id.txt_ahorroTitlte);
        descrip = vista.findViewById(R.id.text_ahorroDescrip);
        subtitulo = vista.findViewById(R.id.text_ahorroSubT);
        CDMA= vista.findViewById(R.id.text_cmda1);
        CMDC = vista.findViewById(R.id.text_cmdc1);
        R30= vista.findViewById(R.id.text_ahorroR301);
        R365 = vista.findViewById(R.id.text_ahorroR3651);
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) descrip.getLayoutParams();
        ViewGroup.LayoutParams params2 = (ViewGroup.LayoutParams) titulo.getLayoutParams();

        String sub = (String) subtitulo.getText();
        //String nominal = (String) TANM.getText();
       // String efectiva = (String) TAAnual.getText();

        titulo.setText(tituloAhorro);
        descrip.setText(descripAhorro);
        subtitulo.setText(sub+subtituloAhorro);
        CMDC.setText(CMDCAhorro);
        CDMA.setText(CMDAAhorro);

        Log.d("xdxd","antes:"+params2.width);

        if(tituloAhorro.contains("Progra.")){
            Log.d("xdxd","sisa");
            //params2.width = 23 ;
            params.height = 520;
            params2.width = 850;
            titulo.setTextSize(22);
            descrip.setLayoutParams(params);
            titulo.setLayoutParams(params2);
        }

        if(tituloAhorro.contains("Fonavi")){
            Log.d("xdxd","sisa");
            params2.width = 800;
            titulo.setLayoutParams(params2);
            titulo.setTextSize(24);
        }

        R30.setText(R30Ahorro);
        R365.setText(R365Ahorro);

        return vista;

    }
}

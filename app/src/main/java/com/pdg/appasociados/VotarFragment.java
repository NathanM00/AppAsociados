package com.pdg.appasociados;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class VotarFragment extends Fragment {


    ImageView btn_back;
    CardView btn_credit;
    CardView tueliges;
    View vista;

        ViewPager viewPager;
        AdapterVote adapter;
        List<Model> models;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_votar, container, false);

            models = new ArrayList<>();
            models.add(new Model("Creditos nuevos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Nuevos destinos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Clases de pintura", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Salida de pesca", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));

            adapter = new AdapterVote(models, this.getContext());

            viewPager = vista.findViewById(R.id.lyt_info);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(130, 0, 130, 0);

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        return vista;

        }

    }

package com.jcodee.tema03_1.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.tema03_1.R;

import org.w3c.dom.Text;

/**
 * Author: johannfjs
 * Date: 21/5/16
 * Time: 10:12
 */
public class DetalleFragment extends Fragment {
    private Activity activity;
    private SimpleDraweeView sdvImagen;
    private TextView lblDescripcion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle, container, false);

        sdvImagen = (SimpleDraweeView) root.findViewById(R.id.sdvImagen);
        lblDescripcion = (TextView) root.findViewById(R.id.lblDescripcion);

        Bundle bundle = getArguments();
        //posicion
        if (bundle.containsKey("posicion")) {
            
        }

        return root;
    }
}

package com.jcodee.tema03_1.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jcodee.tema03_1.SiguienteActivity;
import com.jcodee.tema03_1.fragments.DetalleFragment;

/**
 * Author: johannfjs
 * Date: 21/5/16
 * Time: 10:31
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DetalleFragment detalleFragment = new DetalleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("posicion", position);
        detalleFragment.setArguments(bundle);
        return detalleFragment;
    }

    @Override
    public int getCount() {
        return SiguienteActivity.lista.size();
    }
}

package com.sharmatechlabs.fruitblastquest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.MobileAds;
import com.sharmatechlabs.fruitblastquest.R;
import com.sharmatechlabs.fruitblastquest.asset.Colors;
import com.sharmatechlabs.fruitblastquest.asset.Fonts;
import com.sharmatechlabs.fruitblastquest.asset.Musics;
import com.sharmatechlabs.fruitblastquest.asset.Preferences;
import com.sharmatechlabs.fruitblastquest.asset.Sounds;
import com.sharmatechlabs.fruitblastquest.asset.Textures;
import com.nativegame.natyengine.ui.GameFragment;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class LoadingFragment extends GameFragment {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public LoadingFragment() {
        // Required empty public constructor
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Load assets
                Textures.load(getGameActivity().getTextureManager(), getContext());
                Sounds.load(getGameActivity().getSoundManager());
                Musics.load(getGameActivity().getMusicManager());
                Fonts.load(getContext());
                Colors.load(getContext());
                Preferences.load(getContext());

                // Load ad
                MobileAds.initialize(getContext());

                // Navigate to menu when loading complete
                getGameActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getGameActivity().navigateToFragment(new MenuFragment());
                    }
                });
            }
        }).start();
    }
    //========================================================

}

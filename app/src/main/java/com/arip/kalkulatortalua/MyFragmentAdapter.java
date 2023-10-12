package com.arip.kalkulatortalua;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyFragmentAdapter(FragmentManager fragmentmanager, Lifecycle lifecycle) {
        super(fragmentmanager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new IkatFragment();
        }

        return new ButirFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

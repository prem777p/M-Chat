package com.mahere.m_chat.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mahere.m_chat.Fragment.Calls;
import com.mahere.m_chat.Fragment.Chats;
import com.mahere.m_chat.Fragment.Groups;
import com.mahere.m_chat.Fragment.Status;

public class FragmentSwitchAdapter extends FragmentStateAdapter {

    public FragmentSwitchAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new Chats();

            case 1: return new Groups();

            case 2: return new Status();

            case 3: return new Calls();

            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

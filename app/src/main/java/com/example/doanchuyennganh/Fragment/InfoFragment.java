package com.example.doanchuyennganh.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanchuyennganh.Adapter.FoodAdapter;
import com.example.doanchuyennganh.Login.DangNhap;
import com.example.doanchuyennganh.Model.CurrentUser;
import com.example.doanchuyennganh.Model.User;
import com.example.doanchuyennganh.R;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    TextView txtPhone, txtName;
    Button btnLogout;
    View mView;
    FoodAdapter foodAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // layout thành view
        mView = inflater.inflate(R.layout.fragment_info, container, false);

        txtPhone = mView.findViewById(R.id.txtSdt);
        txtName = mView.findViewById(R.id.txtName);
        btnLogout = mView.findViewById(R.id.btnLogout);
        //set Name với Phone cho user từ biến
        txtName.setText(CurrentUser.currentUser.getName());
        txtPhone.setText(CurrentUser.currentUser.getPhone());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
                txtName.setText(null);
                txtPhone.setText(null);
                //tạo cờ để tránh user quay lại được tab trước
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
            }
        });
        return mView;
    }
}
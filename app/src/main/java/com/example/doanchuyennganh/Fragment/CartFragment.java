package com.example.doanchuyennganh.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanchuyennganh.Activity.HomeActivity;
import com.example.doanchuyennganh.Adapter.CartAdapter;
import com.example.doanchuyennganh.Adapter.FoodAdapter;
import com.example.doanchuyennganh.Database.Database;
import com.example.doanchuyennganh.Model.CurrentUser;
import com.example.doanchuyennganh.Model.Order;
import com.example.doanchuyennganh.Model.Request;
import com.example.doanchuyennganh.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
    View view;
    TextView txtTotalPrice;
    Button btnPay;
    RecyclerView rcvCart;
    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference requests = database.getReference("Request"); //Request
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //button buy


        //init
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        rcvCart = view.findViewById(R.id.rcv_food_cart);

        rcvCart.setHasFixedSize(true);
        rcvCart.setLayoutManager(new LinearLayoutManager(getActivity()));

        txtTotalPrice = view.findViewById(R.id.txt_total_price);
        btnPay = view.findViewById(R.id.btn_payment);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        loadListFood();
        return view;
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("One more step!");
        alertDialog.setTitle("Nhập số phòng!");

        final EditText edtAddress = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.icon_cart);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newRequestKey = String.valueOf(System.currentTimeMillis());
                Request request = new Request(
                        CurrentUser.currentUser.getName(),
                        CurrentUser.currentUser.getPhone(),
                        edtAddress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart
                );
                request.setKey(newRequestKey);
                requests.child(newRequestKey).setValue(request);
                new Database(getContext()).cleanCart();
                Intent intent = new Intent(getContext(), HomeActivity.class);
                //note
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getContext(),"Thank you ordered",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadListFood() {
        //cart = new ArrayList<>();
        cart = new Database(getContext()).getCarts();
        adapter = new CartAdapter(cart, getContext());
        rcvCart.setAdapter(adapter);
    }
}
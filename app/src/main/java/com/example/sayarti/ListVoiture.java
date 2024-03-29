package com.example.sayarti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListVoiture extends Fragment {
    RecyclerView recyclerView;

    public static final String URL_PRODUCTS = "http://dev.goodlinks.tn/sayarti-apps/test.php";


    //a list to store all the products
    List<Product> productList;
    ArrayList<String> ProdcarNames = new ArrayList();
    private final String sear;
    //ArrayList<String> selectedcar = new ArrayList();
    ArrayList<String> selectedcar = new ArrayList<>();
    //private final String selc;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ProgressDialog progressDoalog;
    public ListVoiture(String sear) {
        // Required empty public constructor
        this.sear = sear;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_voiture, container, false);
        //Toast.makeText(getContext(),sear.toString(),Toast.LENGTH_LONG).show();
        productList = new ArrayList<>();
        //getting the recyclerview from xml
        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        if(sear.equals(""))
        {
            hidkeyboard();
            progressDialog(10);
            loadProducts();
        }
        else
        {
            hidkeyboard();
            productList.clear();
            progressDialog(30);
            postProducts();
        }

        this.configureOnClickRecyclerView();

        return v;
    }
    private void hidkeyboard()
    {
        //get view to hide keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view == null) {
            view = new View(getActivity());
        }
        // hide the keyboard
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void progressDialog(int val)
    {
        Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDoalog.incrementProgressBy(val);
            }
        };

        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setCancelable(false);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Chargement en cours . . . . ");
        progressDoalog.setTitle(R.string.loading);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();
        new Thread(() -> {
            try {
                while (progressDoalog.getProgress() <= progressDoalog
                        .getMax()) {
                    Thread.sleep(200);
                    handle.sendMessage(handle.obtainMessage());
                    if (progressDoalog.getProgress() == progressDoalog
                            .getMax()) {
                        progressDoalog.dismiss();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }



    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_list_voiture)
            .setOnItemClickListener((recyclerView, position, v) -> {

    Intent intent = new Intent(Intent.ACTION_VIEW);
    String url = "https://www.sayarti.tn/prix-des-voitures/" + productList.get(position).getLien();
                                    intent.setData(Uri.parse(url));
    getActivity().startActivity(intent);

            });
    }

    private void postProducts()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                response -> {
                    //Toast.makeText(MainActivity.this,response.trim(),Toast.LENGTH_LONG).show();

                    try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            input.setText(jsonObject.getString("data"));
                        JSONArray P_array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < P_array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = P_array.getJSONObject(i);

                            //adding the product to product list
                            productList.add(new Product(
                                    product.getString("post_title").toUpperCase(),
                                    product.getString("sale_price").toUpperCase(),
                                    product.getString("type_de_carburant").toUpperCase(),
                                    product.getString("engine").toUpperCase(),
                                    product.getString("puissance_ch_din").toUpperCase(),
                                    product.getString("boite").toUpperCase(),
                                    product.getString("path"),
                                    product.getString("trans").toUpperCase(),
                                    product.getString("lien")

                            ));
                            //ProdcarNames.add(product.getString("post_title"));
                        }
                        //creating adapter object and setting it to recyclerview
                        ProductAdapter adapter = new ProductAdapter(getContext(), productList,ProdcarNames);
                        recyclerView.setAdapter(adapter);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        //erreur.setText(e.getMessage());
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("data",sear);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                response -> {

                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject(i);
                            //adding the product to product list
                            productList.add(new Product(
                                    product.getString("post_title").toUpperCase(),
                                    product.getString("sale_price").toUpperCase(),
                                    product.getString("type_de_carburant").toUpperCase(),
                                    product.getString("engine").toUpperCase(),
                                    product.getString("puissance_ch_din").toUpperCase(),
                                    product.getString("boite").toUpperCase(),
                                    product.getString("path"),
                                    product.getString("trans").toUpperCase(),
                                    product.getString("lien")
                            ));
                            //ProdcarNames.add(product.getString("post_title"));
                        }
                        //creating adapter object and setting it to recyclerview
                        ProductAdapter adapter = new ProductAdapter(getContext(), productList,ProdcarNames);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //erreur.setText(e.getMessage());
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show());

        //adding our stringrequest to queue
        Volley.newRequestQueue(requireContext()).add(stringRequest);
        //progress.dismiss();

    }
}
package fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;
import com.snappmi.covid19_ng.AboutActivity;
import com.snappmi.covid19_ng.CallActivity;
import com.snappmi.covid19_ng.LearnSkillActivity;
import com.snappmi.covid19_ng.R;
import com.snappmi.covid19_ng.ShopActivity;
import com.snappmi.covid19_ng.WatchMovieActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import adapters.TabsAdapter;

public class HomeFragment extends Fragment {

    private CountryCodePicker ccp;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout shop_icon, learn_icon, movies_icon, call_icon;

    String  new_url,ad_url,ae_url,af_url,al_url,am_url,ao_url,ar_url,at_url,au_url,az_url,bb_url,bd_url,be_url,bg_url,bh_url,
            bi_url,bj_url,br_url,bs_url,bw_url,by_url,cg_url,ci_url,cl_url,cm_url,cn_url,co_url,cr_url,cu_url,cy_url,cz_url,
            de_url,dj_url,dk_url,do_url,dz_url,ec_url,eg_url,es_url,et_url,fi_url,fj_url,fr_url,ga_url,gb_url,ge_url,gh_url,
            gm_url,gn_url,gr_url,gt_url,hk_url,hn_url,ht_url,hu_url,id_url,ie_url,il_url,in_url,iq_url,ir_url,is_url,it_url,
            je_url,jm_url,jo_url,jp_url,ke_url,kr_url,kw_url,kz_url,lb_url,lt_url,lu_url,lv_url,ly_url,ma_url,mc_url,mg_url,
            ml_url,mw_url,mx_url,my_url,mz_url,na_url,ne_url,ng_url,nl_url,no_url,np_url,nz_url,om_url,pa_url,pe_url,ph_url,
            pk_url,ps_url,pt_url,py_url,qa_url,ro_url,rs_url,ru_url,rw_url,sa_url,sd_url,se_url,sg_url,si_url,sk_url,sl_url,
            sn_url,so_url,ss_url,sv_url,sy_url,sz_url,td_url,tg_url,th_url,tr_url,tt_url,tw_url,tz_url,ua_url,ug_url,us_url,
            uy_url,vn_url,ye_url,za_url,zm_url,zw_url;

    private TextView confirmed_cases, recovered_cases, death_cases, today_confirmed_cases, today_critical_cases, today_death_cases;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FirebaseFirestore db;
    private Button about_button;

    private String TAG = "Tag";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ccp = root.findViewById(R.id.ccp);
        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.viewPager);
        shop_icon = root.findViewById(R.id.shopIcon);
        learn_icon = root.findViewById(R.id.learnIcon);
        movies_icon = root.findViewById(R.id.moviesIcon);
        call_icon = root.findViewById(R.id.callIcon);
        progressBar = root.findViewById(R.id.progressBar);
        confirmed_cases = root.findViewById(R.id.confirmedCasesNumber);
        recovered_cases = root.findViewById(R.id.recoveredCasesNumber);
        death_cases = root.findViewById(R.id.deathCasesNumber);
        today_confirmed_cases = root.findViewById(R.id.todayConfirmedNumber);
        today_critical_cases = root.findViewById(R.id.todayCriticalNumber);
        today_death_cases = root.findViewById(R.id.todayDeathNumber);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        db = FirebaseFirestore.getInstance();
        about_button = root.findViewById(R.id.aboutButton);


        final TabsAdapter tabsAdapter = new TabsAdapter(getContext(), getChildFragmentManager(),
                tabLayout.getTabCount());

        //tabs code to switch between tabs
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //set click listener to icons
        shop_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShopActivity.class);
                startActivity(intent);
            }
        });

        learn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LearnSkillActivity.class);
                startActivity(intent);
            }
        });

        movies_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WatchMovieActivity.class);
                startActivity(intent);
            }
        });

        call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CallActivity.class);
                startActivity(intent);
            }
        });

        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        if(!checkNetwork(getContext())){
            Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.TOP, 0, 140);
            confirmed_cases.setText("N/A");
            recovered_cases.setText("N/A");
            death_cases.setText("N/A");
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //do something here
                if(checkNetwork(getContext())){
                    getSelectedCountryForSwipe();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
                    toast.show();
                    toast.setGravity(Gravity.TOP, 0, 140);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        setData();

        getSelectedCountry();

        return root;
    }



    private void getSelectedCountry() {

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                String scx = ccp.getSelectedCountryNameCode();
                switch (scx) {
                    case "AD":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ad_url;
                        break;

                    case "AE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ae_url;
                        break;

                    case "AF":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = af_url;
                        break;

                    case "AL":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = al_url;
                        break;

                    case "AM":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = am_url;
                        break;

                    case "AO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ao_url;
                        break;

                    case "AR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ar_url;
                        break;

                    case "AT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = at_url;
                        break;

                    case "AU":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = au_url;
                        break;

                    case "AZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = az_url;
                        break;

                    case "BD":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bd_url;
                        break;

                    case "BE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = be_url;
                        break;

                    case "BG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bg_url;
                        break;

                    case "BH":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bh_url;
                        break;

                    case "BI":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bi_url;
                        break;

                    case "BJ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bj_url;
                        break;

                    case "BS":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bs_url;
                        break;

                    case "BW":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = bw_url;
                        break;

                    case "BY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = by_url;
                        break;

                    case "CG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cg_url;
                        break;

                    case "CI":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ci_url;
                        break;

                    case "CL":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cl_url;
                        break;

                    case "CM":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cm_url;
                        break;

                    case "CN":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cn_url;
                        break;

                    case "CO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = co_url;
                        break;

                    case "CR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cr_url;
                        break;

                    case "CU":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cu_url;
                        break;

                    case "CY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cy_url;
                        break;

                    case "CZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = cz_url;
                        break;

                    case "DE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = de_url;
                        break;

                    case "DJ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = dj_url;
                        break;

                    case "DK":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = dk_url;
                        break;

                    case "DO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = do_url;
                        break;

                    case "DZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = dz_url;
                        break;

                    case "EC":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ec_url;
                        break;

                    case "EG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = eg_url;
                        break;

                    case "ES":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = es_url;
                        break;

                    case "ET":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = et_url;
                        break;

                    case "FI":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = fi_url;
                        break;

                    case "FJ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = fj_url;
                        break;

                    case "FR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = fr_url;
                        break;

                    case "GA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ga_url;
                        break;

                    case "GB":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = gb_url;
                        break;

                    case "GE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ge_url;
                        break;

                    case "GH":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = gh_url;
                        break;

                    case "GR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = gr_url;
                        break;

                    case "GT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = gt_url;
                        break;

                    case "HK":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = hk_url;
                        break;

                    case "HN":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = hn_url;
                        break;

                    case "HT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ht_url;
                        break;

                    case "HU":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = hu_url;
                        break;

                    case "ID":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = id_url;
                        break;

                    case "IE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ie_url;
                        break;

                    case "IL":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = il_url;
                        break;

                    case "IN":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = in_url;
                        break;

                    case "IQ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = iq_url;
                        break;

                    case "IR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ir_url;
                        break;

                    case "IS":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = is_url;
                        break;

                    case "IT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = it_url;
                        break;

                    case "JE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = je_url;
                        break;

                    case "JM":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = jm_url;
                        break;

                    case "JO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = jo_url;
                        break;

                    case "JP":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = jp_url;
                        break;

                    case "KE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ke_url;
                        break;

                    case "KR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = kr_url;
                        break;

                    case "KW":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = kw_url;
                        break;

                    case "KZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = kz_url;
                        break;

                    case "LB":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = lb_url;
                        break;

                    case "LT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = lt_url;
                        break;

                    case "LU":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = lu_url;
                        break;

                    case "LY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ly_url;
                        break;

                    case "MA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ma_url;
                        break;

                    case "MC":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = mc_url;
                        break;

                    case "MG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = mg_url;
                        break;

                    case "ML":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ml_url;
                        break;

                    case "MY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = my_url;
                        break;

                    case "MX":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = mx_url;
                        break;

                    case "NA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = na_url;
                        break;

                    case "NE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ne_url;
                        break;

                    case "NG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ng_url;
                        break;

                    case "NL":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = nl_url;
                        break;

                    case "NO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = no_url;
                        break;

                    case "NP":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = np_url;
                        break;

                    case "NZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = nz_url;
                        break;

                    case "OM":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = om_url;
                        break;

                    case "PA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = pa_url;
                        break;

                    case "PE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = pe_url;
                        break;

                    case "PH":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ph_url;
                        break;

                    case "PK":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = pk_url;
                        break;

                    case "PS":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ps_url;
                        break;

                    case "PT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = pt_url;
                        break;

                    case "PY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = py_url;
                        break;

                    case "QA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = qa_url;
                        break;

                    case "RO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ro_url;
                        break;

                    case "RS":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = rs_url;
                        break;

                    case "RU":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ru_url;
                        break;

                    case "RW":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = rw_url;
                        break;

                    case "SA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sa_url;
                        break;

                    case "SD":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sd_url;
                        break;

                    case "SE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = se_url;
                        break;

                    case "SG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sg_url;
                        break;

                    case "SI":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = si_url;
                        break;

                    case "SK":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sk_url;
                        break;

                    case "SL":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sl_url;
                        break;

                    case "SN":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sn_url;
                        break;

                    case "SO":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = so_url;
                        break;

                    case "SS":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ss_url;
                        break;

                    case "SV":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sv_url;
                        break;

                    case "SY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sy_url;
                        break;

                    case "SZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = sz_url;
                        break;

                    case "TD":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = td_url;
                        break;

                    case "TG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = tg_url;
                        break;

                    case "TH":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = th_url;
                        break;

                    case "TR":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = tr_url;
                        break;

                    case "TT":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = tt_url;
                        break;

                    case "TW":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = tw_url;
                        break;

                    case "TZ":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = tz_url;
                        break;

                    case "UA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ua_url;
                        break;

                    case "UG":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ug_url;
                        break;

                    case "US":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = us_url;
                        break;

                    case "UY":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = uy_url;
                        break;

                    case "VN":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = vn_url;
                        break;

                    case "YE":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ye_url;
                        break;

                    case "ZA":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = za_url;
                        break;

                    case "ZM":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = zm_url;
                        break;

                    case "ZW":
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = zw_url;
                        break;

                    default:
                        progressBar.setVisibility(View.VISIBLE);
                        new_url = ng_url;
                        break;
                }

                Log.e("Tag", "getcountry: " +new_url);
                loadData();

            }
        });

    }


    private void setData(){

        DocumentReference docRef = db.collection("endpoint").document("country_url");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ad_url = document.getString("ad");
                        ae_url = document.getString("ae");
                        af_url = document.getString("af");
                        al_url = document.getString("al");
                        am_url = document.getString("am");
                        ao_url = document.getString("ao");
                        ar_url = document.getString("ar");
                        at_url = document.getString("at");
                        au_url = document.getString("au");
                        az_url = document.getString("az");
                        bb_url = document.getString("bb");
                        bd_url = document.getString("bd");
                        be_url = document.getString("be");
                        bg_url = document.getString("bg");
                        bh_url = document.getString("bh");
                        bi_url = document.getString("bi");
                        bj_url = document.getString("bj");
                        br_url = document.getString("br");
                        bs_url = document.getString("bs");
                        bw_url = document.getString("bw");
                        by_url = document.getString("by");
                        cg_url = document.getString("cg");
                        ci_url = document.getString("ci");
                        cl_url = document.getString("cl");
                        cm_url = document.getString("cm");
                        cn_url = document.getString("cn");
                        co_url = document.getString("co");
                        cr_url = document.getString("cr");
                        cu_url = document.getString("cu");
                        cy_url = document.getString("cy");
                        cz_url = document.getString("cz");
                        de_url = document.getString("de");
                        dj_url = document.getString("dj");
                        dk_url = document.getString("dk");
                        do_url = document.getString("do");
                        dz_url = document.getString("dz");
                        ec_url = document.getString("ec");
                        eg_url = document.getString("eg");
                        es_url = document.getString("es");
                        et_url = document.getString("et");
                        fi_url = document.getString("fi");
                        fj_url = document.getString("fj");
                        fr_url = document.getString("fr");
                        ga_url = document.getString("ga");
                        gb_url = document.getString("gb");
                        ge_url = document.getString("ge");
                        gh_url = document.getString("gh");
                        gm_url = document.getString("gm");
                        gn_url = document.getString("gn");
                        gr_url = document.getString("gr");
                        gt_url = document.getString("gt");
                        hk_url = document.getString("hk");
                        hn_url = document.getString("hn");
                        ht_url = document.getString("ht");
                        hu_url = document.getString("hu");
                        id_url = document.getString("id");
                        ie_url = document.getString("ie");
                        il_url = document.getString("il");
                        in_url = document.getString("in");
                        iq_url = document.getString("iq");
                        ir_url = document.getString("ir");
                        is_url = document.getString("is");
                        it_url = document.getString("it");
                        je_url = document.getString("je");
                        jm_url = document.getString("jm");
                        jo_url = document.getString("jo");
                        jp_url = document.getString("jp");
                        ke_url = document.getString("ke");
                        kr_url = document.getString("kr");
                        kw_url = document.getString("kw");
                        kz_url = document.getString("kz");
                        lb_url = document.getString("lb");
                        lt_url = document.getString("lt");
                        lu_url = document.getString("lu");
                        lv_url = document.getString("lv");
                        ly_url = document.getString("ly");
                        ma_url = document.getString("ma");
                        mc_url = document.getString("mc");
                        mg_url = document.getString("mg");
                        ml_url = document.getString("ml");
                        mw_url = document.getString("mw");
                        mx_url = document.getString("mx");
                        my_url = document.getString("my");
                        mz_url = document.getString("mz");
                        na_url = document.getString("na");
                        ne_url = document.getString("ne");
                        ng_url = document.getString("ng");
                        nl_url = document.getString("nl");
                        no_url = document.getString("no");
                        np_url = document.getString("np");
                        nz_url = document.getString("nz");
                        om_url = document.getString("om");
                        pa_url = document.getString("pa");
                        pe_url = document.getString("pe");
                        ph_url = document.getString("ph");
                        pk_url = document.getString("pk");
                        ps_url = document.getString("ps");
                        pt_url = document.getString("pt");
                        py_url = document.getString("py");
                        qa_url = document.getString("qa");
                        ro_url = document.getString("ro");
                        rs_url = document.getString("rs");
                        ru_url = document.getString("ru");
                        rw_url = document.getString("rw");
                        sa_url = document.getString("sa");
                        sd_url = document.getString("sd");
                        se_url = document.getString("se");
                        sg_url = document.getString("sg");
                        si_url = document.getString("si");
                        sk_url = document.getString("sk");
                        sl_url = document.getString("sl");
                        sn_url = document.getString("sn");
                        so_url = document.getString("so");
                        ss_url = document.getString("ss");
                        sv_url = document.getString("sv");
                        sy_url = document.getString("sy");
                        sz_url = document.getString("sz");
                        td_url = document.getString("td");
                        tg_url = document.getString("tg");
                        th_url = document.getString("th");
                        tr_url = document.getString("tr");
                        tt_url = document.getString("tt");
                        tw_url = document.getString("tw");
                        tz_url = document.getString("tz");
                        ua_url = document.getString("ua");
                        ug_url = document.getString("ug");
                        us_url = document.getString("us");
                        uy_url = document.getString("uy");
                        vn_url = document.getString("vn");
                        ye_url = document.getString("ye");
                        za_url = document.getString("za");
                        zm_url = document.getString("zm");
                        zw_url = document.getString("zw");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getString("link_url"));
                        Log.e(TAG, "DocumentSnapshot data: " + ng_url);
                        if(checkNetwork(getContext())){
                            getSelectedCountryForSwipe();
                        }
                        else{
                            Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
                            toast.show();
                            toast.setGravity(Gravity.TOP, 0, 140);
                            confirmed_cases.setText("N/A");
                            recovered_cases.setText("N/A");
                            death_cases.setText("N/A");
                        }
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                //do something here
                                if(checkNetwork(getContext())){
                                    getSelectedCountryForSwipe();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                                else{
                                    Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
                                    toast.show();
                                    toast.setGravity(Gravity.TOP, 0, 140);
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        });
                    } else {
                        Log.d(TAG, "No such document");
                        Log.e("Tag", "failed");
                    }
                }
                else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    public boolean checkNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean netInfo = cm.isActiveNetworkMetered();

        if (netInfo) {
            return true;
        }
        return false;
    }


    public void getSelectedCountryForSwipe() {

        String scx = ccp.getSelectedCountryNameCode();
        switch (scx) {
            case "AD":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ad_url;
                break;

            case "AE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ae_url;
                break;

            case "AF":
                progressBar.setVisibility(View.VISIBLE);
                new_url = af_url;
                break;

            case "AL":
                progressBar.setVisibility(View.VISIBLE);
                new_url = al_url;
                break;

            case "AM":
                progressBar.setVisibility(View.VISIBLE);
                new_url = am_url;
                break;

            case "AO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ao_url;
                break;

            case "AR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ar_url;
                break;

            case "AT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = at_url;
                break;

            case "AU":
                progressBar.setVisibility(View.VISIBLE);
                new_url = au_url;
                break;

            case "AZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = az_url;
                break;

            case "BD":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bd_url;
                break;

            case "BE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = be_url;
                break;

            case "BG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bg_url;
                break;

            case "BH":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bh_url;
                break;

            case "BI":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bi_url;
                break;

            case "BJ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bj_url;
                break;

            case "BS":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bs_url;
                break;

            case "BW":
                progressBar.setVisibility(View.VISIBLE);
                new_url = bw_url;
                break;

            case "BY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = by_url;
                break;

            case "CG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cg_url;
                break;

            case "CI":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ci_url;
                break;

            case "CL":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cl_url;
                break;

            case "CM":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cm_url;
                break;

            case "CN":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cn_url;
                break;

            case "CO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = co_url;
                break;

            case "CR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cr_url;
                break;

            case "CU":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cu_url;
                break;

            case "CY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cy_url;
                break;

            case "CZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = cz_url;
                break;

            case "DE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = de_url;
                break;

            case "DJ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = dj_url;
                break;

            case "DK":
                progressBar.setVisibility(View.VISIBLE);
                new_url = dk_url;
                break;

            case "DO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = do_url;
                break;

            case "DZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = dz_url;
                break;

            case "EC":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ec_url;
                break;

            case "EG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = eg_url;
                break;

            case "ES":
                progressBar.setVisibility(View.VISIBLE);
                new_url = es_url;
                break;

            case "ET":
                progressBar.setVisibility(View.VISIBLE);
                new_url = et_url;
                break;

            case "FI":
                progressBar.setVisibility(View.VISIBLE);
                new_url = fi_url;
                break;

            case "FJ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = fj_url;
                break;

            case "FR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = fr_url;
                break;

            case "GA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ga_url;
                break;

            case "GB":
                progressBar.setVisibility(View.VISIBLE);
                new_url = gb_url;
                break;

            case "GE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ge_url;
                break;

            case "GH":
                progressBar.setVisibility(View.VISIBLE);
                new_url = gh_url;
                break;

            case "GR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = gr_url;
                break;

            case "GT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = gt_url;
                break;

            case "HK":
                progressBar.setVisibility(View.VISIBLE);
                new_url = hk_url;
                break;

            case "HN":
                progressBar.setVisibility(View.VISIBLE);
                new_url = hn_url;
                break;

            case "HT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ht_url;
                break;

            case "HU":
                progressBar.setVisibility(View.VISIBLE);
                new_url = hu_url;
                break;

            case "ID":
                progressBar.setVisibility(View.VISIBLE);
                new_url = id_url;
                break;

            case "IE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ie_url;
                break;

            case "IL":
                progressBar.setVisibility(View.VISIBLE);
                new_url = il_url;
                break;

            case "IN":
                progressBar.setVisibility(View.VISIBLE);
                new_url = in_url;
                break;

            case "IQ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = iq_url;
                break;

            case "IR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ir_url;
                break;

            case "IS":
                progressBar.setVisibility(View.VISIBLE);
                new_url = is_url;
                break;

            case "IT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = it_url;
                break;

            case "JE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = je_url;
                break;

            case "JM":
                progressBar.setVisibility(View.VISIBLE);
                new_url = jm_url;
                break;

            case "JO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = jo_url;
                break;

            case "JP":
                progressBar.setVisibility(View.VISIBLE);
                new_url = jp_url;
                break;

            case "KE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ke_url;
                break;

            case "KR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = kr_url;
                break;

            case "KW":
                progressBar.setVisibility(View.VISIBLE);
                new_url = kw_url;
                break;

            case "KZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = kz_url;
                break;

            case "LB":
                progressBar.setVisibility(View.VISIBLE);
                new_url = lb_url;
                break;

            case "LT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = lt_url;
                break;

            case "LU":
                progressBar.setVisibility(View.VISIBLE);
                new_url = lu_url;
                break;

            case "LY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ly_url;
                break;

            case "MA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ma_url;
                break;

            case "MC":
                progressBar.setVisibility(View.VISIBLE);
                new_url = mc_url;
                break;

            case "MG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = mg_url;
                break;

            case "ML":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ml_url;
                break;

            case "MY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = my_url;
                break;

            case "MX":
                progressBar.setVisibility(View.VISIBLE);
                new_url = mx_url;
                break;

            case "NA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = na_url;
                break;

            case "NE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ne_url;
                break;

            case "NG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ng_url;
                break;

            case "NL":
                progressBar.setVisibility(View.VISIBLE);
                new_url = nl_url;
                break;

            case "NO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = no_url;
                break;

            case "NP":
                progressBar.setVisibility(View.VISIBLE);
                new_url = np_url;
                break;

            case "NZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = nz_url;
                break;

            case "OM":
                progressBar.setVisibility(View.VISIBLE);
                new_url = om_url;
                break;

            case "PA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = pa_url;
                break;

            case "PE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = pe_url;
                break;

            case "PH":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ph_url;
                break;

            case "PK":
                progressBar.setVisibility(View.VISIBLE);
                new_url = pk_url;
                break;

            case "PS":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ps_url;
                break;

            case "PT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = pt_url;
                break;

            case "PY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = py_url;
                break;

            case "QA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = qa_url;
                break;

            case "RO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ro_url;
                break;

            case "RS":
                progressBar.setVisibility(View.VISIBLE);
                new_url = rs_url;
                break;

            case "RU":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ru_url;
                break;

            case "RW":
                progressBar.setVisibility(View.VISIBLE);
                new_url = rw_url;
                break;

            case "SA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sa_url;
                break;

            case "SD":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sd_url;
                break;

            case "SE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = se_url;
                break;

            case "SG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sg_url;
                break;

            case "SI":
                progressBar.setVisibility(View.VISIBLE);
                new_url = si_url;
                break;

            case "SK":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sk_url;
                break;

            case "SL":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sl_url;
                break;

            case "SN":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sn_url;
                break;

            case "SO":
                progressBar.setVisibility(View.VISIBLE);
                new_url = so_url;
                break;

            case "SS":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ss_url;
                break;

            case "SV":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sv_url;
                break;

            case "SY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sy_url;
                break;

            case "SZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = sz_url;
                break;

            case "TD":
                progressBar.setVisibility(View.VISIBLE);
                new_url = td_url;
                break;

            case "TG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = tg_url;
                break;

            case "TH":
                progressBar.setVisibility(View.VISIBLE);
                new_url = th_url;
                break;

            case "TR":
                progressBar.setVisibility(View.VISIBLE);
                new_url = tr_url;
                break;

            case "TT":
                progressBar.setVisibility(View.VISIBLE);
                new_url = tt_url;
                break;

            case "TW":
                progressBar.setVisibility(View.VISIBLE);
                new_url = tw_url;
                break;

            case "TZ":
                progressBar.setVisibility(View.VISIBLE);
                new_url = tz_url;
                break;

            case "UA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ua_url;
                break;

            case "UG":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ug_url;
                break;

            case "US":
                progressBar.setVisibility(View.VISIBLE);
                new_url = us_url;
                break;

            case "UY":
                progressBar.setVisibility(View.VISIBLE);
                new_url = uy_url;
                break;

            case "VN":
                progressBar.setVisibility(View.VISIBLE);
                new_url = vn_url;
                break;

            case "YE":
                progressBar.setVisibility(View.VISIBLE);
                new_url = ye_url;
                break;

            case "ZA":
                progressBar.setVisibility(View.VISIBLE);
                new_url = za_url;
                break;

            case "ZM":
                progressBar.setVisibility(View.VISIBLE);
                new_url = zm_url;
                break;

            case "ZW":
                progressBar.setVisibility(View.VISIBLE);
                new_url = zw_url;
                break;

            default:
                progressBar.setVisibility(View.VISIBLE);
                new_url = ng_url;
                break;
        }

        loadData();
    }

    public void loadData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, new_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    String cc = jsonObject.getString("cases");
                    String rc = jsonObject.getString("recovered");
                    String dc = jsonObject.getString("deaths");
                    String tc = jsonObject.getString("todayCases");
                    String tcc = jsonObject.getString("critical");
                    String td = jsonObject.getString("todayDeaths");

                    int pcc = Integer.parseInt(cc);
                    int prc = Integer.parseInt(rc);
                    int pdc = Integer.parseInt(dc);
                    int ptc = Integer.parseInt(tc);
                    int ptcc = Integer.parseInt(tcc);
                    int ptd = Integer.parseInt(td);

                    String confirmed_cases_formatted = String.format(Locale.US,"%,d", pcc);
                    String recovered_cases_formatted = String.format(Locale.US,"%,d", prc);
                    String death_cases_formatted = String.format(Locale.US,"%,d", pdc);
                    String today_cases_formatted = String.format(Locale.US,"%,d", ptc);
                    String today_cases_critical_formatted = String.format(Locale.US,"%,d", ptcc);
                    String today_deaths_formatted = String.format(Locale.US,"%,d", ptd);

                    confirmed_cases.setText(confirmed_cases_formatted);
                    recovered_cases.setText(recovered_cases_formatted);
                    death_cases.setText(death_cases_formatted);
                    today_confirmed_cases.setText(today_cases_formatted);
                    today_critical_cases.setText(today_cases_critical_formatted);
                    today_death_cases.setText(today_deaths_formatted);
                } catch (JSONException e) {
                    Log.e("INFOOO", response);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }

}

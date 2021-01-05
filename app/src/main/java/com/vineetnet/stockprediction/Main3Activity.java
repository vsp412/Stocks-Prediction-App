package com.vineetnet.stockprediction;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Main3Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button bt;
    private Button bt2;
    private ImageView img;
    private  TextView tvx;

    String algo,tick,ptype;
    int daysx;
//    LineGraphSeries<DataPoint> series;
    GraphView gvx;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String cu = extras.getString("userz");
            Toast.makeText(this, cu, Toast.LENGTH_LONG).show();
            FirebaseUser user = mAuth.getCurrentUser();
            String ema = user.getEmail();
            Toast.makeText(this, ema, Toast.LENGTH_SHORT).show();
            //The key argument here must match that used in the other activity

            final Spinner spinner1 = findViewById(R.id.s1);
            ArrayList<String> arrayList1 = new ArrayList<>();
            String[] jvx = {"AACG", "AAL", "AAME", "AAOI", "AAON", "AAPL", "AAWW", "AAXJ", "AAXN", "ABCB", "ABEO", "ABIO", "ABMD", "ABTX", "ABUS", "ACAD", "ACAM", "ACAMU", "ACAMW", "ACBI", "ACCP", "ACER", "ACGL", "ACGLO", "ACGLP", "ACHC", "ACHV", "ACIA", "ACIU", "ACIW", "ACLS", "ACMR", "ACNB", "ACOR", "ACRS", "ACRX", "ACST", "ACT", "ACTG", "ACTT", "ACTTU", "ACTTW", "ACWI", "ACWX", "ADAP", "ADBE", "ADES", "ADI", "ADIL", "ADILW", "ADMA", "ADMP", "ADMS", "ADP", "ADPT", "ADRE", "ADRO", "ADSK", "ADTN", "ADUS", "ADVM", "ADXN", "ADXS", "AEGN", "AEHR", "AEIS", "AEMD", "AERI", "AESE", "AEY", "AEYE", "AEZS", "AFH", "AFIN", "AFINP", "AFMD", "AFYA", "AGBA", "AGBAR", "AGBAU", "AGBAW", "AGEN", "AGFS", "AGFSW", "AGIO", "AGLE", "AGMH", "AGNC", "AGNCM", "AGNCN", "AGNCO", "AGNCP", "AGND", "AGRX", "AGTC", "AGYS", "AGZD", "AHCO", "AHI", "AHPI", "AIA", "AIH", "AIHS", "AIKI", "AIMC", "AIMT", "AINV", "AIQ", "AIRG", "AIRR", "AIRT", "AIRTP", "AIRTW", "AKAM", "AKBA", "AKCA", "AKER", "AKRO", "AKRX", "AKTS", "AKTX", "ALAC", "ALACR", "ALACU", "ALACW", "ALBO", "ALCO", "ALDX", "ALEC", "ALGN", "ALGR", "ALGRR", "ALGRU", "ALGRW", "ALGT", "ALIM", "ALIT", "ALJJ", "ALKS", "ALLK", "ALLO", "ALLT", "ALNA", "ALNY", "ALOT", "ALPN", "ALRM", "ALRN", "ALRS", "ALSK", "ALT", "ALTM", "ALTR", "ALTY", "ALXN", "ALYA", "AMAG", "AMAL", "AMAT", "AMBA", "AMBC", "AMCA", "AMCI", "AMCIU", "AMCIW", "AMCX", "AMD", "AMED", "AMEH", "AMGN", "AMHC", "AMHCU", "AMHCW", "AMKR", "AMNB", "AMOT", "AMOV", "AMPH", "AMRB", "AMRH", "AMRHW", "AMRK", "AMRN", "AMRS", "AMSC", "AMSF", "AMSWA", "AMTB", "AMTBB", "AMTD", "AMTX", "AMWD", "AMZN", "ANAB", "ANAT", "ANCN", "ANDA", "ANDAR", "ANDAU", "ANDAW", "ANDE", "ANGI", "ANGL", "ANGO", "ANIK", "ANIP", "ANIX", "ANPC", "ANSS", "ANTE", "ANY", "AOBC", "AOSL", "APDN", "APEI", "APEN", "APEX", "APLS", "APLT", "APM", "APOG", "APOP", "APOPW", "APPF", "APPN", "APPS", "APRE", "APTO", "APTX", "APVO", "APWC", "APXT", "APXTU", "APXTW", "APYX", "AQB", "AQMS", "AQST", "ARAV", "ARAY", "ARCB", "ARCC", "ARCE", "ARCT", "ARDS", "ARDX", "AREC", "ARGX", "ARKR", "ARLP", "ARNA", "AROW", "ARPO", "ARQT", "ARTL", "ARTLW", "ARTNA", "ARTW", "ARVN", "ARWR", "ARYA", "ARYAU", "ARYAW", "ASET", "ASFI", "ASLN", "ASMB", "ASML", "ASNA", "ASND", "ASPS", "ASPU", "ASRT", "ASRV", "ASRVP", "ASTC", "ASTE", "ASUR", "ASYS", "ATAX", "ATCX", "ATCXW", "ATEC", "ATEX", "ATHE", "ATHX", "ATIF", "ATLC", "ATLO", "ATNI", "ATNX", "ATOM", "ATOS", "ATRA", "ATRC", "ATRI", "ATRO", "ATRS", "ATSG", "ATVI", "ATXI", "AUB", "AUBN", "AUDC", "AUPH", "AUTL", "AUTO", "AVAV", "AVCO", "AVDL", "AVEO", "AVGO", "AVGOP", "AVGR", "AVID", "AVNW", "AVRO", "AVT", "AVXL", "AWRE", "AXAS", "AXDX", "AXGN", "AXGT", "AXLA", "AXNX", "AXSM", "AXTI", "AY", "AYTU", "AZPN", "AZRX", "BAND", "BANF", "BANFP", "BANR", "BANX", "BASI", "BATRA", "BATRK", "BBBY", "BBC", "BBCP", "BBGI", "BBH", "BBI", "BBIO", "BBP", "BBQ", "BBRX", "BBSI", "BCBP", "BCDA", "BCDAW", "BCEL", "BCLI", "BCML", "BCOM", "BCOR", "BCOV", "BCOW", "BCPC", "BCRX", "BCTF", "BCTX", "BCYC", "BDGE", "BDSI", "BDTX", "BEAM", "BEAT", "BECN", "BELFA", "BELFB", "BFC", "BFIN", "BFIT", "BFRA", "BFST", "BFYT", "BGCP", "BGFV", "BGNE", "BGRN", "BHAT", "BHF", "BHFAL", "BHFAP", "BHTG", "BIB", "BICK", "BIDU", "BIIB", "BILI", "BIMI", "BIOC", "BIOL", "BIS", "BIVI", "BJK", "BJRI", "BKCC", "BKEP", "BKEPP", "BKNG", "BKSC", "BKYI", "BL", "BLBD", "BLCM", "BLCN", "BLDP", "BLDR", "BLFS", "BLIN          ", "BLKB", "BLMN", "BLNK", "BLNKW", "BLPH", "BLRX", "BLU", "BLUE", "BMCH", "BMLP", "BMRA", "BMRC", "BMRN", "BMTC", "BND", "BNDW", "BNDX", "BNFT", "BNGO", "BNGOW", "BNSO", "BNTC", "BNTCW", "BNTX", "BOCH", "BOKF", "BOKFL", "BOMN", "BOOM", "BOSC", "BOTJ", "BOTZ", "BOXL", "BPFH", "BPMC", "BPOP", "BPOPM", "BPOPN", "BPRN", "BPTH", "BPY", "BPYPN", "BPYPO", "BPYPP", "BPYU", "BPYUP", "BREW", "BRID", "BRKL", "BRKR", "BRKS", "BROG", "BROGW", "BRP", "BRPA", "BRPAR", "BRPAU", "BRPAW", "BRQS", "BRY", "BSAE", "BSBE", "BSBK", "BSCE", "BSCK", "BSCL", "BSCM", "BSCN", "BSCO", "BSCP", "BSCQ", "BSCR", "BSCS", "BSCT", "BSDE", "BSET", "BSGM", "BSJK", "BSJL", "BSJM", "BSJN", "BSJO", "BSJP", "BSJQ", "BSJR", "BSML", "BSMM", "BSMN", "BSMO", "BSMP", "BSMQ", "BSMR", "BSMS", "BSMT", "BSQR", "BSRR", "BSTC", "BSVN", "BTAI", "BTEC", "BUG", "BUSE", "BVSN", "BVXV", "BVXVW", "BWAY", "BWB", "BWEN", "BWFG", "BWMX", "BXRX", "BYFC", "BYND", "BYSI", "BZUN", "CAAS", "CABA", "CAC", "CACC", "CACG", "CAKE", "CALA", "CALB", "CALM", "CAMP", "CAMT", "CAN", "CAPR", "CAR", "CARA", "CARE", "CARG", "CARO", "CARV", "CARZ", "CASA", "CASH", "CASI", "CASS", "CASY", "CATB", "CATC", "CATH", "CATM", "CATS", "CATY", "CBAN", "CBAT", "CBAY", "CBFV", "CBIO", "CBLI", "CBMB", "CBMG", "CBNK", "CBOE", "CBPO", "CBRL", "CBSH", "CBSHP", "CBTX", "CCAP", "CCB", "CCBG", "CCCL", "CCD", "CCLP", "CCMP", "CCNE", "CCOI", "CCRC", "CCRN", "CCXI", "CDC", "CDEV", "CDK", "CDL", "CDLX", "CDMO", "CDMOP", "CDNA", "CDNS", "CDOR", "CDTX", "CDW", "CDXC", "CDXS", "CDZI", "CECE", "CELC", "CELH", "CEMI", "CENT", "CENTA", "CENX", "CERC", "CERN", "CERS", "CETV", "CETX", "CETXP", "CETXW", "CEVA", "CEY", "CEZ", "CFA", "CFB", "CFBI", "CFBK", "CFFA", "CFFAU", "CFFAW", "CFFI", "CFFN", "CFMS", "CFO", "CFRX", "CG", "CGBD", "CGEN", "CGIX", "CGNX", "CGO", "CHCI", "CHCO", "CHDN", "CHEF", "CHEK", "CHEKZ", "CHFS", "CHI", "CHIC", "CHKP", "CHMA", "CHMG", "CHNA", "CHNG", "CHNGU", "CHNR", "CHPM", "CHPMU", "CHPMW", "CHRS", "CHRW", "CHSCL", "CHSCM", "CHSCN", "CHSCO", "CHSCP", "CHTR", "CHUY", "CHW", "CHY", "CIBR", "CID", "CIDM", "CIFS", "CIGI", "CIH", "CIIC", "CIICU", "CIICW", "CIL", "CINF", "CIVB", "CIZ", "CIZN", "CJJD", "CKPT", "CLAR", "CLBK", "CLBS", "CLCT", "CLDB", "CLDX", "CLFD", "CLGN", "CLIR", "CLLS", "CLMT", "CLNE", "CLOU", "CLPS", "CLPT", "CLRB", "CLRBZ", "CLRG", "CLRO", "CLSD", "CLSK", "CLSN", "CLUB", "CLVS", "CLWT", "CLXT", "CMBM", "CMCO", "CMCSA", "CMCT", "CMCTP", "CME", "CMFNL", "CMLS", "CMPR", "CMRX", "CMTL", "CNAT", "CNBKA", "CNCE", "CNCR", "CNDT", "CNET", "CNFR", "CNFRL", "CNMD", "CNNB", "CNOB", "CNSL", "CNSP", "CNST", "CNTG", "CNTX", "CNTY", "CNXN", "COCP", "CODA", "CODX", "COFS", "COHR", "COHU", "COKE", "COLB", "COLL", "COLM", "COMM", "COMT", "CONE", "CONN", "COOP", "CORE", "CORT", "CORV", "COST", "COUP", "COWN", "COWNL", "COWNZ", "CPAA", "CPAAU", "CPAAW", "CPAH", "CPHC", "CPIX", "CPLP", "CPRT", "CPRX", "CPSH", "CPSI", "CPSS", "CPST", "CPTA", "CPTAG", "CPTAL", "CPTI", "CPZ", "CRAI", "CRBP", "CREE", "CREG", "CRESY", "CREX", "CREXW", "CRIS", "CRMT", "CRNC", "CRNT", "CRNX", "CRON", "CROX", "CRSA", "CRSAU", "CRSAW", "CRSP", "CRTO", "CRTX", "CRUS", "CRVL", "CRVS", "CRWD", "CRWS", "CSA", "CSB", "CSBR", "CSCO", "CSF", "CSFL", "CSGP", "CSGS", "CSII", "CSIQ", "CSML", "CSOD", "CSPI", "CSQ", "CSSE", "CSSEP", "CSTE", "CSTL", "CSTR", "CSWC", "CSWCL", "CSWI", "CSX", "CTAS", "CTBI", "CTG", "CTHR", "CTIB", "CTIC", "CTMX", "CTRC", "CTRE", "CTRM", "CTRN", "CTSH", "CTSO", "CTXR", "CTXRW", "CTXS", "CUBA", "CUE", "CUI", "CUTR", "CVBF", "CVCO", "CVCY", "CVET", "CVGI", "CVGW", "CVLT", "CVLY", "CVTI", "CVV", "CWBC", "CWBR", "CWCO", "CWST", "CXDC", "CXSE", "CY", "CYAD", "CYAN", "CYBE", "CYBR", "CYCC", "CYCCP", "CYCN", "CYOU", "CYRN", "CYRX", "CYRXW", "CYTK", "CZNC", "CZR", "CZWI", "DAIO", "DAKT", "DALI", "DARE", "DAX", "DBVT", "DBX", "DCAR", "DCOM", "DCOMP", "DCPH", "DDIV", "DDOG", "DEAC", "DEACU", "DEACW", "DENN", "DFFN", "DFNL", "DFPHU", "DFVL", "DFVS", "DGICA", "DGICB", "DGII", "DGLD", "DGLY", "DGRE", "DGRS", "DGRW", "DHC", "DHCNI", "DHCNL", "DHIL", "DINT", "DIOD", "DISCA", "DISCB", "DISCK", "DISH", "DJCO", "DLHC", "DLPN", "DLPNW", "DLTH", "DLTR", "DMAC", "DMLP", "DMPI", "DMRC", "DMTK", "DNJR", "DNKN", "DNLI", "DOCU", "DOGZ", "DOMO", "DOOO", "DORM", "DOX", "DOYU", "DPHC", "DPHCU", "DPHCW", "DRAD", "DRADP", "DRIO", "DRIOW", "DRIV", "DRMT", "DRNA", "DRRX", "DRTT", "DSGX", "DSKE", "DSKEW", "DSLV", "DSPG", "DSWL", "DTEA", "DTIL", "DTSS", "DTUL", "DTUS", "DTYL", "DUO", "DUOT", "DUSA", "DVAX", "DVLU", "DVOL", "DVY", "DWAS", "DWAT", "DWAW", "DWCR", "DWEQ", "DWFI", "DWLD", "DWMC", "DWPP", "DWSH", "DWSN", "DWUS", "DXCM", "DXGE", "DXJS", "DXLG", "DXPE", "DXYN", "DYAI", "DYNT", "DZSI", "EA", "EARS", "EAST", "EBAY", "EBAYL", "EBIX", "EBIZ", "EBMT", "EBSB", "EBTC", "ECHO", "ECOL", "ECOLW", "ECOR", "ECOW", "ECPG", "EDAP", "EDIT", "EDNT", "EDRY", "EDSA", "EDUC", "EEFT", "EEMA", "EFAS", "EFOI", "EFSC", "EGAN", "EGBN", "EGHT", "EGLE", "EGOV", "EGRX", "EH", "EHR", "EHTH", "EIDX", "EIGI", "EIGR", "EKSO", "ELGX", "ELOX", "ELSE", "ELTK", "EMB", "EMCB", "EMCF", "EMCG", "EMIF", "EMKR", "EML", "EMMS", "EMXC", "ENDP", "ENG", "ENLV", "ENOB", "ENPH", "ENSG", "ENT", "ENTA", "ENTG", "ENTX", "ENTXW", "ENZL", "EOLS", "EPAY", "EPIX", "EPSN", "EPZM", "EQ", "EQBK", "EQIX", "EQRR", "ERI", "ERIC", "ERIE", "ERII", "ERYP", "ESBK", "ESCA", "ESEA", "ESG", "ESGD", "ESGE", "ESGG", "ESGR", "ESGRO", "ESGRP", "ESGU", "ESLT", "ESPO", "ESPR", "ESQ", "ESSA", "ESSC", "ESSCR", "ESSCU", "ESSCW", "ESTA", "ESXB", "ETFC", "ETNB", "ETON", "ETSY", "ETTX", "EUFN", "EVBG", "EVER", "EVFM", "EVGBC", "EVGN", "EVK", "EVLMC", "EVLO", "EVOK", "EVOL", "EVOP", "EVSI", "EVSIW", "EVSTC", "EWBC", "EWJE", "EWJV", "EWZS", "EXAS", "EXC", "EXEL", "EXFO", "EXLS", "EXPC", "EXPCU", "EXPCW", "EXPD", "EXPE", "EXPI", "EXPO", "EXTR", "EYE", "EYEG", "EYEGW", "EYEN", "EYES", "EYESW", "EYPT", "EZPW", "FAAR", "FAB", "FAD", "FALN", "FAMI", "FANG", "FANH", "FARM", "FARO", "FAST", "FAT", "FATE", "FB", "FBIO", "FBIOP", "FBIZ", "FBMS", "FBNC", "FBSS", "FBZ", "FCA", "FCAL", "FCAN", "FCAP", "FCBC", "FCBP", "FCCO", "FCCY", "FCEF", "FCEL", "FCFS", "FCNCA", "FCNCP", "FCVT", "FDBC", "FDEF", "FDIV", "FDNI", "FDT", "FDTS", "FDUS", "FDUSG", "FDUSL", "FDUSZ", "FEIM", "FELE", "FEM", "FEMB", "FEMS", "FENC", "FEP", "FEUZ", "FEX", "FEYE", "FFBC", "FFBW", "FFHL", "FFIC", "FFIN", "FFIV", "FFNW", "FFWM", "FGBI", "FGEN", "FGM", "FHB", "FHK", "FIBK", "FID", "FINX", "FISI", "FISV", "FITB", "FITBI", "FITBO", "FITBP", "FIVE", "FIVN", "FIXD", "FIXX", "FIZZ", "FJP", "FKO", "FKU", "FLAG", "FLAT", "FLDM", "FLEX", "FLGT", "FLIC", "FLIR", "FLL", "FLMN", "FLMNW", "FLN", "FLNT", "FLRZ", "FLUX", "FLWR", "FLWS", "FLXN", "FLXS", "FMAO", "FMAX", "FMB", "FMBH", "FMBI", "FMCI", "FMCIU", "FMCIW", "FMHI", "FMK", "FMNB", "FNCB", "FNHC", "FNJN", "FNK", "FNKO", "FNLC", "FNWB", "FNX", "FNY", "FOCS", "FOLD", "FONR", "FORD", "FORK", "FORM", "FORR", "FORTY", "FOSL", "FOX", "FOXA", "FOXF", "FPA", "FPAY", "FPRX", "FPXE", "FPXI", "FRAF", "FRAN", "FRBA", "FRBK", "FREQ", "FRG", "FRGI", "FRHC", "FRME", "FRPH", "FRPT", "FRSX", "FRTA", "FSBC", "FSBW", "FSCT", "FSEA", "FSFG", "FSLR", "FSRV", "FSRVU", "FSRVW", "FSTR", "FSV", "FSZ", "FTA", "FTAC", "FTACU", "FTACW", "FTAG", "FTC", "FTCS", "FTDR", "FTEK", "FTFT", "FTGC", "FTHI", "FTLB", "FTNT", "FTR", "FTRI", "FTSL", "FTSM", "FTXD", "FTXG", "FTXH", "FTXL", "FTXN", "FTXO", "FTXR", "FULC", "FULT", "FUNC", "FUND", "FUSB", "FUTU", "FUV", "FV", "FVC", "FVCB", "FVE", "FWONA", "FWONK", "FWP", "FWRD", "FXNC", "FYC", "FYT", "FYX", "GABC", "GAIA", "GAIN", "GAINL", "GAINM", "GALT", "GARS", "GASS", "GBCI", "GBDC", "GBLI", "GBLIL", "GBLIZ", "GBLK", "GBT", "GCBC", "GDEN", "GDS", "GDYN", "GDYNW", "GEC", "GECC", "GECCL", "GECCM", "GECCN", "GENC", "GENE", "GENY", "GEOS", "GERN", "GEVO", "GFED", "GFN", "GFNCP", "GFNSL", "GGAL", "GH", "GHIV", "GHIVU", "GHIVW", "GHSI", "GIFI", "GIGE", "GIGM", "GIII", "GILD", "GILT", "GLAD", "GLADD", "GLADL", "GLBS", "GLBZ", "GLDD", "GLDI", "GLG", "GLIBA", "GLIBP", "GLMD", "GLNG", "GLPG", "GLPI", "GLRE", "GLUU", "GLYC", "GMAB", "GMBL", "GMBLW", "GMDA", "GMHI", "GMHIU", "GMHIW", "GMLP", "GMLPP", "GNCA", "GNFT", "GNLN", "GNMA", "GNMK", "GNOM", "GNPX", "GNRSU", "GNSS", "GNTX", "GNTY", "GNUS", "GO", "GOGL", "GOGO", "GOLD", "GOOD", "GOODM", "GOODN", "GOOG", "GOOGL", "GOSS", "GPAQ", "GPAQU", "GPAQW", "GPOR", "GPP", "GPRE", "GPRO", "GRBK", "GRFS", "GRID", "GRIF", "GRIL", "GRIN", "GRMN", "GRNQ", "GRNV", "GRNVR", "GRNVU", "GRNVW", "GROW", "GRPN", "GRTS", "GRTX", "GRVY", "GRWG", "GSBC", "GSHD", "GSIT", "GSKY", "GSM", "GSMG", "GSMGW", "GSUM", "GT", "GTEC", "GTHX", "GTIM", "GTLS", "GTYH", "GULF", "GURE", "GVP", "GWGH", "GWPH", "GWRS", "GXGX", "GXGXU", "GXGXW", "GXTG", "GYRO", "HA", "HAFC", "HAIN", "HALL", "HALO", "HAPP", "HARP", "HAS", "HAYN", "HBAN", "HBANN", "HBANO", "HBCP", "HBIO", "HBMD", "HBNC", "HBP", "HBT", "HCAC", "HCACU", "HCACW", "HCAP", "HCAPZ", "HCAT", "HCCH", "HCCHR", "HCCHU", "HCCHW", "HCCI", "HCCO", "HCCOU", "HCCOW", "HCKT", "HCM", "HCSG", "HDS", "HDSN", "HEAR", "HEBT", "HEES", "HELE", "HEPA", "HERD", "HERO", "HEWG", "HFBL", "HFFG", "HFWA", "HGSH", "HHHH", "HHHHR", "HHHHU", "HHHHW", "HHR", "HHT", "HIBB", "HIFS", "HIHO", "HIMX", "HJLI", "HJLIW", "HLAL", "HLG", "HLIO", "HLIT", "HLNE", "HMHC", "HMNF", "HMST", "HMSY", "HMTV", "HNDL", "HNNA", "HNRG", "HOFT", "HOLI", "HOLX", "HOMB", "HONE", "HOOK", "HOPE", "HOTH", "HOVNP", "HQI", "HQY", "HROW", "HRTX", "HRZN", "HSDT", "HSIC", "HSII", "HSKA", "HSON", "HSTM", "HTBI", "HTBK", "HTBX", "HTGM", "HTHT", "HTIA", "HTLD", "HTLF", "HUBG", "HUGE", "HUIZ", "HURC", "HURN", "HVBC", "HWBK", "HWC", "HWCC", "HWCPL", "HWKN", "HX", "HYAC", "HYACU", "HYACW", "HYLS", "HYND", "HYRE", "HYXE", "HYZD", "HZNP", "IAC", "IART", "IBB", "IBCP", "IBEX", "IBKC", "IBKCN", "IBKCO", "IBKCP", "IBKR", "IBOC", "IBTA", "IBTB", "IBTD", "IBTE", "IBTF", "IBTG", "IBTH", "IBTI", "IBTJ", "IBTX", "IBUY", "ICAD", "ICBK", "ICCC", "ICCH", "ICFI", "ICHR", "ICLK", "ICLN", "ICLR", "ICMB", "ICON", "ICPT", "ICUI", "IDCC", "IDEX", "IDLB", "IDN", "IDRA", "IDXG", "IDXX", "IDYA", "IEA", "IEAWW", "IEC", "IEF", "IEI", "IEP", "IESC", "IEUS", "IFEU", "IFGL", "IFMK", "IFRX", "IFV", "IGF", "IGIB", "IGIC", "IGICW", "IGLE", "IGMS", "IGOV", "IGSB", "IHRT", "III", "IIIN", "IIIV", "IIN", "IIVI", "IJT", "IKNX", "ILMN", "ILPT", "IMAB", "IMAC", "IMACW", "IMBI", "IMGN", "IMKTA", "IMMP", "IMMR", "IMMU", "IMOS", "IMRA", "IMRN", "IMRNW", "IMTE", "IMUX", "IMV", "IMVT", "IMVTU", "IMVTW", "IMXI", "INBK", "INBKL", "INBKZ", "INCY", "INDB", "INDY", "INFI", "INFN", "INFO", "INFR", "INGN", "INMB", "INMD", "INNT", "INO", "INOD", "INOV", "INPX", "INSE", "INSG", "INSM", "INSU", "INSUU", "INSUW", "INTC", "INTG", "INTL", "INTU", "INVA", "INVE", "INWK", "IONS", "IOSP", "IOTS", "IOVA", "IPAR", "IPDN", "IPGP", "IPHA", "IPKW", "IPLDP", "IPWR", "IQ", "IRBT", "IRCP", "IRDM", "IRIX", "IRMD", "IROQ", "IRTC", "IRWD", "ISBC", "ISDS", "ISDX", "ISEE", "ISEM", "ISHG", "ISIG", "ISNS", "ISRG", "ISSC", "ISTB", "ISTR", "ITCI", "ITEQ", "ITI", "ITIC", "ITMR", "ITRI", "ITRM", "ITRN", "IUS", "IUSB", "IUSG", "IUSS", "IUSV", "IVAC", "IXUS", "IZEA", "JACK", "JAGX", "JAKK", "JAN", "JAZZ", "JBHT", "JBLU", "JBSS", "JCOM", "JCS", "JCTCF", "JD", "JFIN", "JFK", "JFKKR", "JFKKU", "JFKKW", "JFU", "JG", "JJSF", "JKHY", "JKI", "JMPNL", "JMPNZ", "JNCE", "JOBS", "JOUT", "JRJC", "JRSH", "JRVR", "JSM", "JSMD", "JSML", "JVA", "JYNT", "KALA", "KALU", "KALV", "KBAL", "KBLM", "KBLMR", "KBLMU", "KBLMW", "KBSF", "KBWB", "KBWD", "KBWP", "KBWR", "KBWY", "KCAPL", "KE", "KELYA", "KELYB", "KEQU", "KERN", "KERNW", "KFFB", "KFRC", "KGJI", "KHC", "KIDS", "KIN", "KINS", "KIRK", "KLAC", "KLDO", "KLIC", "KLXE", "KMDA", "KMPH", "KNDI", "KNSA", "KNSL", "KOD", "KOPN", "KOSS", "KPTI", "KRKR", "KRMA", "KRMD", "KRNT", "KRNY", "KROS", "KRTX", "KRUS", "KRYS", "KTCC", "KTOS", "KTOV", "KTOVW", "KURA", "KVHI", "KWEB", "KXIN", "KZIA", "KZR", "LACQ", "LACQU", "LACQW", "LAKE", "LAMR", "LANC", "LAND", "LANDP", "LARK", "LASR", "LATN", "LATNU", "LATNW", "LAUR", "LAWS", "LAZY", "LBAI", "LBC", "LBRDA", "LBRDK", "LBTYA", "LBTYB", "LBTYK", "LCA", "LCAHU", "LCAHW", "LCNB", "LCUT", "LDEM", "LDSF", "LE", "LECO", "LEDS", "LEGH", "LEGR", "LEVL", "LFAC", "LFACU", "LFACW", "LFUS", "LFVN", "LGIH", "LGND", "LHCG", "LIFE", "LILA", "LILAK", "LINC", "LIND", "LIQT", "LITE", "LIVE", "LIVK", "LIVKU", "LIVKW", "LIVN", "LIVX", "LIZI", "LJPC", "LK", "LKCO", "LKFN", "LKOR", "LKQ", "LLEX", "LLIT", "LLNW", "LMAT", "LMB", "LMBS", "LMFA", "LMFAW", "LMNL", "LMNR", "LMNX", "LMPX", "LMRK", "LMRKN", "LMRKO", "LMRKP", "LMST", "LNDC", "LNGR", "LNT", "LNTH", "LOAC", "LOACR", "LOACU", "LOACW", "LOAN", "LOB", "LOCO", "LOGC", "LOGI", "LOGM", "LONE", "LOOP", "LOPE", "LORL", "LOVE", "LPCN", "LPLA", "LPSN", "LPTH", "LPTX", "LQDA", "LQDT", "LRCX", "LRGE", "LSACU", "LSBK", "LSCC", "LSTR", "LSXMA", "LSXMB", "LSXMK", "LTBR", "LTRPA", "LTRPB", "LTRX", "LULU", "LUMO", "LUNA", "LUNG", "LVGO", "LVHD", "LWAY", "LX", "LXRX", "LYFT", "LYL", "LYTS", "MACK", "MAGS", "MANH", "MANT", "MAR", "MARA", "MARK", "MARPS", "MASI", "MAT", "MATW", "MAYS", "MBB", "MBCN", "MBII", "MBIN", "MBINO", "MBINP", "MBIO", "MBNKP", "MBOT", "MBRX", "MBSD", "MBUU", "MBWM", "MCBC", "MCBS", "MCEF", "MCEP", "MCFT", "MCHI", "MCHP", "MCHX", "MCMJ", "MCMJW", "MCRB", "MCRI", "MDB", "MDCA", "MDGL", "MDGS", "MDGSW", "MDIA", "MDIV", "MDJH", "MDLZ", "MDRR", "MDRRP", "MDRX", "MDWD", "MEDP", "MEDS", "MEET", "MEIP", "MELI", "MEOH", "MERC", "MESA", "MESO", "METC", "METX", "MFH", "MFIN", "MFINL", "MFNC", "MFSF", "MGEE", "MGEN", "MGI", "MGIC", "MGLN", "MGNX", "MGPI", "MGRC", "MGTA", "MGTX", "MGYR", "MHLD", "MICT", "MIDD", "MIK", "MILN", "MIME", "MIND", "MINDP", "MINI", "MIRM", "MIST", "MITK", "MITO", "MJCO", "MKD", "MKGI", "MKSI", "MKTX", "MLAB", "MLCO", "MLHR", "MLND", "MLNX", "MLVF", "MMAC", "MMLP", "MMSI", "MMYT", "MNCL", "MNCLU", "MNCLW", "MNDO", "MNKD", "MNLO", "MNOV", "MNPR", "MNRO", "MNSB", "MNST", "MNTA", "MNTX", "MOBL", "MOFG", "MOGO", "MOHO", "MOMO", "MOR", "MORF", "MORN", "MOSY", "MOTA", "MOTS", "MOXC", "MPAA", "MPB", "MPWR", "MRAM", "MRBK", "MRCC", "MRCCL", "MRCY", "MREO", "MRIN", "MRKR", "MRLN", "MRNA", "MRNS", "MRSN", "MRTN", "MRTX", "MRUS", "MRVL", "MSBF", "MSBI", "MSEX", "MSFT", "MSG", "MSON", "MSTR", "MSVB", "MTBC", "MTBCP", "MTC", "MTCH", "MTEM", "MTEX", "MTLS", "MTP", "MTRX", "MTSC", "MTSI", "MTSL", "MU", "MUDS", "MUDSU", "MUDSW", "MVBF", "MVIS", "MWK", "MXIM", "MYFW", "MYGN", "MYL", "MYOK", "MYOS", "MYRG", "MYSZ", "MYT", "NAII", "NAKD", "NAOV", "NATH", "NATI", "NATR", "NAVI", "NBACU", "NBEV", "NBIX", "NBL", "NBLX", "NBN", "NBRV", "NBSE", "NBTB", "NCBS", "NCLH", "NCMI", "NCNA", "NCSM", "NCTY", "NDAQ", "NDLS", "NDRA", "NDRAW", "NDSN", "NEBU", "NEBUU", "NEBUW", "NEO", "NEOG", "NEON", "NEOS", "NEPH", "NEPT", "NERV", "NESR", "NESRW", "NETE", "NEWA", "NEWT", "NEWTI", "NEWTL", "NEXT", "NFBK", "NFE", "NFIN", "NFINU", "NFINW", "NFLX", "NFTY", "NGHC", "NGHCN", "NGHCO", "NGHCP", "NGHCZ", "NGM", "NH", "NHLD", "NHLDW", "NHTC", "NICE", "NICK", "NIU", "NK", "NKSH", "NKTR", "NLOK", "NLTX", "NMCI", "NMIH", "NMRD", "NMRK", "NNBR", "NNDM", "NODK", "NOVN", "NOVT", "NPA", "NPAUU", "NPAWW", "NRBO", "NRC", "NRIM", "NSEC", "NSIT", "NSSC", "NSTG", "NSYS", "NTAP", "NTCT", "NTEC", "NTES", "NTGN", "NTGR", "NTIC", "NTLA", "NTNX", "NTRA", "NTRP", "NTRS", "NTRSO", "NTUS", "NTWK", "NUAN", "NURO", "NUROW", "NUVA", "NVAX", "NVCN", "NVCR", "NVDA", "NVEC", "NVEE", "NVFY", "NVIV", "NVMI", "NVUS", "NWBI", "NWFL", "NWGI", "NWL", "NWLI", "NWPX", "NWS", "NWSA", "NXGN", "NXMD", "NXPI", "NXST", "NXTC", "NXTD", "NXTG", "NYMT", "NYMTM", "NYMTN", "NYMTO", "NYMTP", "NYMX", "OAS", "OBAS", "OBCI", "OBLN", "OBNK", "OBSV", "OCC", "OCCI", "OCCIP", "OCFC", "OCGN", "OCSI", "OCSL", "OCUL", "ODFL", "ODP", "ODT", "OESX", "OFED", "OFIX", "OFLX", "OFS", "OFSSI", "OFSSL", "OFSSZ", "OGI", "OIIM", "OKTA", "OLD", "OLED", "OLLI", "OMAB", "OMCL", "OMER", "OMEX", "OMP", "ON", "ONB", "ONCS", "ONCT", "ONCY", "ONEM", "ONEQ", "ONEW", "ONTX", "ONTXW", "ONVO", "OPB", "OPBK", "OPCH", "OPES", "OPESU", "OPESW", "OPGN", "OPGNW", "OPHC", "OPI", "OPINI", "OPK", "OPNT", "OPOF", "OPRA", "OPRT", "OPRX", "OPTN", "OPTT", "ORBC", "ORGO", "ORGS", "ORLY", "ORMP", "ORRF", "ORSN", "ORSNR", "ORSNU", "ORSNW", "ORTX", "OSBC", "OSIS", "OSMT", "OSN", "OSPN", "OSS", "OSTK", "OSUR", "OSW", "OTEL", "OTEX", "OTG", "OTIC", "OTLK", "OTLKW", "OTTR", "OTTW", "OVBC", "OVID", "OVLY", "OXBR", "OXBRW", "OXFD", "OXLC", "OXLCM", "OXLCO", "OXLCP", "OXSQ", "OXSQL", "OXSQZ", "OYST", "OZK", "PAAC", "PAACR", "PAACU", "PAACW", "PAAS", "PACB", "PACQ", "PACQU", "PACQW", "PACW", "PAE", "PAEWW", "PAHC", "PANL", "PASG", "PATI", "PATK", "PAVM", "PAVMW", "PAVMZ", "PAYS", "PAYX", "PBBI", "PBCT", "PBCTP", "PBFS", "PBHC", "PBIO", "PBIP", "PBPB", "PBTS", "PBYI", "PCAR", "PCB", "PCH", "PCIM", "PCOM", "PCRX", "PCSB", "PCTI", "PCTY", "PCYG", "PCYO", "PDBC", "PDCE", "PDCO", "PDD", "PDEV", "PDEX", "PDFS", "PDLB", "PDLI", "PDP", "PDSB", "PEBK", "PEBO", "PECK", "PEER", "PEGA", "PEIX", "PENN", "PEP", "PERI", "PESI", "PETQ", "PETS", "PETZ", "PEY", "PEZ", "PFBC", "PFBI", "PFF", "PFG", "PFHD", "PFI", "PFIE", "PFIN", "PFIS", "PFLT", "PFM", "PFMT", "PFPT", "PFSW", "PGC", "PGEN", "PGJ", "PGNX", "PGNY", "PGTI", "PHAS", "PHAT", "PHCF", "PHIO", "PHIOW", "PHO", "PHUN", "PHUNW", "PI", "PICO", "PID", "PIE", "PIH", "PIHPP", "PINC", "PIO", "PIRS", "PIXY", "PIZ", "PKBK", "PKOH", "PKW", "PLAB", "PLAY", "PLBC", "PLC", "PLCE", "PLIN", "PLL", "PLMR", "PLPC", "PLSE", "PLUG", "PLUS", "PLW", "PLXP", "PLXS", "PLYA", "PMBC", "PMD", "PME", "PMOM", "PNBK", "PNFP", "PNNT", "PNNTG", "PNQI", "PNRG", "PNTG", "POAI", "PODD", "POLA", "POLY", "POOL", "POPE", "POTX", "POWI", "POWL", "PPBI", "PPC", "PPD", "PPH", "PPHI", "PPIH", "PPSI", "PRAA", "PRAH", "PRCP", "PRDO", "PRFT", "PRFZ", "PRGS", "PRGX", "PRIM", "PRMW", "PRN", "PRNB", "PROF", "PROV", "PRPH", "PRPL", "PRPO", "PRQR", "PRSC", "PRTA", "PRTH", "PRTK", "PRTS", "PRVB", "PRVL", "PS", "PSC", "PSCC", "PSCD", "PSCE", "PSCF", "PSCH", "PSCI", "PSCM", "PSCT", "PSCU", "PSEC", "PSET", "PSHG", "PSL", "PSM", "PSMT", "PSNL", "PSTI", "PSTV", "PSTVZ", "PT", "PTAC", "PTACU", "PTACW", "PTC", "PTCT", "PTE", "PTEN", "PTF", "PTGX", "PTH", "PTI", "PTLA", "PTMN", "PTNR", "PTON", "PTSI", "PTVCA", "PTVCB", "PUB", "PUI", "PULM", "PUYI", "PVAC", "PVAL", "PVBC", "PWFL", "PWOD", "PXI", "PXLW", "PXS", "PY", "PYPL", "PYZ", "PZZA", "QABA", "QADA", "QADB", "QAT", "QBAK", "QCLN", "QCOM", "QCRH", "QDEL", "QFIN", "QGEN", "QIWI", "QK", "QLC", "QLYS", "QMCO", "QNST", "QQEW", "QQQ", "QQQX", "QQXT", "QRHC", "QRTEA", "QRTEB", "QRVO", "QTEC", "QTNT", "QTRX", "QTT", "QUIK", "QUMU", "QURE", "QYLD", "RADA", "RAIL", "RAND", "RAPT", "RARE", "RAVE", "RAVN", "RBB", "RBBN", "RBCAA", "RBCN", "RBKB", "RBNC", "RBZ", "RCEL", "RCII", "RCKT", "RCKY", "RCM", "RCMT", "RCON", "RDCM", "RDFN", "RDHL", "RDI", "RDIB", "RDNT", "RDUS", "RDVT", "RDVY", "RDWR", "REAL", "REDU", "REED", "REFR", "REG", "REGI", "REGN", "REKR", "RELL", "RELV", "REPH", "REPL", "RESN", "RETA", "RETO", "REXN", "REYN", "RFAP", "RFDI", "RFEM", "RFEU", "RFIL", "RGCO", "RGEN", "RGLD", "RGLS", "RGNX", "RGP", "RIBT", "RICK", "RIGL", "RILY", "RILYG", "RILYH", "RILYI", "RILYM", "RILYN", "RILYO", "RILYP", "RILYZ", "RING", "RIOT", "RIVE", "RKDA", "RLMD", "RMBI", "RMBL", "RMBS", "RMCF", "RMNI", "RMR", "RMTI", "RNDB", "RNDM", "RNDV", "RNEM", "RNET", "RNLC", "RNMC", "RNSC", "RNST", "RNWK", "ROAD", "ROBO", "ROBT", "ROCK", "ROIC", "ROKU", "ROLL", "ROSE", "ROSEU", "ROSEW", "ROST", "RP", "RPAY", "RPD", "RRBI", "RRD", "RRGB", "RRR", "RSSS", "RTH", "RTIX", "RTLR", "RTRX", "RTTR", "RUBY", "RUHN", "RUN", "RUSHA", "RUSHB", "RUTH", "RVMD", "RVNC", "RVSB", "RWLK", "RYAAY", "RYTM", "SABR", "SAEX", "SAFM", "SAFT", "SAGE", "SAIA", "SAL", "SALM", "SAMA", "SAMAU", "SAMAW", "SAMG", "SANM", "SANW", "SAQN", "SAQNU", "SAQNW", "SASR", "SATS", "SAVA", "SAVE", "SBAC", "SBBP", "SBBX", "SBCF", "SBFG", "SBGI", "SBLK", "SBLKZ", "SBNY", "SBPH", "SBRA", "SBSI", "SBT", "SBUX", "SCCI", "SCHL", "SCHN", "SCKT", "SCON", "SCOR", "SCPH", "SCPL", "SCSC", "SCVL", "SCWX", "SCYX", "SCZ", "SDC", "SDG", "SDGR", "SDVY", "SEAC", "SECO", "SEDG", "SEED", "SEEL", "SEIC", "SELB", "SELF", "SENEA", "SENEB", "SES", "SESN", "SFBC", "SFBS", "SFET", "SFIX", "SFM", "SFNC", "SFST", "SG", "SGA", "SGBX", "SGC", "SGEN", "SGH", "SGLB", "SGLBW", "SGMA", "SGMO", "SGMS", "SGOC", "SGRP", "SGRY", "SHBI", "SHEN", "SHIP", "SHIPW", "SHIPZ", "SHLO", "SHOO", "SHSP", "SHV", "SHY", "SIBN", "SIC", "SIEB", "SIEN", "SIFY", "SIGA", "SIGI", "SILC", "SILK", "SIMO", "SINA", "SINO", "SINT", "SIRI", "SITM", "SIVB", "SIVBP", "SKOR", "SKYS", "SKYW", "SKYY", "SLAB", "SLCT", "SLDB", "SLGG", "SLGL", "SLGN", "SLM", "SLMBP", "SLNO", "SLP", "SLQD", "SLRC", "SLRX", "SLS", "SLVO", "SMBC", "SMBK", "SMCI", "SMCP", "SMED", "SMH", "SMIT", "SMMC", "SMMCU", "SMMCW", "SMMF", "SMMT", "SMPL", "SMRT", "SMSI", "SMTC", "SMTX", "SNBR", "SNCA", "SNCR", "SND", "SNDE", "SNDL", "SNDX", "SNES", "SNFCA", "SNGX", "SNGXW", "SNLN", "SNOA", "SNPS", "SNSR", "SNSS", "SNUG", "SNY", "SOCL", "SOHO", "SOHOB", "SOHON", "SOHOO", "SOHU", "SOLO", "SOLOW", "SOLY", "SONA", "SONG", "SONGW", "SONM", "SONN", "SONO", "SORL", "SOXX", "SP", "SPAR", "SPCB", "SPFI", "SPI", "SPKE", "SPKEP", "SPLK", "SPNE", "SPNS", "SPOK", "SPPI", "SPRO", "SPRT", "SPSC", "SPT", "SPTN", "SPWH", "SPWR", "SQBG", "SQLV", "SQQQ", "SRAC", "SRACU", "SRACW", "SRAX", "SRCE", "SRCL", "SRDX", "SRET", "SREV", "SRNE", "SRPT", "SRRA", "SRRK", "SRTS", "SRTSW", "SRVA", "SSB", "SSBI", "SSKN", "SSNC", "SSNT", "SSP", "SSPK", "SSPKU", "SSPKW", "SSRM", "SSSS", "SSTI", "SSYS", "STAA", "STAF", "STAY", "STBA", "STCN", "STFC", "STIM", "STKL", "STKS", "STLD", "STML", "STMP", "STND", "STNE", "STOK", "STPP", "STRA", "STRL", "STRM", "STRO", "STRS", "STRT", "STSA", "STX", "STXB", "SUMR", "SUNS", "SUNW", "SUPN", "SURF", "SUSB", "SUSC", "SUSL", "SVA", "SVBI", "SVC", "SVMK", "SVRA", "SVVC", "SWAV", "SWIR", "SWKH", "SWKS", "SWTX", "SXTC", "SY", "SYBT", "SYBX", "SYKE", "SYNA", "SYNC", "SYNH", "SYNL", "SYPR", "SYRS", "TA", "TACO", "TACOW", "TACT", "TAIT", "TANH", "TANNI", "TANNL", "TANNZ", "TAOP", "TAPM", "TAPR", "TARA", "TAST", "TATT", "TAYD", "TBBK", "TBIO", "TBK", "TBLT", "TBLTW", "TBNK", "TBPH", "TC", "TCBI", "TCBIL", "TCBIP", "TCBK", "TCCO", "TCDA", "TCF", "TCFC", "TCFCP", "TCMD", "TCOM", "TCON", "TCPC", "TCRD", "TCRR", "TCX", "TDAC", "TDACU", "TDACW", "TDIV", "TEAM", "TECD", "TECH", "TECTP", "TEDU", "TELA", "TELL", "TENB", "TENX", "TER", "TERP", "TESS", "TEUM", "TFFP", "TFSL", "TGA", "TGEN", "TGLS", "TGTX", "TH", "THBR", "THBRU", "THBRW", "THCA", "THCAU", "THCAW", "THCB", "THCBU", "THCBW", "THFF", "THMO", "THRM", "THTX", "THWWW", "TIGO", "TIGR", "TILE", "TIPT", "TITN", "TIVO", "TLC", "TLF", "TLGT", "TLND", "TLRY", "TLSA", "TLT", "TMDI", "TMDX", "TMSR", "TMUS", "TNAV", "TNDM", "TNXP", "TOCA", "TOPS", "TORC", "TOTA", "TOTAR", "TOTAU", "TOTAW", "TOUR", "TOWN", "TPCO", "TPIC", "TPTX", "TQQQ", "TRCH", "TREE", "TRHC", "TRIB", "TRIL", "TRIP", "TRMB", "TRMD", "TRMK", "TRMT", "TRNS", "TRNX", "TROV", "TROW", "TRPX", "TRS", "TRST", "TRUP", "TRVG", "TRVI", "TRVN", "TSBK", "TSC", "TSCAP", "TSCBP", "TSCO", "TSEM", "TSG", "TSLA", "TSRI", "TTD", "TTEC", "TTEK", "TTGT", "TTMI", "TTNP", "TTOO", "TTPH", "TTTN", "TTWO", "TUES", "TUR", "TURN", "TUSA", "TUSK", "TVIX", "TVTY", "TW", "TWIN", "TWMC", "TWNK", "TWNKW", "TWOU", "TWST", "TXG", "TXMD", "TXN", "TXRH", "TYHT", "TYME", "TZAC", "TZACU", "TZACW", "TZOO", "UAE", "UAL", "UBCP", "UBFO", "UBOH", "UBSI", "UBX", "UCBI", "UCTT", "UEIC", "UEPS", "UFCS", "UFO", "UFPI", "UFPT", "UG", "UGLD", "UHAL", "UIHC", "ULBI", "ULH", "ULTA", "UMBF", "UMPQ", "UMRX", "UNAM", "UNB", "UNFI", "UNIT", "UNTY", "UONE", "UONEK", "UPLD", "UPWK", "URBN", "URGN", "UROV", "USAK", "USAP", "USAU", "USCR", "USEG", "USIG", "USIO", "USLB", "USLM", "USLV", "USMC", "USOI", "USWS", "USWSW", "UTHR", "UTMD", "UTSI", "UVSP", "UXIN", "VALU", "VALX", "VBFC", "VBIV", "VBLT", "VBND", "VBTX", "VC", "VCEL", "VCIT", "VCLT", "VCNX", "VCSH", "VCTR", "VCYT", "VECO", "VEON", "VERB", "VERBW", "VERI", "VERO", "VERU", "VERY", "VETS", "VFF", "VG", "VGIT", "VGLT", "VGSH", "VIAC", "VIACA", "VIAV", "VICR", "VIDI", "VIE", "VIGI", "VIIX", "VIOT", "VIR", "VIRC", "VIRT", "VISL", "VIVE", "VIVO", "VKTX", "VKTXW", "VLGEA", "VLY", "VLYPO", "VLYPP", "VMBS", "VMD", "VNDA", "VNET", "VNOM", "VNQI", "VOD", "VONE", "VONG", "VONV", "VOXX", "VRA", "VRAY", "VRCA", "VREX", "VRIG", "VRML", "VRNA", "VRNS", "VRNT", "VRRM", "VRSK", "VRSN", "VRTS", "VRTU", "VRTX", "VSAT", "VSDA", "VSEC", "VSMV", "VSTM", "VTC", "VTEC", "VTGN", "VTHR", "VTIP", "VTIQ", "VTIQU", "VTIQW", "VTNR", "VTSI", "VTUS", "VTVT", "VTWG", "VTWO", "VTWV", "VUSE", "VUZI", "VVPR", "VVUS", "VWOB", "VXRT", "VXUS", "VYGR", "VYMI", "WABC", "WAFD", "WAFU", "WASH", "WATT", "WB", "WBA", "WBND", "WCLD", "WDAY", "WDC", "WDFC", "WEBK", "WEN", "WERN", "WETF", "WEYS", "WHF", "WHFBZ", "WHLM", "WHLR", "WHLRD", "WHLRP", "WIFI", "WILC", "WIMI", "WINA", "WINC", "WING", "WINS", "WIRE", "WISA", "WIX", "WKEY", "WKHS", "WLDN", "WLFC", "WLTW", "WMGI", "WNEB", "WNFM", "WOOD", "WORX", "WPRT", "WRLD", "WRTC", "WSBC", "WSBF", "WSC", "WSFS", "WSG", "WSTG", "WSTL", "WTBA", "WTER", "WTFC", "WTFCM", "WTRE", "WTREP", "WTRH", "WVE", "WVFC", "WVVI", "WVVIP", "WW", "WWD", "WWR", "WYNN", "XAIR", "XBIO", "XBIOW", "XBIT", "XCUR", "XEL", "XELA", "XELB", "XENE", "XENT", "XERS", "XFOR", "XGN", "XLNX", "XLRN", "XNCR", "XNET", "XOG", "XOMA", "XONE", "XP", "XPEL", "XPER", "XRAY", "XSPA", "XT", "XTLB", "YGYI", "YGYIP", "YI", "YIN", "YJ", "YLCO", "YLDE", "YMAB", "YNDX", "YORW", "YRCW", "YTEN", "YTRA", "YVR", "YY", "Z", "ZAGG", "ZBRA", "ZCMD", "ZEAL", "ZEUS", "ZFGN", "ZG", "ZGNX", "ZGYH", "ZGYHR", "ZGYHU", "ZGYHW", "ZION", "ZIONL", "ZIONN", "ZIONO", "ZIONP", "ZIONW", "ZIOP", "ZIV", "ZIXI", "ZKIN", "ZLAB", "ZM", "ZN", "ZNGA", "ZNTL", "ZNWAA", "ZS", "ZSAN", "ZTEST", "ZUMZ", "ZVO", "ZYNE", "ZYXI"};
            for(int hg=0;hg<jvx.length;hg++){
                arrayList1.add(jvx[hg]);
            }
//                        arrayList1.add("AAL");
//            arrayList1.add("BAC");
//            arrayList1.add("INFY");
//            arrayList1.add("MSFT");
//            arrayList1.add("WMT");


            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList1);
            arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter1);

//            tick = spinner1.getSelectedItem().toString();
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    tick = spinner1.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            final Spinner spinner2 = findViewById(R.id.s2);
            ArrayList<String> arrayList2 = new ArrayList<>();
            for (int oi = 1; oi <= 10; oi++) {
                String lml = String.valueOf(oi);
                arrayList2.add(lml);
            }


            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
            arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(arrayAdapter2);

//            daysx = Integer.parseInt(spinner2.getSelectedItem().toString());
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    daysx = Integer.parseInt(spinner2.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            final Spinner spinner3 = findViewById(R.id.s3);
            ArrayList<String> arrayList3 = new ArrayList<>();
            arrayList3.add("Close");
            arrayList3.add("Open");
            arrayList3.add("High");
            arrayList3.add("Low");
            arrayList3.add("Volume");

            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList3);
            arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(arrayAdapter3);


//            ptype = spinner3.getSelectedItem().toString();

            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ptype = spinner3.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            final Spinner spinner4 = findViewById(R.id.s4);
            ArrayList<String> arrayList4 = new ArrayList<>();
            arrayList4.add("Delta");
            arrayList4.add("Meta");
            arrayList4.add("Gradient");


            ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList4);
            arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner4.setAdapter(arrayAdapter4);

//            algo = spinner4.getSelectedItem().toString();


            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    algo = spinner4.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

//            gvx=(GraphView)findViewById(R.id.gvw);
//            bt2 = (Button) findViewById(R.id.resett);
//            bt2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    gvx.removeAllSeries();
//                    gvx.setVisibility(View.INVISIBLE);
//
//                }
//            });

           // tvx=(TextView)findViewById(R.id.texty);


            bt = (Button) findViewById(R.id.subops);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String myUrl="http://52.90.141.77/engine?algo="+algo+"&tick="+tick+"&ptype="+ptype+"&daysx="+daysx;
                    //String to place our result in
                    String result;
                    //Instantiate new instance of our class
                    HttpGetRequest getRequest = new HttpGetRequest(Main3Activity.this,tvx,bt2,bt,gvx,daysx);
                    //Perform the doInBackground method, passing in our url
                    try {
                        result = getRequest.execute(myUrl).get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            });

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mybutton32:
                mAuth.signOut();
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
                Toast.makeText(this, "Signed out!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

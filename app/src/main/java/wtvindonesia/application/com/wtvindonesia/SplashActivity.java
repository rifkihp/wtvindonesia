package wtvindonesia.application.com.wtvindonesia;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import customfonts.MyTextView;
import wtvindonesia.application.com.libs.CommonUtilities;
import wtvindonesia.application.com.libs.DatabaseHandler;
import wtvindonesia.application.com.libs.JSONParser;
import wtvindonesia.application.com.libs.LocaleHelper;
import wtvindonesia.application.com.model.version;

import static wtvindonesia.application.com.libs.CommonUtilities.getOptionsImage;
import static wtvindonesia.application.com.libs.CommonUtilities.initImageLoader;


public class SplashActivity extends Activity {

    Dialog dialog_update;
    MyTextView text_dialog;
    MyTextView btn_ok;
    MyTextView btn_cancel;

    // Splash screen timer
    static int SPLASH_TIME_OUT = 3000;
    static int TIME_TO_CHECKED = 100;
    Context context;
    DatabaseHandler dh;

    FrameLayout imagebg;
    ImageView image_bg;
    ImageView imagelogo;

    ImageLoader imageLoader;
    DisplayImageOptions imageOptionLogo;
    DisplayImageOptions imageOptionBackground;

    Handler mHandler;
    boolean is_ready_logo, is_ready_bg;

    String logo = "default_logo.png";
    String bg = "default_bg.jpg";
    String app_ver_no = "";
    String app_ver_name = "";
    String app_desc = "";
    int landing_page = 0;
    version app_ver;

    public Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            mHandler.removeCallbacks(this);
            if (is_ready_bg && is_ready_logo) {
                imagebg.setVisibility(View.VISIBLE);
                imagelogo.setVisibility(View.VISIBLE);

                setAnimation();

                //******change activity here*******
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        CommonUtilities.setLandingPage(context, landing_page);
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        i.putExtra("position", 0);
                        if (landing_page == 1) {
                            i.putExtra("landing_page", landing_page);
                        }
                        startActivity(i);
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            } else {
                mHandler.postDelayed(this, TIME_TO_CHECKED);
            }

        }
    };

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(mHandleLoadSplashReceiver);
            mHandler.removeCallbacks(mUpdateTimeTask);

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        try {
            unregisterReceiver(mHandleLoadSplashReceiver);
            mHandler.removeCallbacks(mUpdateTimeTask);

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPause();
    }

    private String getCertificate() {
        String certificate = null;
        PackageManager pm = this.getPackageManager();
        String packageName = this.getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getPublicKey().getEncoded());
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            certificate = hexString.toString();
        } catch (NoSuchAlgorithmException e1) {
        }
        return certificate + ";" + packageName;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mHandleLoadSplashReceiver, new IntentFilter("wtvindonesia.application.com.wtvindonesia.LOAD_SPLASH_SCREEN"));

        String certificate = getCertificate();
        Log.i("certificate", certificate);
    }

    private final BroadcastReceiver mHandleLoadSplashReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (app_ver_no.equalsIgnoreCase(app_ver.getNo()) && app_ver_name.equalsIgnoreCase(app_ver.getNama())) {

                //background
                imageLoader.loadImage(CommonUtilities.SERVER_URL + "/uploads/umum/" + bg, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        is_ready_bg = true;
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        is_ready_bg = true;
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        image_bg.setImageBitmap(loadedImage);
                        is_ready_bg = true;
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        is_ready_bg = true;
                    }
                });

                //logo
                imageLoader.loadImage(CommonUtilities.SERVER_URL + "/uploads/umum/" + logo, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        is_ready_logo = true;
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        is_ready_logo = true;
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        imagelogo.setImageBitmap(loadedImage);
                        is_ready_logo = true;
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        is_ready_logo = true;
                    }
                });
            } else {
                text_dialog.setText(app_desc);
                showUpdateDialog("Update Aplikasi", app_desc);
//                dialog_update.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog_update.show();
            }
        }
    };

    public class loadSplashScreen extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imagebg.setVisibility(View.GONE);
            imagelogo.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(String... urls) {

            String url = CommonUtilities.SERVER_URL + "/store/androidSplashDataStore.php";
            List<NameValuePair> params = new ArrayList<>();
            JSONObject json = new JSONParser().getJSONFromUrl(url, params, null);
            if (json != null) {
                try {
                    bg = json.isNull("bg") ? "default_bg.jpg" : json.getString("bg");
                    logo = json.isNull("logo") ? "default_logo.jpg" : json.getString("logo");
                    app_ver_no = json.isNull("app_ver_no") ? app_ver_no : json.getString("app_ver_no");
                    app_ver_name = json.isNull("app_ver_name") ? app_ver_name : json.getString("app_ver_name");
                    app_desc = json.isNull("app_desc") ? app_desc : json.getString("app_desc");
                    landing_page = json.isNull("landing_page") ? landing_page : json.getInt("landing_page");
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            Intent i = new Intent("wtvindonesia.application.com.wtvindonesia.LOAD_SPLASH_SCREEN");
            sendBroadcast(i);

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*user user_login = CommonUtilities.getSettingUser(this);
        if (user_login.getId() > 0) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }*/

        setContentView(R.layout.activity_splash);


        String language_type = CommonUtilities.getLanguageType(SplashActivity.this);

        if (language_type.equals("en")) {
            LocaleHelper.setLocale(SplashActivity.this, "en");
        } else if (language_type.equals("in")) {
            LocaleHelper.setLocale(SplashActivity.this, "in");
        }

        context = SplashActivity.this;
        app_ver = CommonUtilities.getAppVersion(context);
        app_ver_no = app_ver.getNo();
        app_ver_name = app_ver.getNama();

        initImageLoader(context);
        imageLoader = ImageLoader.getInstance();
        imageOptionLogo = getOptionsImage(R.drawable.blankicon, R.drawable.blankicon);
        imageOptionBackground = getOptionsImage(R.drawable.blankicon, R.drawable.blankicon);

        imagebg = findViewById(R.id.imagebg);
        image_bg = findViewById(R.id.image_bg);
        imagelogo = findViewById(R.id.imagelogo);

        dialog_update = new Dialog(context);
        dialog_update.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_update.setCancelable(false);
        dialog_update.setContentView(R.layout.update_dialog);

        btn_ok = dialog_update.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                //Toast.makeText(context, appPackageName, Toast.LENGTH_SHORT).show();
                dialog_update.dismiss();
                finish();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        btn_cancel = dialog_update.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                //Toast.makeText(context, appPackageName, Toast.LENGTH_SHORT).show();
                dialog_update.dismiss();
                CommonUtilities.setLandingPage(context, landing_page);
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                i.putExtra("position", 0);
                if (landing_page == 1) {
                    i.putExtra("landing_page", landing_page);
                }
                startActivity(i);
                finish();
            }
        });
        text_dialog = dialog_update.findViewById(R.id.text_dialog);

        is_ready_logo = false;
        is_ready_bg = false;

        mHandler = new Handler();
        mHandler.postDelayed(mUpdateTimeTask, TIME_TO_CHECKED);

        dh = new DatabaseHandler(context);
        dh.createTable();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        new loadSplashScreen().execute();
    }


    private void setAnimation() {

        //start logo animation
        findViewById(R.id.imagelogo).setAlpha(1.0F);
        Animation anim_logo = AnimationUtils.loadAnimation(this, R.anim.fade);
        findViewById(R.id.imagelogo).startAnimation(anim_logo);
    }

    public void showUpdateDialog(String title, String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                SplashActivity.this.getResources().getString(R.string.update),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        //Toast.makeText(context, appPackageName, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                });

        builder1.setNegativeButton(
                "Lain Kali",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        //Toast.makeText(context, appPackageName, Toast.LENGTH_SHORT).show();
                        dialog_update.dismiss();
                        CommonUtilities.setLandingPage(context, landing_page);
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        i.putExtra("position", 0);
                        if (landing_page == 1) {
                            i.putExtra("landing_page", landing_page);
                        }
                        startActivity(i);
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
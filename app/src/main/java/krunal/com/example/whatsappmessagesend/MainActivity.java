package krunal.com.example.whatsappmessagesend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Button button;

    public static final int REQUEST_READ_PHONE_STATE = 123;
    private static final String AUTHORITY =
            BuildConfig.APPLICATION_ID + ".myfileprovider";
    String defultapp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        checkpermission();

         defultapp = "WhatsApp";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if the WhatsApp or BusinessWhatsApp is install or not.
                boolean isWhatsappInstalled = ClsGlobal.whatsappInstalledOrNot("com.whatsapp", MainActivity.this);
                boolean isWhatsappBusinessInstalled = ClsGlobal.whatsappInstalledOrNot("com.whatsapp.w4b", MainActivity.this);


                if (isWhatsappBusinessInstalled || isWhatsappInstalled) {

                    try {
                        String url = "https://api.whatsapp.com/send?phone=+91 number" +
                                  "&text=" + URLEncoder.encode("Your message", "UTF-8");

                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        sendIntent.setAction(Intent.ACTION_VIEW);

                        if (defultapp.equalsIgnoreCase("WhatsApp")) {
                            sendIntent.setPackage("com.whatsapp");
                            Log.e("message", "WhatsApp");
                        } else if (defultapp.equalsIgnoreCase("BusinessWhatsApp")) {
                            sendIntent.setPackage("com.whatsapp.w4b");
                            Log.e("message", "Business WhatsApp");
                        }

                        sendIntent.setData(Uri.parse(url));
                        startActivity(sendIntent);

                    } catch (Exception e) {
                        Log.e("message", e.getMessage());
                    }

                } else {
                    Toast.makeText(MainActivity.this, "WhatsApp Not Install!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED
                        && grantResults[6] == PackageManager.PERMISSION_GRANTED
                        && grantResults[7] == PackageManager.PERMISSION_GRANTED
                        && grantResults[8] == PackageManager.PERMISSION_GRANTED
                        && grantResults[9] == PackageManager.PERMISSION_GRANTED
                        && grantResults[10] == PackageManager.PERMISSION_GRANTED
                        && grantResults[11] == PackageManager.PERMISSION_GRANTED
                        && grantResults[12] == PackageManager.PERMISSION_GRANTED

                ) {

                    // permission granted!
                    // you may now do the action that requires this permission
                } else {
                    // permission denied


                }
                return;
            }

        }
    }

    private void checkpermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECEIVE_BOOT_COMPLETED)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
//                ||ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.READ_CALL_LOG)
//                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.ACCESS_NETWORK_STATE
                            , Manifest.permission.ACCESS_COARSE_LOCATION
                            , Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.READ_PHONE_STATE
                            , Manifest.permission.RECEIVE_BOOT_COMPLETED
                            , Manifest.permission.ACCESS_WIFI_STATE
                            , Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.CAMERA
                            , Manifest.permission.READ_SMS
                            , Manifest.permission.SEND_SMS
                            , Manifest.permission.CALL_PHONE
                            , Manifest.permission.READ_CONTACTS}
                    , REQUEST_READ_PHONE_STATE);
        }
    }
}

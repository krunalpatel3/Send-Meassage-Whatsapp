package krunal.com.example.whatsappmessagesend;

import android.content.Context;
import android.content.pm.PackageManager;

public class ClsGlobal {


    public static boolean whatsappInstalledOrNot(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


}

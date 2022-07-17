package com.gabriel.whatsapp_clone.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean validarPermissoes(Activity activity, List<String> permissoesNecessarias, int requestCode ){
        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissoes = new ArrayList<>();
            permissoesNecessarias.forEach(p -> {
               boolean temPermissao = ContextCompat.
                       checkSelfPermission(
                               activity.getApplicationContext(), p) == PackageManager.PERMISSION_GRANTED;
               if(!temPermissao){
                   listaPermissoes.add(p);
               }
            });
            if (listaPermissoes.isEmpty()) return true;
            String[] novasPermissoes = new String[listaPermissoes.size()];

            ActivityCompat.requestPermissions(
                    activity,
                    listaPermissoes.toArray(novasPermissoes),
                    requestCode
            );
        }
        return true;
    }
}

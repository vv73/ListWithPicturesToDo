package ru.samsung.itschool.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Task {
      public static void showMessage(Context context, String message)
      {
    	  //Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    	  //toast.show();
    	  AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
    	  dlgAlert.setMessage(message);
    	  dlgAlert.setTitle("Задание");
    	  dlgAlert.setPositiveButton("OK", null);
    	  dlgAlert.setCancelable(true);
    	  dlgAlert.setPositiveButton("Ok",
    			    new DialogInterface.OnClickListener() {
    			        public void onClick(DialogInterface dialog, int which) {
    			          //dismiss the dialog  
    			        }
    			    });
    	  dlgAlert.create().show();
      }
      
}

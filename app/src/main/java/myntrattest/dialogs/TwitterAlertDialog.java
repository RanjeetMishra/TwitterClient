package myntrattest.dialogs;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import myntrattest.config.Constants;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;

import myntrattest.twitterclient.R;

/**
 * Created by ranjeetmishra on 04/02/16.
 */
public class TwitterAlertDialog extends AppCompatDialogFragment {

    public static TwitterAlertDialog newInstance(String title, String message, int type) {
        TwitterAlertDialog alertDialog = new TwitterAlertDialog();
        Bundle localBundle = new Bundle();
        localBundle.putString("title", title);
        localBundle.putString("msg", message);
        localBundle.putInt("type", type);
        alertDialog.setArguments(localBundle);
        return alertDialog;
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String msg = getArguments().getString("msg");
        int i = getArguments().getInt("type");

        switch(i) {
            case Constants.NETWORK_ERROR_ALERT_TYPE:
                return  new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle).setTitle(title)
                            .setMessage(msg)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        try {
                                            startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                                        } catch (ActivityNotFoundException localActivityNotFoundException) {

                                        }
                                    }
                            })
                        .setNegativeButton("Cancel", null)
                        .create();

            default:
                return new AlertDialog.Builder(getActivity()).create();
        }
    }
}

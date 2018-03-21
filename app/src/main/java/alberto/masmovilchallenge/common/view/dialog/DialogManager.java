package alberto.masmovilchallenge.common.view.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;

public class DialogManager {
    private final Activity activity;

    @Inject
    public DialogManager(Activity activity) {
        this.activity = activity;
    }

    public void showErrorDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(activity.getString(R.string.dialog_title_error));
        alertDialog.setMessage(activity.getString(R.string.dialog_message_error));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void showCustomDialog(String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(activity.getString(R.string.dialog_title_alert));
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, activity.getString(R.string.ok), listener);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}

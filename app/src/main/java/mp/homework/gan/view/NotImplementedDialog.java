package mp.homework.gan.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import mp.homework.gan.R;

public class NotImplementedDialog extends DialogFragment {

    /*
        Creates a handy "work in progress" dialog for features that will be implemented later
        To use it:
            
        
        (new NotImplementedDialog()).show(MainMenuActivity.this.getSupportFragmentManager(), null);
                                          ^^^ change this with the calling activity
    */
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.icons8_construction_26);
        builder.setTitle(R.string.unimplemented_feature_title);
        builder.setMessage(R.string.unimplemented_feature_msg);
        builder.setPositiveButton(R.string.OK, null);
        return builder.create();
    }
}

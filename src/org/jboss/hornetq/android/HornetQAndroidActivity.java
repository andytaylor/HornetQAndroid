package org.jboss.hornetq.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import org.jboss.hornetq.HornetQChatHandler;

public class HornetQAndroidActivity extends Activity implements OnClickListener
{
   private DbAdapter dbHelper;

   private EditText username;

   private EditText password;

   private EditText port;

   private Button login;

   private EditText host;
   private HornetQChatHandler chatHandler;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      username = (EditText) findViewById(R.id.username);
      password = (EditText) findViewById(R.id.password);
      host = (EditText) findViewById(R.id.host);
      port = (EditText) findViewById(R.id.port);
      login = (Button) findViewById(R.id.login);

      login.setOnClickListener(this);

      dbHelper = new DbAdapter(this);
      chatHandler = new HornetQChatHandler();
      loadSettings();
   }

   public void loadSettings()
   {
      dbHelper.open();
      Settings settings = dbHelper.getSettings();
      dbHelper.close();
      if (settings != null)
      {
         username.setText(settings.getUsername());
         password.setText(settings.getPassword());
         host.setText(settings.getHost());
         port.setText(settings.getPort());
      }
   }

   public void saveSettings()
   {
      dbHelper.open();
      dbHelper.saveSettings(username.getText().toString(), password.getText().toString(), host.getText().toString(), port.getText().toString());
      dbHelper.close();
   }

   public void onClick(View v)
   {
      switch (v.getId())
      {
         case R.id.login:
            saveSettings();
            try
            {
               chatHandler.connect(host.getText().toString(), port.getText().toString());
            }
            catch (Exception e)
            {
               e.printStackTrace();
               alertbox("unable to connect", e.getMessage());
               return;
            }
            Intent i = new Intent(this, MessageActivity.class);
            startActivityForResult(i, 0);
            break;
      }
   }

   protected void alertbox(String title, String mymessage)
   {
      new AlertDialog.Builder(this)
            .setMessage(mymessage)
            .setTitle(title)
            .setCancelable(true)
            .setNeutralButton(android.R.string.cancel,
                  new DialogInterface.OnClickListener()
                  {
                     public void onClick(DialogInterface dialog, int whichButton)
                     {
                     }
                  })
            .show();
   }
}
package com.pure.settings;
import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
//import android.os.IHelloService;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String TAG = "PureSettings";
    //private IHelloService service = null;
    private Button button;

   private void test() {
        Log.d(TAG, "test");
  /*      try {
            service.hello("qiushao");
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  service = IHelloService.Stub.asInterface(ServiceManager.getService("HelloService"));

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }
}

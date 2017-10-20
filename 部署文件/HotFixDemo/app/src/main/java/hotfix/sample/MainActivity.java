package hotfix.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import hotfix.sample.com.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sample);
        System.out.println(100 / 1);
        Toast.makeText(this, "i am patch", Toast.LENGTH_LONG).show();
        findViewById(R.id.toastInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "patch", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

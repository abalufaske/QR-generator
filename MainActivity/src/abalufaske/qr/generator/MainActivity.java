package abalufaske.qr.generator;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText Edit_url;
	private EditText Edit_width;
	private int width=250;
	private int height=250;
	private String url="";
	private Button getQR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Edit_url = (EditText) findViewById(R.id.EditURL);    
        Edit_width = (EditText) findViewById(R.id.editWidth);    

        getQR =((Button) findViewById(R.id.verQR));
        getQR.setOnClickListener(mButtonVerQRClickListener);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    OnClickListener mButtonVerQRClickListener = new OnClickListener(){
    	@Override
    	public void onClick(View arg0){
    		
    		if(Edit_url.getText().toString().length()>0)
    		{
    			url=Edit_url.getText().toString();

    			if(Edit_width.getText().toString().length()>0)
    			{
    				width=parseInt(Edit_width);

    			}	else
        		{
        		}
    			
    			if(Edit_width.getText().toString().length()>0)
    			{
    				height=parseInt(Edit_width);

    			}	else
        		{
        		}
    			
    			Intent intent = new Intent(getApplication(),generateQR.class);
    			intent.putExtra("width", width);
    			intent.putExtra("height", height);
    			intent.putExtra("url", url);
    			startActivity(intent);
    			
    		}
    		else
    		{
    		}
    		
    	}
    };

    
	public static int parseInt(EditText text){
		if (text.getText().toString().trim().length() != 0){
			return Integer.parseInt(text.getText().toString());
		}else{
			return 0;
		}
	}
    
}

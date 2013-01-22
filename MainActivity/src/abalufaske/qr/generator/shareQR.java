package abalufaske.qr.generator;



import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class shareQR extends Activity {

	private int width=250;
	private int height=250;
	private String url="";
	private Button share;
	private Button start;
	private File qrdirectory;

	private ImageView qrCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compartir_qr);
        
        if (getIntent().hasExtra("width")){ 
    		
        	width= (int) getIntent().getIntExtra("width", 250);
  		  
  	  }  

        if (getIntent().hasExtra("height")){ 
    		
	 height= (int) getIntent().getIntExtra("height", 250);
  		  
  	  }  
        
        if (getIntent().hasExtra("url")){ 
    		
       	 url=  getIntent().getStringExtra("url");
         		  
         	  }  
        start =((Button) findViewById(R.id.generateAnother));
        start.setOnClickListener(mButtonBackClickListener);
        share =((Button) findViewById(R.id.ShareButton));
        share.setOnClickListener(mButtondownloadQRClickListener);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));

        
    	BitmapFactory.Options options=new BitmapFactory.Options();
  	   options.inSampleSize = 8;
        
        qrCode = ((ImageView)  findViewById(R.id.imageView1));
        File sd = Environment.getExternalStorageDirectory();
		 qrdirectory = new File(sd+"/generatedQR/"+width+"x"+height+url+ ".PNG");
		 qrCode.setImageResource(R.drawable.oksign);

       
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    OnClickListener mButtondownloadQRClickListener = new OnClickListener(){
    	@Override
    	public void onClick(View arg0){
    		
    	

    try {
    	Intent share = new Intent(Intent.ACTION_SEND);
    	share.setType("image/png");
    	share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+qrdirectory));
    	startActivity(Intent.createChooser(share, "Share Image"));
    	
    	
            

    } catch( Exception e )
            {
            }
    }
    	} ;

    	OnClickListener mButtonBackClickListener = new OnClickListener(){
        	@Override
        	public void onClick(View arg0){
        		
        		
                Intent intent = new Intent(getApplication(),MainActivity.class);
    			startActivity(intent);


        	}
        };

      
    
    
}

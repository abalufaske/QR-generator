package abalufaske.qr.generator;



import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class generateQR extends Activity {

	private int width=250;
	private int height=250;
	private String url="";
	private Button downloadQR;
	private Button back;
	private WebView qrCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_qr);
        
        if (getIntent().hasExtra("width")){ 
    		
        	width= (int) getIntent().getIntExtra("width", 250);
  		  
  	  }  

        if (getIntent().hasExtra("height")){ 
    		
	 height= (int) getIntent().getIntExtra("height", 250);
  		  
  	  }  
        
        if (getIntent().hasExtra("url")){ 
    		
       	 url=  getIntent().getStringExtra("url");
         		  
         	  }  
        back =((Button) findViewById(R.id.BackButton));
        back.setOnClickListener(mButtonBackClickListener);
        downloadQR =((Button) findViewById(R.id.Download));
        downloadQR.setOnClickListener(mButtondownloadQRClickListener);
        
        
        qrCode = ((WebView)  findViewById(R.id.webView1));
        
   //    prueba = new WebView(this);
        qrCode.loadUrl("https://chart.googleapis.com/chart?chs="+width+"x"+height+"&cht=qr&chl="+url+"&choe=UTF-8");      
       qrCode.setDrawingCacheEnabled(true);
       qrCode.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
               MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
       //v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
       qrCode.layout(0, 0, qrCode.getWidth(), qrCode.getHeight());
       qrCode.buildDrawingCache(true);
       
       
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    OnClickListener mButtondownloadQRClickListener = new OnClickListener(){
    	@Override
    	public void onClick(View arg0){
    		
    		try{
    			
    			   Picture picture = qrCode.capturePicture();

    	    Bitmap  b = Bitmap.createBitmap( picture.getWidth(), picture.getHeight(), Bitmap.Config.ARGB_8888);

    	    Canvas c = new Canvas( b );

    	    picture.draw( c );

    	    FileOutputStream fos = null;

    	    try {

    	    		File sd = Environment.getExternalStorageDirectory();
    	    		File qrdirectory = new File(sd+"/generatedQR/");
    	    		// have the object build the directory structure, if needed.
    	    		qrdirectory.mkdirs();
    	    		File outputFile = new File(qrdirectory, width+"x"+height+url+ ".PNG");
    	            fos = new FileOutputStream( outputFile );

    	            if ( fos != null )
    	            {
    	                    b.compress(Bitmap.CompressFormat.PNG, 90, fos );

    	                    fos.close();
    	                	 Intent intent = new Intent(getApplication(),shareQR.class);

    	          			intent.putExtra("width", width);
    	          			intent.putExtra("height", height);
    	          			intent.putExtra("url", url);

    	          			startActivity(intent);
    	                    
    	            }
    	        	

    	    } catch( Exception e )
    	            {
    	    	 Toast toast1 =
    	    	            Toast.makeText(getApplicationContext(),R.string.wait
    	    	                    , Toast.LENGTH_LONG);
    	    	 
    	    	        toast1.show();
    	            }
    	    
    	    	
    			
    		}
    		catch( Exception e )
            {
            }
    		
    		
    	 
    	
    	}
    	} ;

    	OnClickListener mButtonBackClickListener = new OnClickListener(){
        	@Override
        	public void onClick(View arg0){
        		
        		
	    		cancelMainActivity();

        	}
        };

      
    
		private void cancelMainActivity() {
			
		
		
			this.finish();		
		}  
    
}

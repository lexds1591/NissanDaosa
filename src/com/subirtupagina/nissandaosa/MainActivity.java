package com.subirtupagina.nissandaosa;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Bitmap loadedImage;
	 static private String imageHttpAddress = "http://www.nissandaosa.com";
	 static private String dominio = "http://www.nissandaosa.com";
	 private ImageView img;
	 static private String ruta = "/mnt/emmc/promo.jpg";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//no rotar pantalla
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		//eliminar la barra de titulo
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		img = (ImageView)findViewById(R.id.imageView1);
	    downloadFile(imageHttpAddress);
	 }
	 
	 void downloadFile(String imageHttpAddress) {
       URL imageUrl = null;
       try {
           imageUrl = new URL(imageHttpAddress);
           HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
           conn.connect();            
           
			loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
			img.setImageBitmap(loadedImage);
            
			//guardar la imagen en la memoria interna
			InputStream is = conn.getInputStream();
           FileOutputStream out = new FileOutputStream(ruta);
           
           byte[] buffer = new byte[1024];
           int len;
           while((len  = is.read(buffer))>0){
               out.write(buffer, 0, len );
           }
           is.close();
           
           
           //guardar la imagen en la ruta y en el formato especificado
           loadedImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
           out.flush();
           out.close();
           
           
       } catch (IOException e) {
           Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
           e.printStackTrace();
       }
			
	}
	public void abrirPagina(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(dominio));
		this.startActivity(intent);
	}


}

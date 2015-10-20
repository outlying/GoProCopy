package com.antyzero.goprocopy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ExternalSD externalSD = new ExternalSD( this );

        final File dir = externalSD.getDir();

        new Thread( new Runnable() {

            @Override
            public void run() {

                try {
                    FileUtils.copyDirectoryOneLocationToAnotherLocation(
                            new File( dir, "DCIM" ),
                            getExternalFilesDir( Environment.DIRECTORY_MOVIES ) );
                } catch ( IOException e ) {
                    System.out.println( "XDXDXDX" + e );
                    e.printStackTrace();
                }
            }
        } ).start();
    }

}

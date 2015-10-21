package com.antyzero.goprocopy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.antyzero.goprocopy.disk.ExternalSD;
import com.antyzero.goprocopy.disk.FileUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final ExternalSD externalSD = new ExternalSD( this );

        final File dir = externalSD.getDir();

        new Thread( new Runnable() {

            @Override
            public void run() {

                try {
                    String dateTime = "asd";

                    FileUtils.copyDirectoryOneLocationToAnotherLocation(
                            new File( dir, "DCIM" ),
                            new File( getExternalFilesDir( Environment.DIRECTORY_MOVIES ), dateTime ) );
                } catch ( IOException e ) {
                    System.out.println( "XDXDXDX" + e );
                    e.printStackTrace();
                }

            }
        } ).start();


        new Thread( new Runnable() {

            @Override
            public void run() {
                long size = FileUtils.size( externalSD.getDir() );
                System.out.println( "XDXDXD >> " + size );
            }
        } ).start();
    }

}

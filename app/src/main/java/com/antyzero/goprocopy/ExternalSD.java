package com.antyzero.goprocopy;

import android.content.Context;
import android.support.annotation.StringRes;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * For detection and access
 */
public class ExternalSD {

    // Potential list of SD dirs
    private static final List<String> SD_DIRS = Collections.unmodifiableList( Arrays.asList(
            "external_SD",
            "Dog in the fog, don't bother" // this one is incorrect OFC
    ) );

    private static final String STORAGE_DIR = "/storage";

    private final File externalSdDir;

    public ExternalSD( Context context ) throws IllegalStateException{

        // find dir

        File file = new File( STORAGE_DIR );

        String[] directories = file.list( new FilenameFilter() {
            @Override
            public boolean accept( File current, String name ) {
                boolean isDirectory = new File( current, name ).isDirectory();
                return isDirectory && SD_DIRS.contains( name );
            }
        } );

        if( directories == null || directories.length == 0 ){
            throw new IllegalStateException( "Missing external SD card" );
        }

        externalSdDir = new File( STORAGE_DIR, directories[0] );
    }

    public File getDir() {
        return externalSdDir;
    }
}

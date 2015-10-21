package com.antyzero.goprocopy.disk;

import com.antyzero.goprocopy.BuildConfig;
import com.antyzero.goprocopy.general.Precondition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.antyzero.goprocopy.general.Precondition.checkArgument;
import static com.antyzero.goprocopy.general.Precondition.checkNotNull;

/**
 *
 */
public class FileUtils {

    private FileUtils() {
        throw new IllegalAccessError( "Utils class" );
    }

    public static void copyDirectoryOneLocationToAnotherLocation( File sourceLocation, File targetLocation ) throws IOException {

        if ( sourceLocation.isDirectory() ) {

            if ( !targetLocation.exists() ) {

                if ( sourceLocation.isDirectory() ) {
                    if ( !targetLocation.mkdir() ) {
                        throw new IOException( "Unable to create target dir " + targetLocation.getAbsolutePath() );
                    }
                } else {
                    if ( !targetLocation.createNewFile() ) {
                        throw new IOException( "Unable to create new file " + targetLocation.getAbsolutePath() );
                    }
                }
            }

            String[] children = sourceLocation.list();
            for ( int i = 0; i < sourceLocation.listFiles().length; i++ ) {
                copyDirectoryOneLocationToAnotherLocation( new File( sourceLocation, children[i] ), new File( targetLocation, children[i] ) );
            }
        } else {

            InputStream in = new FileInputStream( sourceLocation );
            OutputStream out = new FileOutputStream( targetLocation );

            if ( BuildConfig.DEBUG ) {
                System.out.println( "Copy to " + targetLocation.getAbsolutePath() );
            }

            byte[] buf = new byte[1024];
            int len;
            while ( ( len = in.read( buf ) ) > 0 ) {
                out.write( buf, 0, len );
            }
            in.close();
            out.close();
        }
    }

    /**
     * Calculates file size or directory with all files contained recursively
     *
     * @param file or dir for size calculation
     * @return bytes of data
     */
    public static long size( File file ) {
        return size( file, 0 );
    }

    /**
     * Calculates file size or directory with all files contained recursively
     *
     * @param file        or dir for size calculation
     * @param initialSize for internal use because of recurrence
     * @return bytes of data
     */
    private static long size( File file, long initialSize ) {

        long result = initialSize;

        if ( file != null && file.exists() ) {
            if ( file.isDirectory() ) {
                for ( String child : file.list() ) {
                    result = result + size( new File( file, child ) );
                }
            } else if ( file.isFile() ) {
                result = result + file.length();
            }
        }

        return result;
    }
}

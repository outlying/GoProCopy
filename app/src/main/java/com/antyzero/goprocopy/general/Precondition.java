package com.antyzero.goprocopy.general;

import java.util.Objects;

/**
 * Created by iwopolanski on 21.10.2015.
 */
public class Precondition {

    private Precondition() {
        throw new IllegalAccessError( "Utils class" );
    }

    public static void checkArgument( boolean expression, Object errorMessage ) {
        if ( !expression ) {
            if ( errorMessage != null ) {
                throw new IllegalArgumentException( String.valueOf( errorMessage ) );
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static <T> void checkNotNull( T t ) {
        checkArgument( t != null, "Null value not allowed" );
    }
}

package com.antyzero.goprocopy.disk;

import java.io.File;

/**
 * For detection and access to external HDD connected via USB OTG
 */
public class ExternalHDD {

    private final String storageDir;
    private final String externalHddDir;

    private final File externalHddPath;

    /**
     * ...
     */
    public ExternalHDD() {

        // TODO detect main storage dir
        storageDir = "/storage/";

        // TODO detect external HDD subdirectory
        externalHddDir = "USBstorage1";

        externalHddPath = new File( storageDir, externalHddDir );
    }

    /**
     * Access to external HDD connected to device
     *
     * @return File pointing to HDD
     */
    public File getPath() {
        return externalHddPath;
    }
}

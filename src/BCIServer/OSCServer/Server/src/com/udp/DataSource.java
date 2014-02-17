package com.udp;

import dataPackages.EEGData;

public interface DataSource {
    public EEGData getEEGData();
}

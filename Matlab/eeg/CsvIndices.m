classdef CsvIndices
    %CSVINDICES Konstanten zur Adressierung von eeg-csv-Spalten
    %   Hiermit koennen die Spalten der aus der csv-Datei ausgelesenen
    %   Matrix ohne magische Nummern adressiert werden.
    
    properties (Constant = true)
        COUNTER     =  1;
        AF3         =  2;
        F7          =  3;
        F3          =  4;
        FC5         =  5;
        T7          =  6;
        P7          =  7;
        O1          =  8;
        O2          =  9;
        P8          = 10;
        T8          = 11;
        FC6         = 12;
        F4          = 13;
        F8          = 14;
        AF4         = 15;
        GYROX       = 16;
        GYROY       = 17;
        TIMESTAMP   = 18;
        FUNC_ID     = 19;
        FUNC_VALUE  = 20;
        MARKER      = 21;
        SYNC_SIGNAL = 22;
    end
    
end


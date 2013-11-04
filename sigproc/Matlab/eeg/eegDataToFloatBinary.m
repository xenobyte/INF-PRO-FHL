%% Elektroden-Daten aus CSV einlesen, interpolieren und binär (32-Bit-float) speichern
%  Daten werden eingelesen, auf aequidistante Zeitintervalle (128 Hz)
%  interpoliert und dann als float codiert binaer gespeichert.
%
%  TODO: redundante Codezeilen eleganter lösen (am besten gleich als ganze
%  Matrix bearbeiten oder zumindest mit ner Schleife arbeiten)

OFILENAME = 'output.data'; % ggf aendern
IFILENAME = '../../../eegdata/Timo/Video_linkes_Bein_20131021_104452.eeg'; % ggf aendern

%% Daten einlesen:
eegMatrix = csvread(IFILENAME,1,0);


%% Zugriff auf Spalten:
af3        = eegMatrix(:, CsvIndices.AF3);
f7         = eegMatrix(:, CsvIndices.F7);
f3         = eegMatrix(:, CsvIndices.F3);
fc5        = eegMatrix(:, CsvIndices.FC5);
t7         = eegMatrix(:, CsvIndices.T7);
p7         = eegMatrix(:, CsvIndices.P7);
o1         = eegMatrix(:, CsvIndices.O1);
o2         = eegMatrix(:, CsvIndices.O2);
p8         = eegMatrix(:, CsvIndices.P8);
t8         = eegMatrix(:, CsvIndices.T8);
fc6        = eegMatrix(:, CsvIndices.FC6);
f4         = eegMatrix(:, CsvIndices.F4);
f8         = eegMatrix(:, CsvIndices.F8);
af4        = eegMatrix(:, CsvIndices.AF4);

%% Interpolieren auf aequidistante Samples:
timestamps = eegMatrix(:, CsvIndices.TIMESTAMP);    % die Original-Timestamps
t_Eq = timestamps(1) : 1/128 : timestamps(end)-1;   % optimale, aequidistante Timestamps (128 Hz)
idx = find(diff(timestamps) > 0);                   % Werte mit gleichem Timestamp filter (nur einer pro Timestamp)

interp_af3        = transpose(interp1(timestamps(idx), af3(idx), t_Eq)); 
interp_f7         = transpose(interp1(timestamps(idx), f7(idx), t_Eq)); 
interp_f3         = transpose(interp1(timestamps(idx), f3(idx), t_Eq)); 
interp_fc5        = transpose(interp1(timestamps(idx), fc5(idx), t_Eq)); 
interp_t7         = transpose(interp1(timestamps(idx), t7(idx), t_Eq)); 
interp_p7         = transpose(interp1(timestamps(idx), p7(idx), t_Eq)); 
interp_o1         = transpose(interp1(timestamps(idx), o1(idx), t_Eq)); 
interp_o2         = transpose(interp1(timestamps(idx), o2(idx), t_Eq)); 
interp_p8         = transpose(interp1(timestamps(idx), p8(idx), t_Eq)); 
interp_t8         = transpose(interp1(timestamps(idx), t8(idx), t_Eq)); 
interp_fc6        = transpose(interp1(timestamps(idx), fc6(idx), t_Eq)); 
interp_f4         = transpose(interp1(timestamps(idx), f4(idx), t_Eq)); 
interp_f8         = transpose(interp1(timestamps(idx), f8(idx), t_Eq)); 
interp_af4        = transpose(interp1(timestamps(idx), af4(idx), t_Eq)); 

outputMatrix = [interp_af3 interp_f7  interp_f3  interp_fc5 interp_t7  interp_p7  interp_o1  interp_o2  interp_p8  interp_t8  interp_fc6 interp_f4  interp_f8  interp_af4];

%% Speichern als float
ofile = fopen(OFILENAME, 'w');
fwrite(ofile, outputMatrix, 'float')
fclose(ofile);
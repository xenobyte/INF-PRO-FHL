%% Linearer Plot des Versuchs "Video_linkes_Bein"
%  csvread(filename, row, column) -> wir starten bei Zeile 1, da Zeile 0
%  den CSV-Header enthaelt:

eegMatrix = csvread('../../../eegdata/Timo/Video_linkes_Bein_20131021_104452.eeg',1,0);

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

gyrox      = eegMatrix(:, CsvIndices.GYROX);
gyroy      = eegMatrix(:, CsvIndices.GYROY);

timestamps = eegMatrix(:, CsvIndices.TIMESTAMP);

%% Daten zweidimensional plotten:
figure
subplot(3,1,1); 
plot(timestamps,af3, 'b');  % b wie blue
hold on;                    % ins selbe Diagramm plotten
plot(timestamps, f7, 'r');  % r wie red
plot(timestamps, f3, 'g');  % green
plot(timestamps, fc5, 'k'); % black
plot(timestamps, t7, 'm');  % magenta
plot(timestamps, p7, 'c');  % cyan
plot(timestamps, o1, 'y');  % yellow
legend('af3', 'f7', 'f3', 'fc5', 't7', 'p7', 'o1');    % Legende
title('Linke Kopfhaelfte');
xlabel('t in s');
ylabel('eeg-Pegel - Einheit???');
hold off;

subplot(3,1,2); 
plot(timestamps,af4, 'b');  % b wie blue
hold on;                    % ins selbe Diagramm plotten
plot(timestamps, f8, 'r');  % r wie red
plot(timestamps, f4, 'g');  % green
plot(timestamps, fc6, 'k'); % black
plot(timestamps, t8, 'm');  % magenta
plot(timestamps, p8, 'c');  % cyan
plot(timestamps, o2, 'y');  % yellow
legend('af4', 'f8', 'f4', 'fc6', 't8', 'p8', 'o2');    % Legende
title('Rechte Kopfhaelfte');
xlabel('t in s');
ylabel('eeg-Pegel - Einheit???');
hold off;

subplot(3,1,3); 
plot(timestamps, gyrox, 'b');  % b wie blue
hold on;                       % ins selbe Diagramm plotten
plot(timestamps, gyroy, 'r');  % r wie red
legend('GyroX', 'Gyroy');    % Legende
title('Gyroskop');
xlabel('t in s');
ylabel('Gyro-Pegel - Einheit???');
hold off;

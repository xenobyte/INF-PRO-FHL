%% Beispielskript: Einlesen von und Zugreifen auf eeg-Daten aus einer csv-Datei

%% Einlesen eines CSV-Files:
%  csvread(filename, row, column) -> wir starten bei Zeile 1, da Zeile 0
%  den CSV-Header enthaelt:

eegMatrix = csvread('../../eegdata/Martin/RandomThoughts_20131009_111253.txt',1,0);

%% Zugriff auf eine Zeile:
%  Die erste Zeile wird als Vektor der Variable x zugewiesen:
%  (Achtung: Matlab-Vektoren-Indizes starten bei 1, nicht bei 0!)

x = eegMatrix(1,:) % fehlendes Semikolon sorgt fuer Ausgabe auf dem Terminal

%% Zugriff auf Spalten:
af3 = eegMatrix(:, 2);
% besser ohne magic numbers:
f7  = eegMatrix(:, CsvIndices.F7);
timestamps = eegMatrix(:, CsvIndices.TIMESTAMP);

%% Daten zweidimensional plotten:
plot(timestamps,af3, 'b');  % b wie blue
hold on;                    % ins selbe Diagramm plotten
plot(timestamps, f7, 'r');  % r wie red
plot(timestamps, eegMatrix(:, CsvIndices.F3), 'g'); % green
hold off;

legend('af3', 'f7', 'f3');    % Legende
title('Beispielplot');
xlabel('t in s');
ylabel('eeg-Pegel - Einheit???');
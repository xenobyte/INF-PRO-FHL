eegMatrix = csvread('C:\Users\Wojtek\Documents\GitHub\INF-PRO-FHL\eegdata\Timo\Neutral_20131014_131942.txt',1,0);

f7         = eegMatrix(:, CsvIndices.F7);
timestamps = eegMatrix(:, CsvIndices.TIMESTAMP);

t_eq = timestamps(1) : 1/128 : timestamps(end)-1;
idx = find(diff(timestamps) > 0);

f7interp = interp1(timestamps(idx), f7(idx), t_eq);

%Der Mittelwert des Signals wird abgezogen, da wir sonst am Anfang einen zu
%großen Sprung haben
f7interpMittel = f7interp - mean(f7interp);

%auf 32 Abtastwerte downsampeln (für bessere Zeitliche Auflösung auf dem
%Spektrogramm)
f7downsample = downsample(f7interpMittel,4);

%Bandpass mit Durchlassbereich von 1.5 ~ 2.5 Hz
f7filter = filter(bandpass2Hz,f7downsample);
f7Abs = abs(f7filter);

%Tiefpass anwenden damit die Kurve feinere Übergänge hat
f7final = filter(lowpass02Hz,f7Abs);
t_down = downsample(t_eq,4);

subplot(4,1,1);
plot(t_down, f7downsample);
title('downsample mit faktor 4 (32 samples)');
grid on;

subplot(4,1,2);
spectrogram(f7downsample, 64, 32, [], 32);

subplot(4,1,3);
plot(t_down, f7Abs);
title('Bandpass gefiltert mit Durchlassbereich 1,5 ~ 2,5 Hz');
grid on;

subplot(4,1,4);
plot(t_down, f7final);
title('Tiefpass gefiltert mit Grenzfrequenz 0,2 Hz');
grid on;
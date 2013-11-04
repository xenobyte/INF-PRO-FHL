%% Erster Versuch mit der Wavelet-Toolbox
% entsprechend den Beispielen auf:
% s.a. www.mathworks.com/help/wavelet/examples/continuous-and-discrete-wavelet-analysis.html

%% Rohdaten einlesen
%  csvread(filename, row, column) -> wir starten bei Zeile 1, da Zeile 0
%  den CSV-Header enthaelt:
eegMatrix = csvread('../../../eegdata/Timo/Video_linkes_Bein_20131021_104452.eeg',1,0);

%% Interpolieren auf aequidistante Samples:
timestamps = eegMatrix(:, CsvIndices.TIMESTAMP);    % die Original-Timestamps
t_Eq = timestamps(1) : 1/128 : timestamps(end)-1;   % optimale, aequidistante Timestamps (128 Hz)
idx = find(diff(timestamps) > 0);                   % Werte mit gleichem Timestamp filter (nur einer pro Timestamp)

af3       = eegMatrix(:, CsvIndices.AF3);               % Original-Werte af3
af3interp = interp1(timestamps(idx), af3(idx), t_Eq);   % Interpolierte Werte af3

%% Grosse Abweichungen vom Mittelwert filtern:
meanVal = mean(af3interp);  % Mittelwert
threshold = 50;             % Schwellwert (maximal tolerierte Abweichung)
idx2 = find(abs(af3interp - meanVal) > threshold); % Indizes der Abweichungen
af3interp(idx2) = meanVal;  % bei Abweichungen durch Mittelwert ersetzen

%% Untransformierte Daten zweidimensional plotten:
figure
subplot(3,1,1); 
plot(t_Eq ,af3interp, 'b');  % b wie blue
legend('af3 Interpoliert');    % Legende
title('2D-Plot af3');
xlabel('t in s');
ylabel('eeg-Pegel - Einheit???');
colorbar;                   % nur damit alle Plots gleich breit sind

%% Diskrete Wavelet-Transformation (DWT)
% entsprechend dem Beispiel auf:
% www.mathworks.com/help/wavelet/examples/continuous-and-discrete-wavelet-analysis.html
lev   = 5;
wname = 'sym2';
nbcol = 64;
[c,l] = wavedec(af3interp,lev,wname);

%% "Expand the discrete wavelet coefficients for visualization":
len = length(af3interp);
cfd = zeros(lev,len);
for k = 1:lev
    d = detcoef(c,l,k);
    d = d(:)';
    d = d(ones(1,2^k),:);
    cfd(k,:) = wkeep1(d(:)',len);
end
cfd =  cfd(:);
I = find(abs(cfd)<sqrt(eps));
cfd(I) = zeros(size(I));
cfd = reshape(cfd,lev,len);
cfd = wcodemat(cfd,nbcol,'row');

%% Plot the discrete coefficients.
subplot(3,1,2); 
colormap(pink(nbcol));
tics = 1:lev;
labs = int2str((1:lev)');
image(t_Eq, tics, cfd);
set(gca,...
    'YTickLabelMode','manual','YDir','normal', ...
    'Box','On','YTick',tics,'YTickLabel',labs);
title('Discrete Wavelet Transform, Absolute Coefficients.');
xlabel('t in s');
ylabel('Level');
colorbar;


%% Kontinuierliche Wavelet-Transformation (CWT)
subplot(3,1,3); 
scales = (1:32); % Levels 1 to 5 correspond to scales 2, 4, 8, 16 and 32.
cwt(af3interp,scales,wname,'plot');
colormap(pink(nbcol));
colorbar;


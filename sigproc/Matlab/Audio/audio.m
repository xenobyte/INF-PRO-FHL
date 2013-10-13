%% audio.m: Verarbeiten von Audiodateien
%% 1.) Audio einlesen
fn = 'Sounds/santa_maria.wav';
[y,fs,nbits] = wavread(fn);
fs, nbits %% Werte anzeigen
whos y    %% Dimension anzeigen
%% 2.) Audio abspielen
soundsc(y, fs);
%% 3.) Audiodaten modifizieren:
%%    linker Kanal: *0.9
%%    rechter Kanal: Rampe von 0 bis 1
tmp = y(:,1); %% linker Kanal
tmp = tmp * 0.9;
y(:,1) = tmp;
rampe = linspace(0,1, length(y));
whos rampe
%% Zeilenvektor in Spaltenvektor umwandeln
rampe = rampe.'; whos rampe
%% Elementweise multiplizieren
  %% Operator: ``.*'' !!
y(:,2) = y(:,2) .* rampe;
soundsc(y, fs);
%% Resultat ausgeben
fnout='Sounds/santa_maria_neu.wav';
wavwrite(y, fs, nbits, fnout);
ls Sounds/*.wav
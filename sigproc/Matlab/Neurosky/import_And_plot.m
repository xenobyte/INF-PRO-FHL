function import_And_plot()

path='D:/BciAuswertung/';
files=dir([path,'*.csv']);

for i=1:length(files)
    %Auswertungsdaten in einer Matrix speichern
    dataMatrix=csvread([path,files(i).name]);
    %Plot
  
    hold on
    subplot(1,2,1)
    plot(dataMatrix(:,1),dataMatrix(:,2),'red')
    xlabel('Zeit [min]');
    ylabel('Aufmerksamkeit %');
    title (sprintf('Auswertung für %d Probe(n)',length(files)));
  
    hold on
    subplot(1,2,2)
    plot(dataMatrix(:,1),dataMatrix(:,3),'blue')
    xlabel('Zeit [min]');
    ylabel('Entspannung %');
     title (sprintf('Auswertung für %d Probe(n)',length(files)));
     

end 


%Plot Sine cosine und sin(2*pi*1000*t)+0.25*sin(2*pi*3000*t+pi/4)
%F : Korrelationsfrequenz 
%Usage plotKorrelation(1) für 1 Khz


function  plotKorrelation(f)

subplot(1,2,1)
h = ezplot(sprintf('cos(2*pi*%i*1000*t)',f),[0 0.001]);
set(h,'color','r');
hold on
ezplot ('sin(2*pi*1000*t)+0.25*sin(2*pi*3000*t+pi/4)', [0 0.001])
title (sprintf('Korrelation Re(X(n)) mit F = %i Hz',f))
hold off

subplot(1,2,2)
h = ezplot(sprintf('sin(2*pi*%i*1000*t)',f),[0 0.001]);
set(h,'color','r');
hold on
ezplot ('sin(2*pi*1000*t)+0.25*sin(2*pi*3000*t+pi/4)', [0 0.001])
title  (sprintf('Korrelation Im(X(n)) mit F = %i Hz',f))
hold off

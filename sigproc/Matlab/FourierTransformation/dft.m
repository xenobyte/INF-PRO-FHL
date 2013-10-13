%Demo DFT
function [X] = dft(x)
Xsize = length(x);
for m=0:Xsize-1
    summ =0;
    for n=0:Xsize-1
        summ = summ+x(n+1)*(cos(2*pi*n*m/Xsize)- 1i*sin(2*pi*n*m/Xsize));
    end
    X(m+1)=summ;
end

    
I = imread("fotoPlanta.jpg");
%imshow(I)
sizeI = size(I);

%rect = getrect
rect = [75, 49, 271, 267];

x1 =rect(1);
x2 = x1 + rect(3);
y1 =rect(2);
y2 = y1 + rect(4);
imSel = I(y1:y2, x1:x2,:);
imshow(imSel);

%Punt mitjà:
yMed = round ((y1+y2)/2);
xMed = round ((x1+x2)/2);

%Matriu al punt mitj:
imMedy = arrayfun(@(pixel)(pixel(1)-yMed), I);
imMedx = arrayfun(@(pixel)(pixel(1)-xMed), I);

imHSV = rgb2hsv(I);
Hx = arrayfun(@(x) cos(x), imHSV(:,:,1));
Hy = arrayfun(@(y) sin(y), imHSV(:,:,1));
imHxHySV = imHSV(:,:,2:3);
imHxHySV(:,:,3) = Hx; %agafem el sinus, cosinus per evitar el factor ciclic del Hue.
imHxHySV(:,:,4) = Hy; 
imHxHySV(:,:,5) = imMedy(:,:,1);
imHxHySV(:,:,6) = imMedx(:,:,1);

[MAXFILA, MAXCOL, chan] = size(imHxHySV);
O = reshape(imHxHySV, MAXFILA*MAXCOL,6);

k = 20; %nombre de classes
C = kmeans(O,k);
%Visualització de la imatge:
IC = reshape(C,MAXFILA,MAXCOL); 
rgb = label2rgb(IC); 
imshow(rgb)

MASK = zeros(sizeI); %0 fora del rectangle
MASK = MASK(:,:,1);
MASK(y1:y2, x1:x2) = 1; %1 dins del rectangle
%imshow(MASK);	

H = [C, MASK(:)];
aux = size(H);
Hist0 = zeros(k,1);
Hist1 = zeros(k,1);
for x = 1:aux(1)
    claseActual = H(x,1);  
    dinsForaActual = H(x,2);
    if dinsForaActual == 0
        Hist0(claseActual) = Hist0(claseActual) + 1;
    else
        Hist1(claseActual) = Hist1(claseActual) + 1;
    end
end

RES = Hist1 > Hist0;

H = [C, MASK(:)];
aux = size(H);
M = zeros((aux:1));
for x = 1:aux(1)
    claseActual = H(x,1);
    dinsForaActual = H(x,2);
    M(x) = RES(claseActual);  
end
M = reshape(M, MAXFILA,MAXCOL); 
imshow(M);


se = strel('disk', 5);  
Mopen= imopen(M, se);
MfillHoles = imfill(Mopen, 'holes');
montage({M,Mopen,MfillHoles});
image = MfillHoles;



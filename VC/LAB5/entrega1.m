L = imread('Laberint.png');

W = L(:,:,1) > 128; % no murs
R = L(:,:,1) > 128 & L(:,:,2) < 128; % punts vermells

%montage({R W})
% busquem les vores del mapa:
%red = red == 0;
%imshow(red);

%montage({R W})
dist = 0;
se = [1 1 1; 1 1 1; 1 1 1];
I = R;

while bwconncomp(I).NumObjects == 2
    I = imdilate(I, se);
    I = and(I, W);
    dist = dist + 1;
end
dist*2;
montage({L, I});

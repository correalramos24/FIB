I = imread("llavors.png");

I = double(I)/ double(max(max(I)));
I_correct_light = arrayfun(@(x) sqrt(x), I);

EE = strel('disk', 15); 
T = imtophat(I_correct_light, EE);


BW_I = imbinarize(T);

cc = bwconncomp(BW_I);
numObjects = cc.NumObjects

montage({I_correct_light, T, BW_I});
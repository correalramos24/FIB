#include <stdlib.h>
#include <stdio.h>
#include <string.h>


int main(){
    int * iP = malloc(sizeof(int));
    free(iP);
    free(iP);
}
#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

void write ( int n, int p, const vector<string>& s, vector<int>& sol) {
	for ( int j = 0; j < p; ++j) {
		cout << "subconjunt " << j+1 << ": {";
		string aux = "";
		for ( int i = 0; i < n; ++i)
		if ( sol [ i ] == j ) {
			cout << aux << s[i];
			aux = ",";
		}
		cout << "}" << endl;
	}
	cout << endl;
}

//k es lo que ya has asignado
//n es el numero de palabras
//p es el numero de subconjuntos

void particions(int k, int n, int p, const vector<string>& s, vector<int>& sol){
    if (k == n){ //se han asignado todos los elementos
        write (n, p, s , sol);
        return;
    }
    for ( int i = 0; i < p; ++i) {
		sol [k] = i;
		particions(k+1, n, p, s, sol);
	}

}

int main(){
    int n, p;
    cin >> n;
    vector<string> words(n);
    string s;
    for(int i = 0; i < n; ++i){
        cin >> s;
        words[i] = s;
    }
    cin >> p;

    vector<int> sol(n);
	particions(0, n, p, words , sol );

   
    
}
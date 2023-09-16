#include <iostream>
#include <vector>


using namespace std;

int posicio(double x, const vector<double>& v, int esq, int dre){
    if (esq > dre) return -1;
    int med = ( esq + dre ) / 2;
    double val_med = v[med];
    if (v[med] == x) return med;
    else if (x > val_med) return posicio(x, v, med+1, dre);
    else if (x < val_med) return posicio(x,v, esq, med-1);
}

int main() {
    int n;
    while (cin >> n) {
        vector<double> V(n);
        for (int i = 0; i < n; ++i) cin >> V[i];
        int t;
        cin >> t;
        while (t--) {
            double x;
            int esq, dre;
            cin >> x >> esq >> dre;
            cout << posicio(x, V, esq, dre) << endl;
        }
    }
}

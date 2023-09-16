# Programació funcional en C++

Existeixen diverses aproximacions per convertir el C++ en un llenguatge funcional.

[TOC]

### Inferència de tipus

* `auto` infereix el tipus d'una variable automàticament.
* `decltype` Declara una variable com el tipus de retorn d'una expressió. Usat en programació genèrica

````c++
auto i = 3;
for (auto x : C)

int  fun1() { return 10; }
char fun2() { return 'g'; }
decltype(fun1()) x; //x es de tipus int
decltype(fun2()) y; //y es de tipus char
````

### Funcions d'ordre superior

Des de sempre es poden utilitzar punters a funcions com paràmetres.

````c++
//C: punter a una funció que retorna un enter i te dos paràmetres
void qsort(void *v, size_t n, size_t sze, int (*cmp)(const void *, const void*));
//C++: Amb templates es poden pasar classes com a paràmetres:
template <class RandomAccessIterator, class Compare>
void sort (RandomAccessIterator first, RandomAccessIterator last, Compare cmp);
````

### λ- funcions

Les funcions anònimes es construeixen amb `[capture](paràmetres){instr}`. Es pot o no donar el tipus de retorn o dels paràmetres.

````c++
sort(v.begin(), v.end(), [](int a, int b) {return a > b;}); //funció de dos paràmetres.
auto suma = [](double a, double a) -> double {return a + b;}; //funció explicitant el tipus de retorn.
auto doble = [](auto x) { return x + x; };
````

El punt diferencial de Haskell, rau en l'àmbit de visibilitat d'aquesta funció anònima, n'hi han diferents alternatives:

* [] No es captura res :arrow_right: No "veu" les variables fora de la λ funció.
* [=] Captura tot per valor :arrow_right: S'agafa el valor de totes les variables del àmbit.
* [&] Captura tot per referència :arrow_right: S'agafa una referència a totes les variables del àmbit.
* [=, &a] Altres combinacions.

````c++
string salut = "Hola";
auto miss = [c](string nom) {
    cout << salut << ' ' << nom << endl;
}
miss("Jana"); //-> Hola Jana
salut = "Eo"; //si la 'c' es =, llavorns el string no es veura alterat -> Hola Laia
miss("Laia"); //si la 'c' es &, llavorns la λ funció veu el nou valor -> Eo Laia
````

### Tipus "function"

Es tracta d'un tipus genèric que ajunta tots els tipus de funcions (típiques C, λ- funcions, functors()).

```c++
#include <functional>
int suma1(int a, int b) { return a + b; }
auto suma2 = [](int a, int b) { return a + b; };
struct suma3 {
    int operator() (int a, int b) { return a + b; }; //c funtor != functor de haskell.
};
suma3 sumador;
function<int(int, int)> f;                  // f :: int, int -> int
f = suma1;      cout << f(2, 3) << endl;    // utilitza una funció de sempre
f = suma2;      cout << f(2, 3) << endl;    // utilitza una λ-funció
f = sumador;    cout << f(2, 3) << endl;    // utilitza un functor
```

### ToDo: HOF

### Tipus a la Haskell (C++ 17)

Es defineixen aproximacions per `Maybe a` i `Either a b`

````c++
#include <optional>
using namespace std;
optional<string> user_name(int user_id) {
    if (...) return "the name";
    else return {};             // o també nullopt
}
int main() {
    optional<string> name = user_name(1234);
    if (name) cout << *name << endl;
}

#include <variant>
using namespace std;
variant<int, float> num1 = 3;
variant<int, float> num2 = 3.5;
cout << num1.index();           // 0
cout << num1.get<int>;          // 3
cout << num1.get<0>;            // 3
cout << num1.get<float>;        // indefinit
cout << num1.get<1>;            // indefinit
````




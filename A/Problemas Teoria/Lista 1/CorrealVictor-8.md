## Problema 8

Digueu si cadascuna de les armacions següents són certes o falses (i per què).

(a)  Asimptoticament $(1 + o(1))^{(\omega(1))} = 1$

> Cert, son tot valors constants.

(b) Si $f(n) = (n + 2)n/2$ aleshores $f(n) \in \Theta (n^2)$

> Desenvolupant l'expressió obtenim : $f(n) = (n^2 +2n)*1/2 \equiv f(n) \in \Theta (n^2)$
>
> Per tant, l'afirmació es certa

(c) Si $f(n) = (n + 2)n/2$ aleshores $f(n) \in \Theta (n^3)$

> L'afirmació es falsa, a l'apartat b s'ha resolt que el creixement asimptòtic es $n^2$

(d) $n^{1.1} \in O(n (lg n))^2$

>Fals, calculant $\lim_{n\to\infty} = \frac{f(n)}{g(n)}=\frac{n^{1.1}}{n(lg n)^2} = \infty$, per tant l'expressió $n^{1.1}$ creix asimptòticament més ràpid que g(n) i mai podrà ser O gran.

(e)$n^{0.01} \in \omega (n (lg n))^2$

>Fals, calculant $\lim_{n\to\infty} \frac{f(n)}{g(n)}= \frac{n^{0.01}}{n(lg n)^2} = 0$ veiem que g(n) creix asimptòticament més ràpid que f(n).
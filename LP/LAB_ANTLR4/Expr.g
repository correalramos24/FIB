grammar Expr;

/** Sintàxis minuscula */
root : comanda EOF
        | comanda* EOF
        | comanda* ENDLINE
        ;


comanda : newVar ENDLINE
        | expr  ENDLINE
        ;

newVar : ID ASSIGN expr;
write: WRITE ID;

expr : <assoc=right> expr POW expr
    | expr MULT expr
    | expr DIVISIO expr
    | expr MES expr
    | expr RESTA expr
    | NUM
    ;

/** Lexica MAJUSCULA */
NUM : [0-9]+;
MES : '+';
RESTA : '-';
MULT : '*';
DIVISIO: '/';
POW: '^';

ASSIGN : ':=';

WRITE: 'write';
ID:[a-zA-Z]+;

ENDLINE : '\r\n'+|'\n\r'+|'\n'+|'\r'+;

WS : [ \t]+ -> skip;
COMMENT : '//'~[\r\n]*ENDLINE -> skip;